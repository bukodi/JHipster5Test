import { Injectable } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { Resolve, ActivatedRouteSnapshot, RouterStateSnapshot, Routes } from '@angular/router';
import { JhiPaginationUtil, JhiResolvePagingParams } from 'ng-jhipster';
import { UserRouteAccessService } from 'app/core';
import { Observable } from 'rxjs';
import { Identity } from 'app/shared/model/identity.model';
import { IdentityService } from './identity.service';
import { IdentityComponent } from './identity.component';
import { IdentityDetailComponent } from './identity-detail.component';
import { IdentityUpdateComponent } from './identity-update.component';
import { IdentityDeletePopupComponent } from './identity-delete-dialog.component';
import { IIdentity } from 'app/shared/model/identity.model';

@Injectable({ providedIn: 'root' })
export class IdentityResolve implements Resolve<IIdentity> {
    constructor(private service: IdentityService) {}

    resolve(route: ActivatedRouteSnapshot, state: RouterStateSnapshot) {
        const id = route.params['id'] ? route.params['id'] : null;
        if (id) {
            return this.service.find(id).map((identity: HttpResponse<Identity>) => identity.body);
        }
        return Observable.of(new Identity());
    }
}

export const identityRoute: Routes = [
    {
        path: 'identity',
        component: IdentityComponent,
        resolve: {
            pagingParams: JhiResolvePagingParams
        },
        data: {
            authorities: ['ROLE_USER'],
            defaultSort: 'id,asc',
            pageTitle: 'jHipster5TestApp.identity.home.title'
        },
        canActivate: [UserRouteAccessService]
    },
    {
        path: 'identity/:id/view',
        component: IdentityDetailComponent,
        resolve: {
            identity: IdentityResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'jHipster5TestApp.identity.home.title'
        },
        canActivate: [UserRouteAccessService]
    },
    {
        path: 'identity/new',
        component: IdentityUpdateComponent,
        resolve: {
            identity: IdentityResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'jHipster5TestApp.identity.home.title'
        },
        canActivate: [UserRouteAccessService]
    },
    {
        path: 'identity/:id/edit',
        component: IdentityUpdateComponent,
        resolve: {
            identity: IdentityResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'jHipster5TestApp.identity.home.title'
        },
        canActivate: [UserRouteAccessService]
    }
];

export const identityPopupRoute: Routes = [
    {
        path: 'identity/:id/delete',
        component: IdentityDeletePopupComponent,
        resolve: {
            identity: IdentityResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'jHipster5TestApp.identity.home.title'
        },
        canActivate: [UserRouteAccessService],
        outlet: 'popup'
    }
];
