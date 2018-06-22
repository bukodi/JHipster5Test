import { Injectable } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { Resolve, ActivatedRouteSnapshot, RouterStateSnapshot, Routes } from '@angular/router';
import { JhiPaginationUtil, JhiResolvePagingParams } from 'ng-jhipster';
import { UserRouteAccessService } from 'app/core';
import { Observable } from 'rxjs';
import { CustomProcess } from 'app/shared/model/custom-process.model';
import { CustomProcessService } from './custom-process.service';
import { CustomProcessComponent } from './custom-process.component';
import { CustomProcessDetailComponent } from './custom-process-detail.component';
import { CustomProcessUpdateComponent } from './custom-process-update.component';
import { CustomProcessDeletePopupComponent } from './custom-process-delete-dialog.component';
import { ICustomProcess } from 'app/shared/model/custom-process.model';

@Injectable({ providedIn: 'root' })
export class CustomProcessResolve implements Resolve<ICustomProcess> {
    constructor(private service: CustomProcessService) {}

    resolve(route: ActivatedRouteSnapshot, state: RouterStateSnapshot) {
        const id = route.params['id'] ? route.params['id'] : null;
        if (id) {
            return this.service.find(id).map((customProcess: HttpResponse<CustomProcess>) => customProcess.body);
        }
        return Observable.of(new CustomProcess());
    }
}

export const customProcessRoute: Routes = [
    {
        path: 'custom-process',
        component: CustomProcessComponent,
        resolve: {
            pagingParams: JhiResolvePagingParams
        },
        data: {
            authorities: ['ROLE_USER'],
            defaultSort: 'id,asc',
            pageTitle: 'jHipster5TestApp.customProcess.home.title'
        },
        canActivate: [UserRouteAccessService]
    },
    {
        path: 'custom-process/:id/view',
        component: CustomProcessDetailComponent,
        resolve: {
            customProcess: CustomProcessResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'jHipster5TestApp.customProcess.home.title'
        },
        canActivate: [UserRouteAccessService]
    },
    {
        path: 'custom-process/new',
        component: CustomProcessUpdateComponent,
        resolve: {
            customProcess: CustomProcessResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'jHipster5TestApp.customProcess.home.title'
        },
        canActivate: [UserRouteAccessService]
    },
    {
        path: 'custom-process/:id/edit',
        component: CustomProcessUpdateComponent,
        resolve: {
            customProcess: CustomProcessResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'jHipster5TestApp.customProcess.home.title'
        },
        canActivate: [UserRouteAccessService]
    }
];

export const customProcessPopupRoute: Routes = [
    {
        path: 'custom-process/:id/delete',
        component: CustomProcessDeletePopupComponent,
        resolve: {
            customProcess: CustomProcessResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'jHipster5TestApp.customProcess.home.title'
        },
        canActivate: [UserRouteAccessService],
        outlet: 'popup'
    }
];
