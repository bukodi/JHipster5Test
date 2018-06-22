import { Injectable } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { Resolve, ActivatedRouteSnapshot, RouterStateSnapshot, Routes } from '@angular/router';
import { JhiPaginationUtil, JhiResolvePagingParams } from 'ng-jhipster';
import { UserRouteAccessService } from 'app/core';
import { Observable } from 'rxjs';
import { IdentityProfile } from 'app/shared/model/identity-profile.model';
import { IdentityProfileService } from './identity-profile.service';
import { IdentityProfileComponent } from './identity-profile.component';
import { IdentityProfileDetailComponent } from './identity-profile-detail.component';
import { IdentityProfileUpdateComponent } from './identity-profile-update.component';
import { IdentityProfileDeletePopupComponent } from './identity-profile-delete-dialog.component';
import { IIdentityProfile } from 'app/shared/model/identity-profile.model';

@Injectable({ providedIn: 'root' })
export class IdentityProfileResolve implements Resolve<IIdentityProfile> {
    constructor(private service: IdentityProfileService) {}

    resolve(route: ActivatedRouteSnapshot, state: RouterStateSnapshot) {
        const id = route.params['id'] ? route.params['id'] : null;
        if (id) {
            return this.service.find(id).map((identityProfile: HttpResponse<IdentityProfile>) => identityProfile.body);
        }
        return Observable.of(new IdentityProfile());
    }
}

export const identityProfileRoute: Routes = [
    {
        path: 'identity-profile',
        component: IdentityProfileComponent,
        resolve: {
            pagingParams: JhiResolvePagingParams
        },
        data: {
            authorities: ['ROLE_USER'],
            defaultSort: 'id,asc',
            pageTitle: 'jHipster5TestApp.identityProfile.home.title'
        },
        canActivate: [UserRouteAccessService]
    },
    {
        path: 'identity-profile/:id/view',
        component: IdentityProfileDetailComponent,
        resolve: {
            identityProfile: IdentityProfileResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'jHipster5TestApp.identityProfile.home.title'
        },
        canActivate: [UserRouteAccessService]
    },
    {
        path: 'identity-profile/new',
        component: IdentityProfileUpdateComponent,
        resolve: {
            identityProfile: IdentityProfileResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'jHipster5TestApp.identityProfile.home.title'
        },
        canActivate: [UserRouteAccessService]
    },
    {
        path: 'identity-profile/:id/edit',
        component: IdentityProfileUpdateComponent,
        resolve: {
            identityProfile: IdentityProfileResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'jHipster5TestApp.identityProfile.home.title'
        },
        canActivate: [UserRouteAccessService]
    }
];

export const identityProfilePopupRoute: Routes = [
    {
        path: 'identity-profile/:id/delete',
        component: IdentityProfileDeletePopupComponent,
        resolve: {
            identityProfile: IdentityProfileResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'jHipster5TestApp.identityProfile.home.title'
        },
        canActivate: [UserRouteAccessService],
        outlet: 'popup'
    }
];
