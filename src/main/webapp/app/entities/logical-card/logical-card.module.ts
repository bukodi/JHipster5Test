import { NgModule, CUSTOM_ELEMENTS_SCHEMA } from '@angular/core';
import { RouterModule } from '@angular/router';

import { JHipster5TestSharedModule } from 'app/shared';
import {
    LogicalCardComponent,
    LogicalCardDetailComponent,
    LogicalCardUpdateComponent,
    LogicalCardDeletePopupComponent,
    LogicalCardDeleteDialogComponent,
    logicalCardRoute,
    logicalCardPopupRoute
} from './';

const ENTITY_STATES = [...logicalCardRoute, ...logicalCardPopupRoute];

@NgModule({
    imports: [JHipster5TestSharedModule, RouterModule.forChild(ENTITY_STATES)],
    declarations: [
        LogicalCardComponent,
        LogicalCardDetailComponent,
        LogicalCardUpdateComponent,
        LogicalCardDeleteDialogComponent,
        LogicalCardDeletePopupComponent
    ],
    entryComponents: [LogicalCardComponent, LogicalCardUpdateComponent, LogicalCardDeleteDialogComponent, LogicalCardDeletePopupComponent],
    schemas: [CUSTOM_ELEMENTS_SCHEMA]
})
export class JHipster5TestLogicalCardModule {}
