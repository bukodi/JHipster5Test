import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';

import { IIdentityProfile } from 'app/shared/model/identity-profile.model';

@Component({
    selector: '-identity-profile-detail',
    templateUrl: './identity-profile-detail.component.html'
})
export class IdentityProfileDetailComponent implements OnInit {
    identityProfile: IIdentityProfile;

    constructor(private activatedRoute: ActivatedRoute) {}

    ngOnInit() {
        this.activatedRoute.data.subscribe(({ identityProfile }) => {
            this.identityProfile = identityProfile;
        });
    }

    previousState() {
        window.history.back();
    }
}
