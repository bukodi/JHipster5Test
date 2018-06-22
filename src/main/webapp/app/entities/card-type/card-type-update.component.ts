import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { HttpResponse, HttpErrorResponse } from '@angular/common/http';
import { Observable } from 'rxjs';

import { ICardType } from 'app/shared/model/card-type.model';
import { CardTypeService } from './card-type.service';

@Component({
    selector: '-card-type-update',
    templateUrl: './card-type-update.component.html'
})
export class CardTypeUpdateComponent implements OnInit {
    private _cardType: ICardType;
    isSaving: boolean;

    constructor(private cardTypeService: CardTypeService, private activatedRoute: ActivatedRoute) {}

    ngOnInit() {
        this.isSaving = false;
        this.activatedRoute.data.subscribe(({ cardType }) => {
            this.cardType = cardType;
        });
    }

    previousState() {
        window.history.back();
    }

    save() {
        this.isSaving = true;
        if (this.cardType.id !== undefined) {
            this.subscribeToSaveResponse(this.cardTypeService.update(this.cardType));
        } else {
            this.subscribeToSaveResponse(this.cardTypeService.create(this.cardType));
        }
    }

    private subscribeToSaveResponse(result: Observable<HttpResponse<ICardType>>) {
        result.subscribe((res: HttpResponse<ICardType>) => this.onSaveSuccess(), (res: HttpErrorResponse) => this.onSaveError());
    }

    private onSaveSuccess() {
        this.isSaving = false;
        this.previousState();
    }

    private onSaveError() {
        this.isSaving = false;
    }
    get cardType() {
        return this._cardType;
    }

    set cardType(cardType: ICardType) {
        this._cardType = cardType;
    }
}
