import { NgModule, CUSTOM_ELEMENTS_SCHEMA } from '@angular/core';
import { RouterModule } from '@angular/router';

import { JHipster5TestSharedModule } from 'app/shared';
import {
    CustomProcessComponent,
    CustomProcessDetailComponent,
    CustomProcessUpdateComponent,
    CustomProcessDeletePopupComponent,
    CustomProcessDeleteDialogComponent,
    customProcessRoute,
    customProcessPopupRoute
} from './';

const ENTITY_STATES = [...customProcessRoute, ...customProcessPopupRoute];

@NgModule({
    imports: [JHipster5TestSharedModule, RouterModule.forChild(ENTITY_STATES)],
    declarations: [
        CustomProcessComponent,
        CustomProcessDetailComponent,
        CustomProcessUpdateComponent,
        CustomProcessDeleteDialogComponent,
        CustomProcessDeletePopupComponent
    ],
    entryComponents: [
        CustomProcessComponent,
        CustomProcessUpdateComponent,
        CustomProcessDeleteDialogComponent,
        CustomProcessDeletePopupComponent
    ],
    schemas: [CUSTOM_ELEMENTS_SCHEMA]
})
export class JHipster5TestCustomProcessModule {}
