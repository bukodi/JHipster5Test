import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { HttpResponse, HttpErrorResponse } from '@angular/common/http';
import { Observable } from 'rxjs';
import { JhiDataUtils } from 'ng-jhipster';

import { ICertificateAuthority } from 'app/shared/model/certificate-authority.model';
import { CertificateAuthorityService } from './certificate-authority.service';

@Component({
    selector: '-certificate-authority-update',
    templateUrl: './certificate-authority-update.component.html'
})
export class CertificateAuthorityUpdateComponent implements OnInit {
    private _certificateAuthority: ICertificateAuthority;
    isSaving: boolean;

    constructor(
        private dataUtils: JhiDataUtils,
        private certificateAuthorityService: CertificateAuthorityService,
        private activatedRoute: ActivatedRoute
    ) {}

    ngOnInit() {
        this.isSaving = false;
        this.activatedRoute.data.subscribe(({ certificateAuthority }) => {
            this.certificateAuthority = certificateAuthority;
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
        if (this.certificateAuthority.id !== undefined) {
            this.subscribeToSaveResponse(this.certificateAuthorityService.update(this.certificateAuthority));
        } else {
            this.subscribeToSaveResponse(this.certificateAuthorityService.create(this.certificateAuthority));
        }
    }

    private subscribeToSaveResponse(result: Observable<HttpResponse<ICertificateAuthority>>) {
        result.subscribe(
            (res: HttpResponse<ICertificateAuthority>) => this.onSaveSuccess(),
            (res: HttpErrorResponse) => this.onSaveError()
        );
    }

    private onSaveSuccess() {
        this.isSaving = false;
        this.previousState();
    }

    private onSaveError() {
        this.isSaving = false;
    }
    get certificateAuthority() {
        return this._certificateAuthority;
    }

    set certificateAuthority(certificateAuthority: ICertificateAuthority) {
        this._certificateAuthority = certificateAuthority;
    }
}
