import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { HttpResponse, HttpErrorResponse } from '@angular/common/http';
import { Observable } from 'rxjs';
import { JhiAlertService } from 'ng-jhipster';

import { IPhysicalCard } from 'app/shared/model/physical-card.model';
import { PhysicalCardService } from './physical-card.service';
import { IPerson } from 'app/shared/model/person.model';
import { PersonService } from 'app/entities/person';
import { ICardType } from 'app/shared/model/card-type.model';
import { CardTypeService } from 'app/entities/card-type';

@Component({
    selector: '-physical-card-update',
    templateUrl: './physical-card-update.component.html'
})
export class PhysicalCardUpdateComponent implements OnInit {
    private _physicalCard: IPhysicalCard;
    isSaving: boolean;

    people: IPerson[];

    cardtypes: ICardType[];

    constructor(
        private jhiAlertService: JhiAlertService,
        private physicalCardService: PhysicalCardService,
        private personService: PersonService,
        private cardTypeService: CardTypeService,
        private activatedRoute: ActivatedRoute
    ) {}

    ngOnInit() {
        this.isSaving = false;
        this.activatedRoute.data.subscribe(({ physicalCard }) => {
            this.physicalCard = physicalCard;
        });
        this.personService.query().subscribe(
            (res: HttpResponse<IPerson[]>) => {
                this.people = res.body;
            },
            (res: HttpErrorResponse) => this.onError(res.message)
        );
        this.cardTypeService.query().subscribe(
            (res: HttpResponse<ICardType[]>) => {
                this.cardtypes = res.body;
            },
            (res: HttpErrorResponse) => this.onError(res.message)
        );
    }

    previousState() {
        window.history.back();
    }

    save() {
        this.isSaving = true;
        if (this.physicalCard.id !== undefined) {
            this.subscribeToSaveResponse(this.physicalCardService.update(this.physicalCard));
        } else {
            this.subscribeToSaveResponse(this.physicalCardService.create(this.physicalCard));
        }
    }

    private subscribeToSaveResponse(result: Observable<HttpResponse<IPhysicalCard>>) {
        result.subscribe((res: HttpResponse<IPhysicalCard>) => this.onSaveSuccess(), (res: HttpErrorResponse) => this.onSaveError());
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

    trackPersonById(index: number, item: IPerson) {
        return item.id;
    }

    trackCardTypeById(index: number, item: ICardType) {
        return item.id;
    }
    get physicalCard() {
        return this._physicalCard;
    }

    set physicalCard(physicalCard: IPhysicalCard) {
        this._physicalCard = physicalCard;
    }
}
