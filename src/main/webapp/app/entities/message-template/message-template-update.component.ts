import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { HttpResponse, HttpErrorResponse } from '@angular/common/http';
import { Observable } from 'rxjs';
import * as moment from 'moment';
import { DATE_TIME_FORMAT } from 'app/shared/constants/input.constants';

import { IMessageTemplate } from 'app/shared/model/message-template.model';
import { MessageTemplateService } from './message-template.service';

@Component({
    selector: '-message-template-update',
    templateUrl: './message-template-update.component.html'
})
export class MessageTemplateUpdateComponent implements OnInit {
    private _messageTemplate: IMessageTemplate;
    isSaving: boolean;
    instant: string;

    constructor(private messageTemplateService: MessageTemplateService, private activatedRoute: ActivatedRoute) {}

    ngOnInit() {
        this.isSaving = false;
        this.activatedRoute.data.subscribe(({ messageTemplate }) => {
            this.messageTemplate = messageTemplate;
        });
    }

    previousState() {
        window.history.back();
    }

    save() {
        this.isSaving = true;
        this.messageTemplate.instant = moment(this.instant, DATE_TIME_FORMAT);
        if (this.messageTemplate.id !== undefined) {
            this.subscribeToSaveResponse(this.messageTemplateService.update(this.messageTemplate));
        } else {
            this.subscribeToSaveResponse(this.messageTemplateService.create(this.messageTemplate));
        }
    }

    private subscribeToSaveResponse(result: Observable<HttpResponse<IMessageTemplate>>) {
        result.subscribe((res: HttpResponse<IMessageTemplate>) => this.onSaveSuccess(), (res: HttpErrorResponse) => this.onSaveError());
    }

    private onSaveSuccess() {
        this.isSaving = false;
        this.previousState();
    }

    private onSaveError() {
        this.isSaving = false;
    }
    get messageTemplate() {
        return this._messageTemplate;
    }

    set messageTemplate(messageTemplate: IMessageTemplate) {
        this._messageTemplate = messageTemplate;
        this.instant = moment(messageTemplate.instant).format(DATE_TIME_FORMAT);
    }
}
