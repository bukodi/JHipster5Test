import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { HttpResponse, HttpErrorResponse } from '@angular/common/http';
import { Observable } from 'rxjs';

import { ICustomProcess } from 'app/shared/model/custom-process.model';
import { CustomProcessService } from './custom-process.service';

@Component({
    selector: '-custom-process-update',
    templateUrl: './custom-process-update.component.html'
})
export class CustomProcessUpdateComponent implements OnInit {
    private _customProcess: ICustomProcess;
    isSaving: boolean;

    constructor(private customProcessService: CustomProcessService, private activatedRoute: ActivatedRoute) {}

    ngOnInit() {
        this.isSaving = false;
        this.activatedRoute.data.subscribe(({ customProcess }) => {
            this.customProcess = customProcess;
        });
    }

    previousState() {
        window.history.back();
    }

    save() {
        this.isSaving = true;
        if (this.customProcess.id !== undefined) {
            this.subscribeToSaveResponse(this.customProcessService.update(this.customProcess));
        } else {
            this.subscribeToSaveResponse(this.customProcessService.create(this.customProcess));
        }
    }

    private subscribeToSaveResponse(result: Observable<HttpResponse<ICustomProcess>>) {
        result.subscribe((res: HttpResponse<ICustomProcess>) => this.onSaveSuccess(), (res: HttpErrorResponse) => this.onSaveError());
    }

    private onSaveSuccess() {
        this.isSaving = false;
        this.previousState();
    }

    private onSaveError() {
        this.isSaving = false;
    }
    get customProcess() {
        return this._customProcess;
    }

    set customProcess(customProcess: ICustomProcess) {
        this._customProcess = customProcess;
    }
}
