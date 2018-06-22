import { Component, OnInit, OnDestroy } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';

import { NgbActiveModal, NgbModal, NgbModalRef } from '@ng-bootstrap/ng-bootstrap';
import { JhiEventManager } from 'ng-jhipster';

import { IPhysicalCard } from 'app/shared/model/physical-card.model';
import { PhysicalCardService } from './physical-card.service';

@Component({
    selector: '-physical-card-delete-dialog',
    templateUrl: './physical-card-delete-dialog.component.html'
})
export class PhysicalCardDeleteDialogComponent {
    physicalCard: IPhysicalCard;

    constructor(
        private physicalCardService: PhysicalCardService,
        public activeModal: NgbActiveModal,
        private eventManager: JhiEventManager
    ) {}

    clear() {
        this.activeModal.dismiss('cancel');
    }

    confirmDelete(id: number) {
        this.physicalCardService.delete(id).subscribe(response => {
            this.eventManager.broadcast({
                name: 'physicalCardListModification',
                content: 'Deleted an physicalCard'
            });
            this.activeModal.dismiss(true);
        });
    }
}

@Component({
    selector: '-physical-card-delete-popup',
    template: ''
})
export class PhysicalCardDeletePopupComponent implements OnInit, OnDestroy {
    private ngbModalRef: NgbModalRef;

    constructor(private activatedRoute: ActivatedRoute, private router: Router, private modalService: NgbModal) {}

    ngOnInit() {
        this.activatedRoute.data.subscribe(({ physicalCard }) => {
            setTimeout(() => {
                this.ngbModalRef = this.modalService.open(PhysicalCardDeleteDialogComponent as Component, {
                    size: 'lg',
                    backdrop: 'static'
                });
                this.ngbModalRef.componentInstance.physicalCard = physicalCard;
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
