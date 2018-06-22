import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';

import { ICustomProcess } from 'app/shared/model/custom-process.model';

@Component({
    selector: '-custom-process-detail',
    templateUrl: './custom-process-detail.component.html'
})
export class CustomProcessDetailComponent implements OnInit {
    customProcess: ICustomProcess;

    constructor(private activatedRoute: ActivatedRoute) {}

    ngOnInit() {
        this.activatedRoute.data.subscribe(({ customProcess }) => {
            this.customProcess = customProcess;
        });
    }

    previousState() {
        window.history.back();
    }
}
