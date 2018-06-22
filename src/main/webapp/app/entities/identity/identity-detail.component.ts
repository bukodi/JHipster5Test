import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';

import { IIdentity } from 'app/shared/model/identity.model';

@Component({
    selector: '-identity-detail',
    templateUrl: './identity-detail.component.html'
})
export class IdentityDetailComponent implements OnInit {
    identity: IIdentity;

    constructor(private activatedRoute: ActivatedRoute) {}

    ngOnInit() {
        this.activatedRoute.data.subscribe(({ identity }) => {
            this.identity = identity;
        });
    }

    previousState() {
        window.history.back();
    }
}
