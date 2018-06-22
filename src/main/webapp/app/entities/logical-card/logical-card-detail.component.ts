import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';

import { ILogicalCard } from 'app/shared/model/logical-card.model';

@Component({
    selector: '-logical-card-detail',
    templateUrl: './logical-card-detail.component.html'
})
export class LogicalCardDetailComponent implements OnInit {
    logicalCard: ILogicalCard;

    constructor(private activatedRoute: ActivatedRoute) {}

    ngOnInit() {
        this.activatedRoute.data.subscribe(({ logicalCard }) => {
            this.logicalCard = logicalCard;
        });
    }

    previousState() {
        window.history.back();
    }
}
