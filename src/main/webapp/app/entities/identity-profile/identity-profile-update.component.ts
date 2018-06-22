import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { HttpResponse, HttpErrorResponse } from '@angular/common/http';
import { Observable } from 'rxjs';

import { IIdentityProfile } from 'app/shared/model/identity-profile.model';
import { IdentityProfileService } from './identity-profile.service';

@Component({
    selector: '-identity-profile-update',
    templateUrl: './identity-profile-update.component.html'
})
export class IdentityProfileUpdateComponent implements OnInit {
    private _identityProfile: IIdentityProfile;
    isSaving: boolean;

    constructor(private identityProfileService: IdentityProfileService, private activatedRoute: ActivatedRoute) {}

    ngOnInit() {
        this.isSaving = false;
        this.activatedRoute.data.subscribe(({ identityProfile }) => {
            this.identityProfile = identityProfile;
        });
    }

    previousState() {
        window.history.back();
    }

    save() {
        this.isSaving = true;
        if (this.identityProfile.id !== undefined) {
            this.subscribeToSaveResponse(this.identityProfileService.update(this.identityProfile));
        } else {
            this.subscribeToSaveResponse(this.identityProfileService.create(this.identityProfile));
        }
    }

    private subscribeToSaveResponse(result: Observable<HttpResponse<IIdentityProfile>>) {
        result.subscribe((res: HttpResponse<IIdentityProfile>) => this.onSaveSuccess(), (res: HttpErrorResponse) => this.onSaveError());
    }

    private onSaveSuccess() {
        this.isSaving = false;
        this.previousState();
    }

    private onSaveError() {
        this.isSaving = false;
    }
    get identityProfile() {
        return this._identityProfile;
    }

    set identityProfile(identityProfile: IIdentityProfile) {
        this._identityProfile = identityProfile;
    }
}
