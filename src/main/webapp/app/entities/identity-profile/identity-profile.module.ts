import { NgModule, CUSTOM_ELEMENTS_SCHEMA } from '@angular/core';
import { RouterModule } from '@angular/router';

import { JHipster5TestSharedModule } from 'app/shared';
import {
    IdentityProfileComponent,
    IdentityProfileDetailComponent,
    IdentityProfileUpdateComponent,
    IdentityProfileDeletePopupComponent,
    IdentityProfileDeleteDialogComponent,
    identityProfileRoute,
    identityProfilePopupRoute
} from './';

const ENTITY_STATES = [...identityProfileRoute, ...identityProfilePopupRoute];

@NgModule({
    imports: [JHipster5TestSharedModule, RouterModule.forChild(ENTITY_STATES)],
    declarations: [
        IdentityProfileComponent,
        IdentityProfileDetailComponent,
        IdentityProfileUpdateComponent,
        IdentityProfileDeleteDialogComponent,
        IdentityProfileDeletePopupComponent
    ],
    entryComponents: [
        IdentityProfileComponent,
        IdentityProfileUpdateComponent,
        IdentityProfileDeleteDialogComponent,
        IdentityProfileDeletePopupComponent
    ],
    schemas: [CUSTOM_ELEMENTS_SCHEMA]
})
export class JHipster5TestIdentityProfileModule {}
