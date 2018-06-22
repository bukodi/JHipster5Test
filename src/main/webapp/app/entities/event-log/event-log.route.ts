import { Injectable } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { Resolve, ActivatedRouteSnapshot, RouterStateSnapshot, Routes } from '@angular/router';
import { JhiPaginationUtil, JhiResolvePagingParams } from 'ng-jhipster';
import { UserRouteAccessService } from 'app/core';
import { Observable } from 'rxjs';
import { EventLog } from 'app/shared/model/event-log.model';
import { EventLogService } from './event-log.service';
import { EventLogComponent } from './event-log.component';
import { EventLogDetailComponent } from './event-log-detail.component';
import { EventLogUpdateComponent } from './event-log-update.component';
import { EventLogDeletePopupComponent } from './event-log-delete-dialog.component';
import { IEventLog } from 'app/shared/model/event-log.model';

@Injectable({ providedIn: 'root' })
export class EventLogResolve implements Resolve<IEventLog> {
    constructor(private service: EventLogService) {}

    resolve(route: ActivatedRouteSnapshot, state: RouterStateSnapshot) {
        const id = route.params['id'] ? route.params['id'] : null;
        if (id) {
            return this.service.find(id).map((eventLog: HttpResponse<EventLog>) => eventLog.body);
        }
        return Observable.of(new EventLog());
    }
}

export const eventLogRoute: Routes = [
    {
        path: 'event-log',
        component: EventLogComponent,
        resolve: {
            pagingParams: JhiResolvePagingParams
        },
        data: {
            authorities: ['ROLE_USER'],
            defaultSort: 'id,asc',
            pageTitle: 'jHipster5TestApp.eventLog.home.title'
        },
        canActivate: [UserRouteAccessService]
    },
    {
        path: 'event-log/:id/view',
        component: EventLogDetailComponent,
        resolve: {
            eventLog: EventLogResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'jHipster5TestApp.eventLog.home.title'
        },
        canActivate: [UserRouteAccessService]
    },
    {
        path: 'event-log/new',
        component: EventLogUpdateComponent,
        resolve: {
            eventLog: EventLogResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'jHipster5TestApp.eventLog.home.title'
        },
        canActivate: [UserRouteAccessService]
    },
    {
        path: 'event-log/:id/edit',
        component: EventLogUpdateComponent,
        resolve: {
            eventLog: EventLogResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'jHipster5TestApp.eventLog.home.title'
        },
        canActivate: [UserRouteAccessService]
    }
];

export const eventLogPopupRoute: Routes = [
    {
        path: 'event-log/:id/delete',
        component: EventLogDeletePopupComponent,
        resolve: {
            eventLog: EventLogResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'jHipster5TestApp.eventLog.home.title'
        },
        canActivate: [UserRouteAccessService],
        outlet: 'popup'
    }
];
