import { Component, OnInit, OnDestroy } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';

import { NgbActiveModal, NgbModal, NgbModalRef } from '@ng-bootstrap/ng-bootstrap';
import { JhiEventManager } from 'ng-jhipster';

import { ILogicalCard } from 'app/shared/model/logical-card.model';
import { LogicalCardService } from './logical-card.service';

@Component({
    selector: '-logical-card-delete-dialog',
    templateUrl: './logical-card-delete-dialog.component.html'
})
export class LogicalCardDeleteDialogComponent {
    logicalCard: ILogicalCard;

    constructor(
        private logicalCardService: LogicalCardService,
        public activeModal: NgbActiveModal,
        private eventManager: JhiEventManager
    ) {}

    clear() {
        this.activeModal.dismiss('cancel');
    }

    confirmDelete(id: number) {
        this.logicalCardService.delete(id).subscribe(response => {
            this.eventManager.broadcast({
                name: 'logicalCardListModification',
                content: 'Deleted an logicalCard'
            });
            this.activeModal.dismiss(true);
        });
    }
}

@Component({
    selector: '-logical-card-delete-popup',
    template: ''
})
export class LogicalCardDeletePopupComponent implements OnInit, OnDestroy {
    private ngbModalRef: NgbModalRef;

    constructor(private activatedRoute: ActivatedRoute, private router: Router, private modalService: NgbModal) {}

    ngOnInit() {
        this.activatedRoute.data.subscribe(({ logicalCard }) => {
            setTimeout(() => {
                this.ngbModalRef = this.modalService.open(LogicalCardDeleteDialogComponent as Component, {
                    size: 'lg',
                    backdrop: 'static'
                });
                this.ngbModalRef.componentInstance.logicalCard = logicalCard;
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
