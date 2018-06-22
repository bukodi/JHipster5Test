import { Component, OnInit, OnDestroy } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';

import { NgbActiveModal, NgbModal, NgbModalRef } from '@ng-bootstrap/ng-bootstrap';
import { JhiEventManager } from 'ng-jhipster';

import { ICertificate } from 'app/shared/model/certificate.model';
import { CertificateService } from './certificate.service';

@Component({
    selector: '-certificate-delete-dialog',
    templateUrl: './certificate-delete-dialog.component.html'
})
export class CertificateDeleteDialogComponent {
    certificate: ICertificate;

    constructor(
        private certificateService: CertificateService,
        public activeModal: NgbActiveModal,
        private eventManager: JhiEventManager
    ) {}

    clear() {
        this.activeModal.dismiss('cancel');
    }

    confirmDelete(id: number) {
        this.certificateService.delete(id).subscribe(response => {
            this.eventManager.broadcast({
                name: 'certificateListModification',
                content: 'Deleted an certificate'
            });
            this.activeModal.dismiss(true);
        });
    }
}

@Component({
    selector: '-certificate-delete-popup',
    template: ''
})
export class CertificateDeletePopupComponent implements OnInit, OnDestroy {
    private ngbModalRef: NgbModalRef;

    constructor(private activatedRoute: ActivatedRoute, private router: Router, private modalService: NgbModal) {}

    ngOnInit() {
        this.activatedRoute.data.subscribe(({ certificate }) => {
            setTimeout(() => {
                this.ngbModalRef = this.modalService.open(CertificateDeleteDialogComponent as Component, {
                    size: 'lg',
                    backdrop: 'static'
                });
                this.ngbModalRef.componentInstance.certificate = certificate;
                this.ngbModalRef.result.then(
                    result => {
                        this.router.navigate([{ outlets: { popup: null } }], { replaceUrl: true, queryParamsHandling: 'merge' });
                        this.ngbModalRef = null;
                    },
                    reason => {
                        this.router.navigate([{ outlets: { popup: null } }], { replaceUrl: true, queryParamsHandling: 'merge' });
                        this.ngbModalRef = null;
                    }
                );
            }, 0);
        });
    }

    ngOnDestroy() {
        this.ngbModalRef = null;
    }
}
