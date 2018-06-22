import { Component, OnInit, OnDestroy } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';

import { NgbActiveModal, NgbModal, NgbModalRef } from '@ng-bootstrap/ng-bootstrap';
import { JhiEventManager } from 'ng-jhipster';

import { ICertificateAuthority } from 'app/shared/model/certificate-authority.model';
import { CertificateAuthorityService } from './certificate-authority.service';

@Component({
    selector: '-certificate-authority-delete-dialog',
    templateUrl: './certificate-authority-delete-dialog.component.html'
})
export class CertificateAuthorityDeleteDialogComponent {
    certificateAuthority: ICertificateAuthority;

    constructor(
        private certificateAuthorityService: CertificateAuthorityService,
        public activeModal: NgbActiveModal,
        private eventManager: JhiEventManager
    ) {}

    clear() {
        this.activeModal.dismiss('cancel');
    }

    confirmDelete(id: number) {
        this.certificateAuthorityService.delete(id).subscribe(response => {
            this.eventManager.broadcast({
                name: 'certificateAuthorityListModification',
                content: 'Deleted an certificateAuthority'
            });
            this.activeModal.dismiss(true);
        });
    }
}

@Component({
    selector: '-certificate-authority-delete-popup',
    template: ''
})
export class CertificateAuthorityDeletePopupComponent implements OnInit, OnDestroy {
    private ngbModalRef: NgbModalRef;

    constructor(private activatedRoute: ActivatedRoute, private router: Router, private modalService: NgbModal) {}

    ngOnInit() {
        this.activatedRoute.data.subscribe(({ certificateAuthority }) => {
            setTimeout(() => {
                this.ngbModalRef = this.modalService.open(CertificateAuthorityDeleteDialogComponent as Component, {
                    size: 'lg',
                    backdrop: 'static'
                });
                this.ngbModalRef.componentInstance.certificateAuthority = certificateAuthority;
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
