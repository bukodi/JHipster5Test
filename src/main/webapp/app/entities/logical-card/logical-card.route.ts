import { Injectable } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { Resolve, ActivatedRouteSnapshot, RouterStateSnapshot, Routes } from '@angular/router';
import { JhiPaginationUtil, JhiResolvePagingParams } from 'ng-jhipster';
import { UserRouteAccessService } from 'app/core';
import { Observable } from 'rxjs';
import { LogicalCard } from 'app/shared/model/logical-card.model';
import { LogicalCardService } from './logical-card.service';
import { LogicalCardComponent } from './logical-card.component';
import { LogicalCardDetailComponent } from './logical-card-detail.component';
import { LogicalCardUpdateComponent } from './logical-card-update.component';
import { LogicalCardDeletePopupComponent } from './logical-card-delete-dialog.component';
import { ILogicalCard } from 'app/shared/model/logical-card.model';

@Injectable({ providedIn: 'root' })
export class LogicalCardResolve implements Resolve<ILogicalCard> {
    constructor(private service: LogicalCardService) {}

    resolve(route: ActivatedRouteSnapshot, state: RouterStateSnapshot) {
        const id = route.params['id'] ? route.params['id'] : null;
        if (id) {
            return this.service.find(id).map((logicalCard: HttpResponse<LogicalCard>) => logicalCard.body);
        }
        return Observable.of(new LogicalCard());
    }
}

export const logicalCardRoute: Routes = [
    {
        path: 'logical-card',
        component: LogicalCardComponent,
        resolve: {
            pagingParams: JhiResolvePagingParams
        },
        data: {
            authorities: ['ROLE_USER'],
            defaultSort: 'id,asc',
            pageTitle: 'jHipster5TestApp.logicalCard.home.title'
        },
        canActivate: [UserRouteAccessService]
    },
    {
        path: 'logical-card/:id/view',
        component: LogicalCardDetailComponent,
        resolve: {
            logicalCard: LogicalCardResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'jHipster5TestApp.logicalCard.home.title'
        },
        canActivate: [UserRouteAccessService]
    },
    {
        path: 'logical-card/new',
        component: LogicalCardUpdateComponent,
        resolve: {
            logicalCard: LogicalCardResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'jHipster5TestApp.logicalCard.home.title'
        },
        canActivate: [UserRouteAccessService]
    },
    {
        path: 'logical-card/:id/edit',
        component: LogicalCardUpdateComponent,
        resolve: {
            logicalCard: LogicalCardResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'jHipster5TestApp.logicalCard.home.title'
        },
        canActivate: [UserRouteAccessService]
    }
];

export const logicalCardPopupRoute: Routes = [
    {
        path: 'logical-card/:id/delete',
        component: LogicalCardDeletePopupComponent,
        resolve: {
            logicalCard: LogicalCardResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'jHipster5TestApp.logicalCard.home.title'
        },
        canActivate: [UserRouteAccessService],
        outlet: 'popup'
    }
];
