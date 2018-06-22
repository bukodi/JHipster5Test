import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';

import { IPhysicalCard } from 'app/shared/model/physical-card.model';

@Component({
    selector: '-physical-card-detail',
    templateUrl: './physical-card-detail.component.html'
})
export class PhysicalCardDetailComponent implements OnInit {
    physicalCard: IPhysicalCard;

    constructor(private activatedRoute: ActivatedRoute) {}

    ngOnInit() {
        this.activatedRoute.data.subscribe(({ physicalCard }) => {
            this.physicalCard = physicalCard;
        });
    }

    previousState() {
        window.history.back();
    }
}
