import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { HttpResponse, HttpErrorResponse } from '@angular/common/http';
import { Observable } from 'rxjs';
import { JhiDataUtils } from 'ng-jhipster';

import { IDirectoryServer } from 'app/shared/model/directory-server.model';
import { DirectoryServerService } from './directory-server.service';

@Component({
    selector: '-directory-server-update',
    templateUrl: './directory-server-update.component.html'
})
export class DirectoryServerUpdateComponent implements OnInit {
    private _directoryServer: IDirectoryServer;
    isSaving: boolean;

    constructor(
        private dataUtils: JhiDataUtils,
        private directoryServerService: DirectoryServerService,
        private activatedRoute: ActivatedRoute
    ) {}

    ngOnInit() {
        this.isSaving = false;
        this.activatedRoute.data.subscribe(({ directoryServer }) => {
            this.directoryServer = directoryServer;
        });
    }

    byteSize(field) {
        return this.dataUtils.byteSize(field);
    }

    openFile(contentType, field) {
        return this.dataUtils.openFile(contentType, field);
    }

    setFileData(event, entity, field, isImage) {
        this.dataUtils.setFileData(event, entity, field, isImage);
    }

    previousState() {
        window.history.back();
    }

    save() {
        this.isSaving = true;
        if (this.directoryServer.id !== undefined) {
            this.subscribeToSaveResponse(this.directoryServerService.update(this.directoryServer));
        } else {
            this.subscribeToSaveResponse(this.directoryServerService.create(this.directoryServer));
        }
    }

    private subscribeToSaveResponse(result: Observable<HttpResponse<IDirectoryServer>>) {
        result.subscribe((res: HttpResponse<IDirectoryServer>) => this.onSaveSuccess(), (res: HttpErrorResponse) => this.onSaveError());
    }

    private onSaveSuccess() {
        this.isSaving = false;
        this.previousState();
    }

    private onSaveError() {
        this.isSaving = false;
    }
    get directoryServer() {
        return this._directoryServer;
    }

    set directoryServer(directoryServer: IDirectoryServer) {
        this._directoryServer = directoryServer;
    }
}
