import { Component, OnInit, OnDestroy } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';

import { NgbActiveModal, NgbModal, NgbModalRef } from '@ng-bootstrap/ng-bootstrap';
import { JhiEventManager } from 'ng-jhipster';

import { ICardType } from 'app/shared/model/card-type.model';
import { CardTypeService } from './card-type.service';

@Component({
    selector: '-card-type-delete-dialog',
    templateUrl: './card-type-delete-dialog.component.html'
})
export class CardTypeDeleteDialogComponent {
    cardType: ICardType;

    constructor(private cardTypeService: CardTypeService, public activeModal: NgbActiveModal, private eventManager: JhiEventManager) {}

    clear() {
        this.activeModal.dismiss('cancel');
    }

    confirmDelete(id: number) {
        this.cardTypeService.delete(id).subscribe(response => {
            this.eventManager.broadcast({
                name: 'cardTypeListModification',
                content: 'Deleted an cardType'
            });
            this.activeModal.dismiss(true);
        });
    }
}

@Component({
    selector: '-card-type-delete-popup',
    template: ''
})
export class CardTypeDeletePopupComponent implements OnInit, OnDestroy {
    private ngbModalRef: NgbModalRef;

    constructor(private activatedRoute: ActivatedRoute, private router: Router, private modalService: NgbModal) {}

    ngOnInit() {
        this.activatedRoute.data.subscribe(({ cardType }) => {
            setTimeout(() => {
                this.ngbModalRef = this.modalService.open(CardTypeDeleteDialogComponent as Component, { size: 'lg', backdrop: 'static' });
                this.ngbModalRef.componentInstance.cardType = cardType;
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
