import { NgModule, CUSTOM_ELEMENTS_SCHEMA } from '@angular/core';
import { RouterModule } from '@angular/router';

import { JHipster5TestSharedModule } from 'app/shared';
import {
    DirectoryServerComponent,
    DirectoryServerDetailComponent,
    DirectoryServerUpdateComponent,
    DirectoryServerDeletePopupComponent,
    DirectoryServerDeleteDialogComponent,
    directoryServerRoute,
    directoryServerPopupRoute
} from './';

const ENTITY_STATES = [...directoryServerRoute, ...directoryServerPopupRoute];

@NgModule({
    imports: [JHipster5TestSharedModule, RouterModule.forChild(ENTITY_STATES)],
    declarations: [
        DirectoryServerComponent,
        DirectoryServerDetailComponent,
        DirectoryServerUpdateComponent,
        DirectoryServerDeleteDialogComponent,
        DirectoryServerDeletePopupComponent
    ],
    entryComponents: [
        DirectoryServerComponent,
        DirectoryServerUpdateComponent,
        DirectoryServerDeleteDialogComponent,
        DirectoryServerDeletePopupComponent
    ],
    schemas: [CUSTOM_ELEMENTS_SCHEMA]
})
export class JHipster5TestDirectoryServerModule {}
