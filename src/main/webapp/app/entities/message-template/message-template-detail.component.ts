import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';

import { IMessageTemplate } from 'app/shared/model/message-template.model';

@Component({
    selector: '-message-template-detail',
    templateUrl: './message-template-detail.component.html'
})
export class MessageTemplateDetailComponent implements OnInit {
    messageTemplate: IMessageTemplate;

    constructor(private activatedRoute: ActivatedRoute) {}

    ngOnInit() {
        this.activatedRoute.data.subscribe(({ messageTemplate }) => {
            this.messageTemplate = messageTemplate;
        });
    }

    previousState() {
        window.history.back();
    }
}
