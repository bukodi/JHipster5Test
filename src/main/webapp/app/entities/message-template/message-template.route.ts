import { Injectable } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { Resolve, ActivatedRouteSnapshot, RouterStateSnapshot, Routes } from '@angular/router';
import { JhiPaginationUtil, JhiResolvePagingParams } from 'ng-jhipster';
import { UserRouteAccessService } from 'app/core';
import { Observable } from 'rxjs';
import { MessageTemplate } from 'app/shared/model/message-template.model';
import { MessageTemplateService } from './message-template.service';
import { MessageTemplateComponent } from './message-template.component';
import { MessageTemplateDetailComponent } from './message-template-detail.component';
import { MessageTemplateUpdateComponent } from './message-template-update.component';
import { MessageTemplateDeletePopupComponent } from './message-template-delete-dialog.component';
import { IMessageTemplate } from 'app/shared/model/message-template.model';

@Injectable({ providedIn: 'root' })
export class MessageTemplateResolve implements Resolve<IMessageTemplate> {
    constructor(private service: MessageTemplateService) {}

    resolve(route: ActivatedRouteSnapshot, state: RouterStateSnapshot) {
        const id = route.params['id'] ? route.params['id'] : null;
        if (id) {
            return this.service.find(id).map((messageTemplate: HttpResponse<MessageTemplate>) => messageTemplate.body);
        }
        return Observable.of(new MessageTemplate());
    }
}

export const messageTemplateRoute: Routes = [
    {
        path: 'message-template',
        component: MessageTemplateComponent,
        resolve: {
            pagingParams: JhiResolvePagingParams
        },
        data: {
            authorities: ['ROLE_USER'],
            defaultSort: 'id,asc',
            pageTitle: 'jHipster5TestApp.messageTemplate.home.title'
        },
        canActivate: [UserRouteAccessService]
    },
    {
        path: 'message-template/:id/view',
        component: MessageTemplateDetailComponent,
        resolve: {
            messageTemplate: MessageTemplateResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'jHipster5TestApp.messageTemplate.home.title'
        },
        canActivate: [UserRouteAccessService]
    },
    {
        path: 'message-template/new',
        component: MessageTemplateUpdateComponent,
        resolve: {
            messageTemplate: MessageTemplateResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'jHipster5TestApp.messageTemplate.home.title'
        },
        canActivate: [UserRouteAccessService]
    },
    {
        path: 'message-template/:id/edit',
        component: MessageTemplateUpdateComponent,
        resolve: {
            messageTemplate: MessageTemplateResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'jHipster5TestApp.messageTemplate.home.title'
        },
        canActivate: [UserRouteAccessService]
    }
];

export const messageTemplatePopupRoute: Routes = [
    {
        path: 'message-template/:id/delete',
        component: MessageTemplateDeletePopupComponent,
        resolve: {
            messageTemplate: MessageTemplateResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'jHipster5TestApp.messageTemplate.home.title'
        },
        canActivate: [UserRouteAccessService],
        outlet: 'popup'
    }
];
