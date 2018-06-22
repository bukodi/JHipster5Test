import { NgModule, CUSTOM_ELEMENTS_SCHEMA } from '@angular/core';
import { RouterModule } from '@angular/router';

import { JHipster5TestSharedModule } from 'app/shared';
import {
    IdentityComponent,
    IdentityDetailComponent,
    IdentityUpdateComponent,
    IdentityDeletePopupComponent,
    IdentityDeleteDialogComponent,
    identityRoute,
    identityPopupRoute
} from './';

const ENTITY_STATES = [...identityRoute, ...identityPopupRoute];

@NgModule({
    imports: [JHipster5TestSharedModule, RouterModule.forChild(ENTITY_STATES)],
    declarations: [
        IdentityComponent,
        IdentityDetailComponent,
        IdentityUpdateComponent,
        IdentityDeleteDialogComponent,
        IdentityDeletePopupComponent
    ],
    entryComponents: [IdentityComponent, IdentityUpdateComponent, IdentityDeleteDialogComponent, IdentityDeletePopupComponent],
    schemas: [CUSTOM_ELEMENTS_SCHEMA]
})
export class JHipster5TestIdentityModule {}
