import { Injectable } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { Resolve, ActivatedRouteSnapshot, RouterStateSnapshot, Routes } from '@angular/router';
import { JhiPaginationUtil, JhiResolvePagingParams } from 'ng-jhipster';
import { UserRouteAccessService } from 'app/core';
import { Observable } from 'rxjs';
import { CardType } from 'app/shared/model/card-type.model';
import { CardTypeService } from './card-type.service';
import { CardTypeComponent } from './card-type.component';
import { CardTypeDetailComponent } from './card-type-detail.component';
import { CardTypeUpdateComponent } from './card-type-update.component';
import { CardTypeDeletePopupComponent } from './card-type-delete-dialog.component';
import { ICardType } from 'app/shared/model/card-type.model';

@Injectable({ providedIn: 'root' })
export class CardTypeResolve implements Resolve<ICardType> {
    constructor(private service: CardTypeService) {}

    resolve(route: ActivatedRouteSnapshot, state: RouterStateSnapshot) {
        const id = route.params['id'] ? route.params['id'] : null;
        if (id) {
            return this.service.find(id).map((cardType: HttpResponse<CardType>) => cardType.body);
        }
        return Observable.of(new CardType());
    }
}

export const cardTypeRoute: Routes = [
    {
        path: 'card-type',
        component: CardTypeComponent,
        resolve: {
            pagingParams: JhiResolvePagingParams
        },
        data: {
            authorities: ['ROLE_USER'],
            defaultSort: 'id,asc',
            pageTitle: 'jHipster5TestApp.cardType.home.title'
        },
        canActivate: [UserRouteAccessService]
    },
    {
        path: 'card-type/:id/view',
        component: CardTypeDetailComponent,
        resolve: {
            cardType: CardTypeResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'jHipster5TestApp.cardType.home.title'
        },
        canActivate: [UserRouteAccessService]
    },
    {
        path: 'card-type/new',
        component: CardTypeUpdateComponent,
        resolve: {
            cardType: CardTypeResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'jHipster5TestApp.cardType.home.title'
        },
        canActivate: [UserRouteAccessService]
    },
    {
        path: 'card-type/:id/edit',
        component: CardTypeUpdateComponent,
        resolve: {
            cardType: CardTypeResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'jHipster5TestApp.cardType.home.title'
        },
        canActivate: [UserRouteAccessService]
    }
];

export const cardTypePopupRoute: Routes = [
    {
        path: 'card-type/:id/delete',
        component: CardTypeDeletePopupComponent,
        resolve: {
            cardType: CardTypeResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'jHipster5TestApp.cardType.home.title'
        },
        canActivate: [UserRouteAccessService],
        outlet: 'popup'
    }
];
