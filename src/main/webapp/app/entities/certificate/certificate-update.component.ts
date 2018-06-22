import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { HttpResponse, HttpErrorResponse } from '@angular/common/http';
import { Observable } from 'rxjs';
import { JhiAlertService, JhiDataUtils } from 'ng-jhipster';

import { ICertificate } from 'app/shared/model/certificate.model';
import { CertificateService } from './certificate.service';
import { ICertificateAuthority } from 'app/shared/model/certificate-authority.model';
import { CertificateAuthorityService } from 'app/entities/certificate-authority';
import { ICertificateTemplate } from 'app/shared/model/certificate-template.model';
import { CertificateTemplateService } from 'app/entities/certificate-template';
import { IIdentity } from 'app/shared/model/identity.model';
import { IdentityService } from 'app/entities/identity';
import { ILogicalCard } from 'app/shared/model/logical-card.model';
import { LogicalCardService } from 'app/entities/logical-card';

@Component({
    selector: '-certificate-update',
    templateUrl: './certificate-update.component.html'
})
export class CertificateUpdateComponent implements OnInit {
    private _certificate: ICertificate;
    isSaving: boolean;

    certificateauthorities: ICertificateAuthority[];

    certificatetemplates: ICertificateTemplate[];

    identities: IIdentity[];

    logicalcards: ILogicalCard[];

    constructor(
        private dataUtils: JhiDataUtils,
        private jhiAlertService: JhiAlertService,
        private certificateService: CertificateService,
        private certificateAuthorityService: CertificateAuthorityService,
        private certificateTemplateService: CertificateTemplateService,
        private identityService: IdentityService,
        private logicalCardService: LogicalCardService,
        private activatedRoute: ActivatedRoute
    ) {}

    ngOnInit() {
        this.isSaving = false;
        this.activatedRoute.data.subscribe(({ certificate }) => {
            this.certificate = certificate;
        });
        this.certificateAuthorityService.query().subscribe(
            (res: HttpResponse<ICertificateAuthority[]>) => {
                this.certificateauthorities = res.body;
            },
            (res: HttpErrorResponse) => this.onError(res.message)
        );
        this.certificateTemplateService.query().subscribe(
            (res: HttpResponse<ICertificateTemplate[]>) => {
                this.certificatetemplates = res.body;
            },
            (res: HttpErrorResponse) => this.onError(res.message)
        );
        this.identityService.query().subscribe(
            (res: HttpResponse<IIdentity[]>) => {
                this.identities = res.body;
            },
            (res: HttpErrorResponse) => this.onError(res.message)
        );
        this.logicalCardService.query().subscribe(
            (res: HttpResponse<ILogicalCard[]>) => {
                this.logicalcards = res.body;
            },
            (res: HttpErrorResponse) => this.onError(res.message)
        );
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
        if (this.certificate.id !== undefined) {
            this.subscribeToSaveResponse(this.certificateService.update(this.certificate));
        } else {
            this.subscribeToSaveResponse(this.certificateService.create(this.certificate));
        }
    }

    private subscribeToSaveResponse(result: Observable<HttpResponse<ICertificate>>) {
        result.subscribe((res: HttpResponse<ICertificate>) => this.onSaveSuccess(), (res: HttpErrorResponse) => this.onSaveError());
    }

    private onSaveSuccess() {
        this.isSaving = false;
        this.previousState();
    }

    private onSaveError() {
        this.isSaving = false;
    }

    private onError(errorMessage: string) {
        this.jhiAlertService.error(errorMessage, null, null);
    }

    trackCertificateAuthorityById(index: number, item: ICertificateAuthority) {
        return item.id;
    }

    trackCertificateTemplateById(index: number, item: ICertificateTemplate) {
        return item.id;
    }

    trackIdentityById(index: number, item: IIdentity) {
        return item.id;
    }

    trackLogicalCardById(index: number, item: ILogicalCard) {
        return item.id;
    }

    getSelected(selectedVals: Array<any>, option: any) {
        if (selectedVals) {
            for (let i = 0; i < selectedVals.length; i++) {
                if (option.id === selectedVals[i].id) {
                    return selectedVals[i];
                }
            }
        }
        return option;
    }
    get certificate() {
        return this._certificate;
    }

    set certificate(certificate: ICertificate) {
        this._certificate = certificate;
    }
}
