import { Component, OnInit, OnDestroy } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';

import { NgbActiveModal, NgbModal, NgbModalRef } from '@ng-bootstrap/ng-bootstrap';
import { JhiEventManager } from 'ng-jhipster';

import { IIdentity } from 'app/shared/model/identity.model';
import { IdentityService } from './identity.service';

@Component({
    selector: '-identity-delete-dialog',
    templateUrl: './identity-delete-dialog.component.html'
})
export class IdentityDeleteDialogComponent {
    identity: IIdentity;

    constructor(private identityService: IdentityService, public activeModal: NgbActiveModal, private eventManager: JhiEventManager) {}

    clear() {
        this.activeModal.dismiss('cancel');
    }

    confirmDelete(id: number) {
        this.identityService.delete(id).subscribe(response => {
            this.eventManager.broadcast({
                name: 'identityListModification',
                content: 'Deleted an identity'
            });
            this.activeModal.dismiss(true);
        });
    }
}

@Component({
    selector: '-identity-delete-popup',
    template: ''
})
export class IdentityDeletePopupComponent implements OnInit, OnDestroy {
    private ngbModalRef: NgbModalRef;

    constructor(private activatedRoute: ActivatedRoute, private router: Router, private modalService: NgbModal) {}

    ngOnInit() {
        this.activatedRoute.data.subscribe(({ identity }) => {
            setTimeout(() => {
                this.ngbModalRef = this.modalService.open(IdentityDeleteDialogComponent as Component, { size: 'lg', backdrop: 'static' });
                this.ngbModalRef.componentInstance.identity = identity;
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
