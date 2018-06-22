import { NgModule, CUSTOM_ELEMENTS_SCHEMA } from '@angular/core';
import { RouterModule } from '@angular/router';

import { JHipster5TestSharedModule } from 'app/shared';
import {
    CardTypeComponent,
    CardTypeDetailComponent,
    CardTypeUpdateComponent,
    CardTypeDeletePopupComponent,
    CardTypeDeleteDialogComponent,
    cardTypeRoute,
    cardTypePopupRoute
} from './';

const ENTITY_STATES = [...cardTypeRoute, ...cardTypePopupRoute];

@NgModule({
    imports: [JHipster5TestSharedModule, RouterModule.forChild(ENTITY_STATES)],
    declarations: [
        CardTypeComponent,
        CardTypeDetailComponent,
        CardTypeUpdateComponent,
        CardTypeDeleteDialogComponent,
        CardTypeDeletePopupComponent
    ],
    entryComponents: [CardTypeComponent, CardTypeUpdateComponent, CardTypeDeleteDialogComponent, CardTypeDeletePopupComponent],
    schemas: [CUSTOM_ELEMENTS_SCHEMA]
})
export class JHipster5TestCardTypeModule {}
