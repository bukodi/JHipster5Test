import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { HttpResponse, HttpErrorResponse } from '@angular/common/http';
import { Observable } from 'rxjs';
import * as moment from 'moment';
import { DATE_TIME_FORMAT } from 'app/shared/constants/input.constants';

import { IEventLog } from 'app/shared/model/event-log.model';
import { EventLogService } from './event-log.service';

@Component({
    selector: '-event-log-update',
    templateUrl: './event-log-update.component.html'
})
export class EventLogUpdateComponent implements OnInit {
    private _eventLog: IEventLog;
    isSaving: boolean;
    instant: string;

    constructor(private eventLogService: EventLogService, private activatedRoute: ActivatedRoute) {}

    ngOnInit() {
        this.isSaving = false;
        this.activatedRoute.data.subscribe(({ eventLog }) => {
            this.eventLog = eventLog;
        });
    }

    previousState() {
        window.history.back();
    }

    save() {
        this.isSaving = true;
        this.eventLog.instant = moment(this.instant, DATE_TIME_FORMAT);
        if (this.eventLog.id !== undefined) {
            this.subscribeToSaveResponse(this.eventLogService.update(this.eventLog));
        } else {
            this.subscribeToSaveResponse(this.eventLogService.create(this.eventLog));
        }
    }

    private subscribeToSaveResponse(result: Observable<HttpResponse<IEventLog>>) {
        result.subscribe((res: HttpResponse<IEventLog>) => this.onSaveSuccess(), (res: HttpErrorResponse) => this.onSaveError());
    }

    private onSaveSuccess() {
        this.isSaving = false;
        this.previousState();
    }

    private onSaveError() {
        this.isSaving = false;
    }
    get eventLog() {
        return this._eventLog;
    }

    set eventLog(eventLog: IEventLog) {
        this._eventLog = eventLog;
        this.instant = moment(eventLog.instant).format(DATE_TIME_FORMAT);
    }
}
