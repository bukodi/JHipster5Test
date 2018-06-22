import { NgModule, CUSTOM_ELEMENTS_SCHEMA } from '@angular/core';
import { RouterModule } from '@angular/router';

import { JHipster5TestSharedModule } from 'app/shared';
import {
    CertificateAuthorityComponent,
    CertificateAuthorityDetailComponent,
    CertificateAuthorityUpdateComponent,
    CertificateAuthorityDeletePopupComponent,
    CertificateAuthorityDeleteDialogComponent,
    certificateAuthorityRoute,
    certificateAuthorityPopupRoute
} from './';

const ENTITY_STATES = [...certificateAuthorityRoute, ...certificateAuthorityPopupRoute];

@NgModule({
    imports: [JHipster5TestSharedModule, RouterModule.forChild(ENTITY_STATES)],
    declarations: [
        CertificateAuthorityComponent,
        CertificateAuthorityDetailComponent,
        CertificateAuthorityUpdateComponent,
        CertificateAuthorityDeleteDialogComponent,
        CertificateAuthorityDeletePopupComponent
    ],
    entryComponents: [
        CertificateAuthorityComponent,
        CertificateAuthorityUpdateComponent,
        CertificateAuthorityDeleteDialogComponent,
        CertificateAuthorityDeletePopupComponent
    ],
    schemas: [CUSTOM_ELEMENTS_SCHEMA]
})
export class JHipster5TestCertificateAuthorityModule {}
