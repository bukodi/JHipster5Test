import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';

import { IEventLog } from 'app/shared/model/event-log.model';

@Component({
    selector: '-event-log-detail',
    templateUrl: './event-log-detail.component.html'
})
export class EventLogDetailComponent implements OnInit {
    eventLog: IEventLog;

    constructor(private activatedRoute: ActivatedRoute) {}

    ngOnInit() {
        this.activatedRoute.data.subscribe(({ eventLog }) => {
            this.eventLog = eventLog;
        });
    }

    previousState() {
        window.history.back();
    }
}
