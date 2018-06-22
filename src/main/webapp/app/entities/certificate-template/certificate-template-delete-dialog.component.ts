import { Component, OnInit, OnDestroy } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';

import { NgbActiveModal, NgbModal, NgbModalRef } from '@ng-bootstrap/ng-bootstrap';
import { JhiEventManager } from 'ng-jhipster';

import { ICertificateTemplate } from 'app/shared/model/certificate-template.model';
import { CertificateTemplateService } from './certificate-template.service';

@Component({
    selector: '-certificate-template-delete-dialog',
    templateUrl: './certificate-template-delete-dialog.component.html'
})
export class CertificateTemplateDeleteDialogComponent {
    certificateTemplate: ICertificateTemplate;

    constructor(
        private certificateTemplateService: CertificateTemplateService,
        public activeModal: NgbActiveModal,
        private eventManager: JhiEventManager
    ) {}

    clear() {
        this.activeModal.dismiss('cancel');
    }

    confirmDelete(id: number) {
        this.certificateTemplateService.delete(id).subscribe(response => {
            this.eventManager.broadcast({
                name: 'certificateTemplateListModification',
                content: 'Deleted an certificateTemplate'
            });
            this.activeModal.dismiss(true);
        });
    }
}

@Component({
    selector: '-certificate-template-delete-popup',
    template: ''
})
export class CertificateTemplateDeletePopupComponent implements OnInit, OnDestroy {
    private ngbModalRef: NgbModalRef;

    constructor(private activatedRoute: ActivatedRoute, private router: Router, private modalService: NgbModal) {}

    ngOnInit() {
        this.activatedRoute.data.subscribe(({ certificateTemplate }) => {
            setTimeout(() => {
                this.ngbModalRef = this.modalService.open(CertificateTemplateDeleteDialogComponent as Component, {
                    size: 'lg',
                    backdrop: 'static'
                });
                this.ngbModalRef.componentInstance.certificateTemplate = certificateTemplate;
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
