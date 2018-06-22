import { Component, OnInit, OnDestroy } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';

import { NgbActiveModal, NgbModal, NgbModalRef } from '@ng-bootstrap/ng-bootstrap';
import { JhiEventManager } from 'ng-jhipster';

import { IIdentityProfile } from 'app/shared/model/identity-profile.model';
import { IdentityProfileService } from './identity-profile.service';

@Component({
    selector: '-identity-profile-delete-dialog',
    templateUrl: './identity-profile-delete-dialog.component.html'
})
export class IdentityProfileDeleteDialogComponent {
    identityProfile: IIdentityProfile;

    constructor(
        private identityProfileService: IdentityProfileService,
        public activeModal: NgbActiveModal,
        private eventManager: JhiEventManager
    ) {}

    clear() {
        this.activeModal.dismiss('cancel');
    }

    confirmDelete(id: number) {
        this.identityProfileService.delete(id).subscribe(response => {
            this.eventManager.broadcast({
                name: 'identityProfileListModification',
                content: 'Deleted an identityProfile'
            });
            this.activeModal.dismiss(true);
        });
    }
}

@Component({
    selector: '-identity-profile-delete-popup',
    template: ''
})
export class IdentityProfileDeletePopupComponent implements OnInit, OnDestroy {
    private ngbModalRef: NgbModalRef;

    constructor(private activatedRoute: ActivatedRoute, private router: Router, private modalService: NgbModal) {}

    ngOnInit() {
        this.activatedRoute.data.subscribe(({ identityProfile }) => {
            setTimeout(() => {
                this.ngbModalRef = this.modalService.open(IdentityProfileDeleteDialogComponent as Component, {
                    size: 'lg',
                    backdrop: 'static'
                });
                this.ngbModalRef.componentInstance.identityProfile = identityProfile;
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
