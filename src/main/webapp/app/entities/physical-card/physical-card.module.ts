import { NgModule, CUSTOM_ELEMENTS_SCHEMA } from '@angular/core';
import { RouterModule } from '@angular/router';

import { JHipster5TestSharedModule } from 'app/shared';
import {
    PhysicalCardComponent,
    PhysicalCardDetailComponent,
    PhysicalCardUpdateComponent,
    PhysicalCardDeletePopupComponent,
    PhysicalCardDeleteDialogComponent,
    physicalCardRoute,
    physicalCardPopupRoute
} from './';

const ENTITY_STATES = [...physicalCardRoute, ...physicalCardPopupRoute];

@NgModule({
    imports: [JHipster5TestSharedModule, RouterModule.forChild(ENTITY_STATES)],
    declarations: [
        PhysicalCardComponent,
        PhysicalCardDetailComponent,
        PhysicalCardUpdateComponent,
        PhysicalCardDeleteDialogComponent,
        PhysicalCardDeletePopupComponent
    ],
    entryComponents: [
        PhysicalCardComponent,
        PhysicalCardUpdateComponent,
        PhysicalCardDeleteDialogComponent,
        PhysicalCardDeletePopupComponent
    ],
    schemas: [CUSTOM_ELEMENTS_SCHEMA]
})
export class JHipster5TestPhysicalCardModule {}
