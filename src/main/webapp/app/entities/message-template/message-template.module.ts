import { NgModule, CUSTOM_ELEMENTS_SCHEMA } from '@angular/core';
import { RouterModule } from '@angular/router';

import { JHipster5TestSharedModule } from 'app/shared';
import {
    MessageTemplateComponent,
    MessageTemplateDetailComponent,
    MessageTemplateUpdateComponent,
    MessageTemplateDeletePopupComponent,
    MessageTemplateDeleteDialogComponent,
    messageTemplateRoute,
    messageTemplatePopupRoute
} from './';

const ENTITY_STATES = [...messageTemplateRoute, ...messageTemplatePopupRoute];

@NgModule({
    imports: [JHipster5TestSharedModule, RouterModule.forChild(ENTITY_STATES)],
    declarations: [
        MessageTemplateComponent,
        MessageTemplateDetailComponent,
        MessageTemplateUpdateComponent,
        MessageTemplateDeleteDialogComponent,
        MessageTemplateDeletePopupComponent
    ],
    entryComponents: [
        MessageTemplateComponent,
        MessageTemplateUpdateComponent,
        MessageTemplateDeleteDialogComponent,
        MessageTemplateDeletePopupComponent
    ],
    schemas: [CUSTOM_ELEMENTS_SCHEMA]
})
export class JHipster5TestMessageTemplateModule {}
