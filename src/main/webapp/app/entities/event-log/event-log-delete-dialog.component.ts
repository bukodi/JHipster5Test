import { Component, OnInit, OnDestroy } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';

import { NgbActiveModal, NgbModal, NgbModalRef } from '@ng-bootstrap/ng-bootstrap';
import { JhiEventManager } from 'ng-jhipster';

import { IEventLog } from 'app/shared/model/event-log.model';
import { EventLogService } from './event-log.service';

@Component({
    selector: '-event-log-delete-dialog',
    templateUrl: './event-log-delete-dialog.component.html'
})
export class EventLogDeleteDialogComponent {
    eventLog: IEventLog;

    constructor(private eventLogService: EventLogService, public activeModal: NgbActiveModal, private eventManager: JhiEventManager) {}

    clear() {
        this.activeModal.dismiss('cancel');
    }

    confirmDelete(id: number) {
        this.eventLogService.delete(id).subscribe(response => {
            this.eventManager.broadcast({
                name: 'eventLogListModification',
                content: 'Deleted an eventLog'
            });
            this.activeModal.dismiss(true);
        });
    }
}

@Component({
    selector: '-event-log-delete-popup',
    template: ''
})
export class EventLogDeletePopupComponent implements OnInit, OnDestroy {
    private ngbModalRef: NgbModalRef;

    constructor(private activatedRoute: ActivatedRoute, private router: Router, private modalService: NgbModal) {}

    ngOnInit() {
        this.activatedRoute.data.subscribe(({ eventLog }) => {
            setTimeout(() => {
                this.ngbModalRef = this.modalService.open(EventLogDeleteDialogComponent as Component, { size: 'lg', backdrop: 'static' });
                this.ngbModalRef.componentInstance.eventLog = eventLog;
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
