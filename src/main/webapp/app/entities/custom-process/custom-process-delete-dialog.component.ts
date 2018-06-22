import { Component, OnInit, OnDestroy } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';

import { NgbActiveModal, NgbModal, NgbModalRef } from '@ng-bootstrap/ng-bootstrap';
import { JhiEventManager } from 'ng-jhipster';

import { ICustomProcess } from 'app/shared/model/custom-process.model';
import { CustomProcessService } from './custom-process.service';

@Component({
    selector: '-custom-process-delete-dialog',
    templateUrl: './custom-process-delete-dialog.component.html'
})
export class CustomProcessDeleteDialogComponent {
    customProcess: ICustomProcess;

    constructor(
        private customProcessService: CustomProcessService,
        public activeModal: NgbActiveModal,
        private eventManager: JhiEventManager
    ) {}

    clear() {
        this.activeModal.dismiss('cancel');
    }

    confirmDelete(id: number) {
        this.customProcessService.delete(id).subscribe(response => {
            this.eventManager.broadcast({
                name: 'customProcessListModification',
                content: 'Deleted an customProcess'
            });
            this.activeModal.dismiss(true);
        });
    }
}

@Component({
    selector: '-custom-process-delete-popup',
    template: ''
})
export class CustomProcessDeletePopupComponent implements OnInit, OnDestroy {
    private ngbModalRef: NgbModalRef;

    constructor(private activatedRoute: ActivatedRoute, private router: Router, private modalService: NgbModal) {}

    ngOnInit() {
        this.activatedRoute.data.subscribe(({ customProcess }) => {
            setTimeout(() => {
                this.ngbModalRef = this.modalService.open(CustomProcessDeleteDialogComponent as Component, {
                    size: 'lg',
                    backdrop: 'static'
                });
                this.ngbModalRef.componentInstance.customProcess = customProcess;
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
