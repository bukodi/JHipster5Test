import { Component, OnInit, OnDestroy } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';

import { NgbActiveModal, NgbModal, NgbModalRef } from '@ng-bootstrap/ng-bootstrap';
import { JhiEventManager } from 'ng-jhipster';

import { IDirectoryServer } from 'app/shared/model/directory-server.model';
import { DirectoryServerService } from './directory-server.service';

@Component({
    selector: '-directory-server-delete-dialog',
    templateUrl: './directory-server-delete-dialog.component.html'
})
export class DirectoryServerDeleteDialogComponent {
    directoryServer: IDirectoryServer;

    constructor(
        private directoryServerService: DirectoryServerService,
        public activeModal: NgbActiveModal,
        private eventManager: JhiEventManager
    ) {}

    clear() {
        this.activeModal.dismiss('cancel');
    }

    confirmDelete(id: number) {
        this.directoryServerService.delete(id).subscribe(response => {
            this.eventManager.broadcast({
                name: 'directoryServerListModification',
                content: 'Deleted an directoryServer'
            });
            this.activeModal.dismiss(true);
        });
    }
}

@Component({
    selector: '-directory-server-delete-popup',
    template: ''
})
export class DirectoryServerDeletePopupComponent implements OnInit, OnDestroy {
    private ngbModalRef: NgbModalRef;

    constructor(private activatedRoute: ActivatedRoute, private router: Router, private modalService: NgbModal) {}

    ngOnInit() {
        this.activatedRoute.data.subscribe(({ directoryServer }) => {
            setTimeout(() => {
                this.ngbModalRef = this.modalService.open(DirectoryServerDeleteDialogComponent as Component, {
                    size: 'lg',
                    backdrop: 'static'
                });
                this.ngbModalRef.componentInstance.directoryServer = directoryServer;
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
