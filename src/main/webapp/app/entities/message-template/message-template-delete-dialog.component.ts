import { Component, OnInit, OnDestroy } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';

import { NgbActiveModal, NgbModal, NgbModalRef } from '@ng-bootstrap/ng-bootstrap';
import { JhiEventManager } from 'ng-jhipster';

import { IMessageTemplate } from 'app/shared/model/message-template.model';
import { MessageTemplateService } from './message-template.service';

@Component({
    selector: '-message-template-delete-dialog',
    templateUrl: './message-template-delete-dialog.component.html'
})
export class MessageTemplateDeleteDialogComponent {
    messageTemplate: IMessageTemplate;

    constructor(
        private messageTemplateService: MessageTemplateService,
        public activeModal: NgbActiveModal,
        private eventManager: JhiEventManager
    ) {}

    clear() {
        this.activeModal.dismiss('cancel');
    }

    confirmDelete(id: number) {
        this.messageTemplateService.delete(id).subscribe(response => {
            this.eventManager.broadcast({
                name: 'messageTemplateListModification',
                content: 'Deleted an messageTemplate'
            });
            this.activeModal.dismiss(true);
        });
    }
}

@Component({
    selector: '-message-template-delete-popup',
    template: ''
})
export class MessageTemplateDeletePopupComponent implements OnInit, OnDestroy {
    private ngbModalRef: NgbModalRef;

    constructor(private activatedRoute: ActivatedRoute, private router: Router, private modalService: NgbModal) {}

    ngOnInit() {
        this.activatedRoute.data.subscribe(({ messageTemplate }) => {
            setTimeout(() => {
                this.ngbModalRef = this.modalService.open(MessageTemplateDeleteDialogComponent as Component, {
                    size: 'lg',
                    backdrop: 'static'
                });
                this.ngbModalRef.componentInstance.messageTemplate = messageTemplate;
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
