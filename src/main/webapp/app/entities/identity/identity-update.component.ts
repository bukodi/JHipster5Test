import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { HttpResponse, HttpErrorResponse } from '@angular/common/http';
import { Observable } from 'rxjs';
import { JhiAlertService } from 'ng-jhipster';

import { IIdentity } from 'app/shared/model/identity.model';
import { IdentityService } from './identity.service';
import { IIdentityProfile } from 'app/shared/model/identity-profile.model';
import { IdentityProfileService } from 'app/entities/identity-profile';
import { IDirectoryServer } from 'app/shared/model/directory-server.model';
import { DirectoryServerService } from 'app/entities/directory-server';
import { IPerson } from 'app/shared/model/person.model';
import { PersonService } from 'app/entities/person';

@Component({
    selector: '-identity-update',
    templateUrl: './identity-update.component.html'
})
export class IdentityUpdateComponent implements OnInit {
    private _identity: IIdentity;
    isSaving: boolean;

    identityprofiles: IIdentityProfile[];

    directoryservers: IDirectoryServer[];

    people: IPerson[];

    constructor(
        private jhiAlertService: JhiAlertService,
        private identityService: IdentityService,
        private identityProfileService: IdentityProfileService,
        private directoryServerService: DirectoryServerService,
        private personService: PersonService,
        private activatedRoute: ActivatedRoute
    ) {}

    ngOnInit() {
        this.isSaving = false;
        this.activatedRoute.data.subscribe(({ identity }) => {
            this.identity = identity;
        });
        this.identityProfileService.query().subscribe(
            (res: HttpResponse<IIdentityProfile[]>) => {
                this.identityprofiles = res.body;
            },
            (res: HttpErrorResponse) => this.onError(res.message)
        );
        this.directoryServerService.query().subscribe(
            (res: HttpResponse<IDirectoryServer[]>) => {
                this.directoryservers = res.body;
            },
            (res: HttpErrorResponse) => this.onError(res.message)
        );
        this.personService.query().subscribe(
            (res: HttpResponse<IPerson[]>) => {
                this.people = res.body;
            },
            (res: HttpErrorResponse) => this.onError(res.message)
        );
    }

    previousState() {
        window.history.back();
    }

    save() {
        this.isSaving = true;
        if (this.identity.id !== undefined) {
            this.subscribeToSaveResponse(this.identityService.update(this.identity));
        } else {
            this.subscribeToSaveResponse(this.identityService.create(this.identity));
        }
    }

    private subscribeToSaveResponse(result: Observable<HttpResponse<IIdentity>>) {
        result.subscribe((res: HttpResponse<IIdentity>) => this.onSaveSuccess(), (res: HttpErrorResponse) => this.onSaveError());
    }

    private onSaveSuccess() {
        this.isSaving = false;
        this.previousState();
    }

    private onSaveError() {
        this.isSaving = false;
    }

    private onError(errorMessage: string) {
        this.jhiAlertService.error(errorMessage, null, null);
    }

    trackIdentityProfileById(index: number, item: IIdentityProfile) {
        return item.id;
    }

    trackDirectoryServerById(index: number, item: IDirectoryServer) {
        return item.id;
    }

    trackPersonById(index: number, item: IPerson) {
        return item.id;
    }
    get identity() {
        return this._identity;
    }

    set identity(identity: IIdentity) {
        this._identity = identity;
    }
}
