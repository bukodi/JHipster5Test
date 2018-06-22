import { Injectable } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { Resolve, ActivatedRouteSnapshot, RouterStateSnapshot, Routes } from '@angular/router';
import { JhiPaginationUtil, JhiResolvePagingParams } from 'ng-jhipster';
import { UserRouteAccessService } from 'app/core';
import { Observable } from 'rxjs';
import { CertificateAuthority } from 'app/shared/model/certificate-authority.model';
import { CertificateAuthorityService } from './certificate-authority.service';
import { CertificateAuthorityComponent } from './certificate-authority.component';
import { CertificateAuthorityDetailComponent } from './certificate-authority-detail.component';
import { CertificateAuthorityUpdateComponent } from './certificate-authority-update.component';
import { CertificateAuthorityDeletePopupComponent } from './certificate-authority-delete-dialog.component';
import { ICertificateAuthority } from 'app/shared/model/certificate-authority.model';

@Injectable({ providedIn: 'root' })
export class CertificateAuthorityResolve implements Resolve<ICertificateAuthority> {
    constructor(private service: CertificateAuthorityService) {}

    resolve(route: ActivatedRouteSnapshot, state: RouterStateSnapshot) {
        const id = route.params['id'] ? route.params['id'] : null;
        if (id) {
            return this.service.find(id).map((certificateAuthority: HttpResponse<CertificateAuthority>) => certificateAuthority.body);
        }
        return Observable.of(new CertificateAuthority());
    }
}

export const certificateAuthorityRoute: Routes = [
    {
        path: 'certificate-authority',
        component: CertificateAuthorityComponent,
        resolve: {
            pagingParams: JhiResolvePagingParams
        },
        data: {
            authorities: ['ROLE_USER'],
            defaultSort: 'id,asc',
            pageTitle: 'jHipster5TestApp.certificateAuthority.home.title'
        },
        canActivate: [UserRouteAccessService]
    },
    {
        path: 'certificate-authority/:id/view',
        component: CertificateAuthorityDetailComponent,
        resolve: {
            certificateAuthority: CertificateAuthorityResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'jHipster5TestApp.certificateAuthority.home.title'
        },
        canActivate: [UserRouteAccessService]
    },
    {
        path: 'certificate-authority/new',
        component: CertificateAuthorityUpdateComponent,
        resolve: {
            certificateAuthority: CertificateAuthorityResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'jHipster5TestApp.certificateAuthority.home.title'
        },
        canActivate: [UserRouteAccessService]
    },
    {
        path: 'certificate-authority/:id/edit',
        component: CertificateAuthorityUpdateComponent,
        resolve: {
            certificateAuthority: CertificateAuthorityResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'jHipster5TestApp.certificateAuthority.home.title'
        },
        canActivate: [UserRouteAccessService]
    }
];

export const certificateAuthorityPopupRoute: Routes = [
    {
        path: 'certificate-authority/:id/delete',
        component: CertificateAuthorityDeletePopupComponent,
        resolve: {
            certificateAuthority: CertificateAuthorityResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'jHipster5TestApp.certificateAuthority.home.title'
        },
        canActivate: [UserRouteAccessService],
        outlet: 'popup'
    }
];
