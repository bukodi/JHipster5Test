import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';

import { IPerson } from 'app/shared/model/person.model';

@Component({
    selector: '-person-detail',
    templateUrl: './person-detail.component.html'
})
export class PersonDetailComponent implements OnInit {
    person: IPerson;

    constructor(private activatedRoute: ActivatedRoute) {}

    ngOnInit() {
        this.activatedRoute.data.subscribe(({ person }) => {
            this.person = person;
        });
    }

    previousState() {
        window.history.back();
    }
}
