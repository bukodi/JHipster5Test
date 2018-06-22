import { NgModule, CUSTOM_ELEMENTS_SCHEMA } from '@angular/core';
import { RouterModule } from '@angular/router';

import { JHipster5TestSharedModule } from 'app/shared';
import {
    CertificateTemplateComponent,
    CertificateTemplateDetailComponent,
    CertificateTemplateUpdateComponent,
    CertificateTemplateDeletePopupComponent,
    CertificateTemplateDeleteDialogComponent,
    certificateTemplateRoute,
    certificateTemplatePopupRoute
} from './';

const ENTITY_STATES = [...certificateTemplateRoute, ...certificateTemplatePopupRoute];

@NgModule({
    imports: [JHipster5TestSharedModule, RouterModule.forChild(ENTITY_STATES)],
    declarations: [
        CertificateTemplateComponent,
        CertificateTemplateDetailComponent,
        CertificateTemplateUpdateComponent,
        CertificateTemplateDeleteDialogComponent,
        CertificateTemplateDeletePopupComponent
    ],
    entryComponents: [
        CertificateTemplateComponent,
        CertificateTemplateUpdateComponent,
        CertificateTemplateDeleteDialogComponent,
        CertificateTemplateDeletePopupComponent
    ],
    schemas: [CUSTOM_ELEMENTS_SCHEMA]
})
export class JHipster5TestCertificateTemplateModule {}
