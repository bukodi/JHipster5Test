import { Injectable } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { Resolve, ActivatedRouteSnapshot, RouterStateSnapshot, Routes } from '@angular/router';
import { JhiPaginationUtil, JhiResolvePagingParams } from 'ng-jhipster';
import { UserRouteAccessService } from 'app/core';
import { Observable } from 'rxjs';
import { DirectoryServer } from 'app/shared/model/directory-server.model';
import { DirectoryServerService } from './directory-server.service';
import { DirectoryServerComponent } from './directory-server.component';
import { DirectoryServerDetailComponent } from './directory-server-detail.component';
import { DirectoryServerUpdateComponent } from './directory-server-update.component';
import { DirectoryServerDeletePopupComponent } from './directory-server-delete-dialog.component';
import { IDirectoryServer } from 'app/shared/model/directory-server.model';

@Injectable({ providedIn: 'root' })
export class DirectoryServerResolve implements Resolve<IDirectoryServer> {
    constructor(private service: DirectoryServerService) {}

    resolve(route: ActivatedRouteSnapshot, state: RouterStateSnapshot) {
        const id = route.params['id'] ? route.params['id'] : null;
        if (id) {
            return this.service.find(id).map((directoryServer: HttpResponse<DirectoryServer>) => directoryServer.body);
        }
        return Observable.of(new DirectoryServer());
    }
}

export const directoryServerRoute: Routes = [
    {
        path: 'directory-server',
        component: DirectoryServerComponent,
        resolve: {
            pagingParams: JhiResolvePagingParams
        },
        data: {
            authorities: ['ROLE_USER'],
            defaultSort: 'id,asc',
            pageTitle: 'jHipster5TestApp.directoryServer.home.title'
        },
        canActivate: [UserRouteAccessService]
    },
    {
        path: 'directory-server/:id/view',
        component: DirectoryServerDetailComponent,
        resolve: {
            directoryServer: DirectoryServerResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'jHipster5TestApp.directoryServer.home.title'
        },
        canActivate: [UserRouteAccessService]
    },
    {
        path: 'directory-server/new',
        component: DirectoryServerUpdateComponent,
        resolve: {
            directoryServer: DirectoryServerResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'jHipster5TestApp.directoryServer.home.title'
        },
        canActivate: [UserRouteAccessService]
    },
    {
        path: 'directory-server/:id/edit',
        component: DirectoryServerUpdateComponent,
        resolve: {
            directoryServer: DirectoryServerResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'jHipster5TestApp.directoryServer.home.title'
        },
        canActivate: [UserRouteAccessService]
    }
];

export const directoryServerPopupRoute: Routes = [
    {
        path: 'directory-server/:id/delete',
        component: DirectoryServerDeletePopupComponent,
        resolve: {
            directoryServer: DirectoryServerResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'jHipster5TestApp.directoryServer.home.title'
        },
        canActivate: [UserRouteAccessService],
        outlet: 'popup'
    }
];
