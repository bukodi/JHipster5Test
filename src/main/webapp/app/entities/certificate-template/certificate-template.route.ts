import { Injectable } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { Resolve, ActivatedRouteSnapshot, RouterStateSnapshot, Routes } from '@angular/router';
import { JhiPaginationUtil, JhiResolvePagingParams } from 'ng-jhipster';
import { UserRouteAccessService } from 'app/core';
import { Observable } from 'rxjs';
import { CertificateTemplate } from 'app/shared/model/certificate-template.model';
import { CertificateTemplateService } from './certificate-template.service';
import { CertificateTemplateComponent } from './certificate-template.component';
import { CertificateTemplateDetailComponent } from './certificate-template-detail.component';
import { CertificateTemplateUpdateComponent } from './certificate-template-update.component';
import { CertificateTemplateDeletePopupComponent } from './certificate-template-delete-dialog.component';
import { ICertificateTemplate } from 'app/shared/model/certificate-template.model';

@Injectable({ providedIn: 'root' })
export class CertificateTemplateResolve implements Resolve<ICertificateTemplate> {
    constructor(private service: CertificateTemplateService) {}

    resolve(route: ActivatedRouteSnapshot, state: RouterStateSnapshot) {
        const id = route.params['id'] ? route.params['id'] : null;
        if (id) {
            return this.service.find(id).map((certificateTemplate: HttpResponse<CertificateTemplate>) => certificateTemplate.body);
        }
        return Observable.of(new CertificateTemplate());
    }
}

export const certificateTemplateRoute: Routes = [
    {
        path: 'certificate-template',
        component: CertificateTemplateComponent,
        resolve: {
            pagingParams: JhiResolvePagingParams
        },
        data: {
            authorities: ['ROLE_USER'],
            defaultSort: 'id,asc',
            pageTitle: 'jHipster5TestApp.certificateTemplate.home.title'
        },
        canActivate: [UserRouteAccessService]
    },
    {
        path: 'certificate-template/:id/view',
        component: CertificateTemplateDetailComponent,
        resolve: {
            certificateTemplate: CertificateTemplateResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'jHipster5TestApp.certificateTemplate.home.title'
        },
        canActivate: [UserRouteAccessService]
    },
    {
        path: 'certificate-template/new',
        component: CertificateTemplateUpdateComponent,
        resolve: {
            certificateTemplate: CertificateTemplateResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'jHipster5TestApp.certificateTemplate.home.title'
        },
        canActivate: [UserRouteAccessService]
    },
    {
        path: 'certificate-template/:id/edit',
        component: CertificateTemplateUpdateComponent,
        resolve: {
            certificateTemplate: CertificateTemplateResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'jHipster5TestApp.certificateTemplate.home.title'
        },
        canActivate: [UserRouteAccessService]
    }
];

export const certificateTemplatePopupRoute: Routes = [
    {
        path: 'certificate-template/:id/delete',
        component: CertificateTemplateDeletePopupComponent,
        resolve: {
            certificateTemplate: CertificateTemplateResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'jHipster5TestApp.certificateTemplate.home.title'
        },
        canActivate: [UserRouteAccessService],
        outlet: 'popup'
    }
];
