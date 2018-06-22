import { NgModule, CUSTOM_ELEMENTS_SCHEMA } from '@angular/core';
import { RouterModule } from '@angular/router';

import { JHipster5TestSharedModule } from 'app/shared';
import {
    EventLogComponent,
    EventLogDetailComponent,
    EventLogUpdateComponent,
    EventLogDeletePopupComponent,
    EventLogDeleteDialogComponent,
    eventLogRoute,
    eventLogPopupRoute
} from './';

const ENTITY_STATES = [...eventLogRoute, ...eventLogPopupRoute];

@NgModule({
    imports: [JHipster5TestSharedModule, RouterModule.forChild(ENTITY_STATES)],
    declarations: [
        EventLogComponent,
        EventLogDetailComponent,
        EventLogUpdateComponent,
        EventLogDeleteDialogComponent,
        EventLogDeletePopupComponent
    ],
    entryComponents: [EventLogComponent, EventLogUpdateComponent, EventLogDeleteDialogComponent, EventLogDeletePopupComponent],
    schemas: [CUSTOM_ELEMENTS_SCHEMA]
})
export class JHipster5TestEventLogModule {}
