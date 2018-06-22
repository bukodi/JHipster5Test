import { NgModule, CUSTOM_ELEMENTS_SCHEMA } from '@angular/core';
import { RouterModule } from '@angular/router';

import { JHipster5TestSharedModule } from 'app/shared';
import {
    CertificateComponent,
    CertificateDetailComponent,
    CertificateUpdateComponent,
    CertificateDeletePopupComponent,
    CertificateDeleteDialogComponent,
    certificateRoute,
    certificatePopupRoute
} from './';

const ENTITY_STATES = [...certificateRoute, ...certificatePopupRoute];

@NgModule({
    imports: [JHipster5TestSharedModule, RouterModule.forChild(ENTITY_STATES)],
    declarations: [
        CertificateComponent,
        CertificateDetailComponent,
        CertificateUpdateComponent,
        CertificateDeleteDialogComponent,
        CertificateDeletePopupComponent
    ],
    entryComponents: [CertificateComponent, CertificateUpdateComponent, CertificateDeleteDialogComponent, CertificateDeletePopupComponent],
    schemas: [CUSTOM_ELEMENTS_SCHEMA]
})
export class JHipster5TestCertificateModule {}
