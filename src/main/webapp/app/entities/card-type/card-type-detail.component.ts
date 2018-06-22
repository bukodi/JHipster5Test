import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';

import { ICardType } from 'app/shared/model/card-type.model';

@Component({
    selector: '-card-type-detail',
    templateUrl: './card-type-detail.component.html'
})
export class CardTypeDetailComponent implements OnInit {
    cardType: ICardType;

    constructor(private activatedRoute: ActivatedRoute) {}

    ngOnInit() {
        this.activatedRoute.data.subscribe(({ cardType }) => {
            this.cardType = cardType;
        });
    }

    previousState() {
        window.history.back();
    }
}
