import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { HttpResponse, HttpErrorResponse } from '@angular/common/http';
import { Observable } from 'rxjs';
import { JhiAlertService } from 'ng-jhipster';

import { ICertificateTemplate } from 'app/shared/model/certificate-template.model';
import { CertificateTemplateService } from './certificate-template.service';
import { ICertificateAuthority } from 'app/shared/model/certificate-authority.model';
import { CertificateAuthorityService } from 'app/entities/certificate-authority';

@Component({
    selector: '-certificate-template-update',
    templateUrl: './certificate-template-update.component.html'
})
export class CertificateTemplateUpdateComponent implements OnInit {
    private _certificateTemplate: ICertificateTemplate;
    isSaving: boolean;

    certificateauthorities: ICertificateAuthority[];

    constructor(
        private jhiAlertService: JhiAlertService,
        private certificateTemplateService: CertificateTemplateService,
        private certificateAuthorityService: CertificateAuthorityService,
        private activatedRoute: ActivatedRoute
    ) {}

    ngOnInit() {
        this.isSaving = false;
        this.activatedRoute.data.subscribe(({ certificateTemplate }) => {
            this.certificateTemplate = certificateTemplate;
        });
        this.certificateAuthorityService.query().subscribe(
            (res: HttpResponse<ICertificateAuthority[]>) => {
                this.certificateauthorities = res.body;
            },
            (res: HttpErrorResponse) => this.onError(res.message)
        );
    }

    previousState() {
        window.history.back();
    }

    save() {
        this.isSaving = true;
        if (this.certificateTemplate.id !== undefined) {
            this.subscribeToSaveResponse(this.certificateTemplateService.update(this.certificateTemplate));
        } else {
            this.subscribeToSaveResponse(this.certificateTemplateService.create(this.certificateTemplate));
        }
    }

    private subscribeToSaveResponse(result: Observable<HttpResponse<ICertificateTemplate>>) {
        result.subscribe((res: HttpResponse<ICertificateTemplate>) => this.onSaveSuccess(), (res: HttpErrorResponse) => this.onSaveError());
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
    get certificateTemplate() {
        return this._certificateTemplate;
    }

    set certificateTemplate(certificateTemplate: ICertificateTemplate) {
        this._certificateTemplate = certificateTemplate;
    }
}
