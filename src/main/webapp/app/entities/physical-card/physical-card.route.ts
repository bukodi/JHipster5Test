import { Injectable } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { Resolve, ActivatedRouteSnapshot, RouterStateSnapshot, Routes } from '@angular/router';
import { JhiPaginationUtil, JhiResolvePagingParams } from 'ng-jhipster';
import { UserRouteAccessService } from 'app/core';
import { Observable } from 'rxjs';
import { PhysicalCard } from 'app/shared/model/physical-card.model';
import { PhysicalCardService } from './physical-card.service';
import { PhysicalCardComponent } from './physical-card.component';
import { PhysicalCardDetailComponent } from './physical-card-detail.component';
import { PhysicalCardUpdateComponent } from './physical-card-update.component';
import { PhysicalCardDeletePopupComponent } from './physical-card-delete-dialog.component';
import { IPhysicalCard } from 'app/shared/model/physical-card.model';

@Injectable({ providedIn: 'root' })
export class PhysicalCardResolve implements Resolve<IPhysicalCard> {
    constructor(private service: PhysicalCardService) {}

    resolve(route: ActivatedRouteSnapshot, state: RouterStateSnapshot) {
        const id = route.params['id'] ? route.params['id'] : null;
        if (id) {
            return this.service.find(id).map((physicalCard: HttpResponse<PhysicalCard>) => physicalCard.body);
        }
        return Observable.of(new PhysicalCard());
    }
}

export const physicalCardRoute: Routes = [
    {
        path: 'physical-card',
        component: PhysicalCardComponent,
        resolve: {
            pagingParams: JhiResolvePagingParams
        },
        data: {
            authorities: ['ROLE_USER'],
            defaultSort: 'id,asc',
            pageTitle: 'jHipster5TestApp.physicalCard.home.title'
        },
        canActivate: [UserRouteAccessService]
    },
    {
        path: 'physical-card/:id/view',
        component: PhysicalCardDetailComponent,
        resolve: {
            physicalCard: PhysicalCardResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'jHipster5TestApp.physicalCard.home.title'
        },
        canActivate: [UserRouteAccessService]
    },
    {
        path: 'physical-card/new',
        component: PhysicalCardUpdateComponent,
        resolve: {
            physicalCard: PhysicalCardResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'jHipster5TestApp.physicalCard.home.title'
        },
        canActivate: [UserRouteAccessService]
    },
    {
        path: 'physical-card/:id/edit',
        component: PhysicalCardUpdateComponent,
        resolve: {
            physicalCard: PhysicalCardResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'jHipster5TestApp.physicalCard.home.title'
        },
        canActivate: [UserRouteAccessService]
    }
];

export const physicalCardPopupRoute: Routes = [
    {
        path: 'physical-card/:id/delete',
        component: PhysicalCardDeletePopupComponent,
        resolve: {
            physicalCard: PhysicalCardResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'jHipster5TestApp.physicalCard.home.title'
        },
        canActivate: [UserRouteAccessService],
        outlet: 'popup'
    }
];
