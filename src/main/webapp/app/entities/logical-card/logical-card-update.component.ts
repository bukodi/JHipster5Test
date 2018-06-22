import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { HttpResponse, HttpErrorResponse } from '@angular/common/http';
import { Observable } from 'rxjs';
import { JhiAlertService } from 'ng-jhipster';

import { ILogicalCard } from 'app/shared/model/logical-card.model';
import { LogicalCardService } from './logical-card.service';
import { IPhysicalCard } from 'app/shared/model/physical-card.model';
import { PhysicalCardService } from 'app/entities/physical-card';
import { ICertificate } from 'app/shared/model/certificate.model';
import { CertificateService } from 'app/entities/certificate';

@Component({
    selector: '-logical-card-update',
    templateUrl: './logical-card-update.component.html'
})
export class LogicalCardUpdateComponent implements OnInit {
    private _logicalCard: ILogicalCard;
    isSaving: boolean;

    physicalcards: IPhysicalCard[];

    certificates: ICertificate[];

    constructor(
        private jhiAlertService: JhiAlertService,
        private logicalCardService: LogicalCardService,
        private physicalCardService: PhysicalCardService,
        private certificateService: CertificateService,
        private activatedRoute: ActivatedRoute
    ) {}

    ngOnInit() {
        this.isSaving = false;
        this.activatedRoute.data.subscribe(({ logicalCard }) => {
            this.logicalCard = logicalCard;
        });
        this.physicalCardService.query().subscribe(
            (res: HttpResponse<IPhysicalCard[]>) => {
                this.physicalcards = res.body;
            },
            (res: HttpErrorResponse) => this.onError(res.message)
        );
        this.certificateService.query().subscribe(
            (res: HttpResponse<ICertificate[]>) => {
                this.certificates = res.body;
            },
            (res: HttpErrorResponse) => this.onError(res.message)
        );
    }

    previousState() {
        window.history.back();
    }

    save() {
        this.isSaving = true;
        if (this.logicalCard.id !== undefined) {
            this.subscribeToSaveResponse(this.logicalCardService.update(this.logicalCard));
        } else {
            this.subscribeToSaveResponse(this.logicalCardService.create(this.logicalCard));
        }
    }

    private subscribeToSaveResponse(result: Observable<HttpResponse<ILogicalCard>>) {
        result.subscribe((res: HttpResponse<ILogicalCard>) => this.onSaveSuccess(), (res: HttpErrorResponse) => this.onSaveError());
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

    trackPhysicalCardById(index: number, item: IPhysicalCard) {
        return item.id;
    }

    trackCertificateById(index: number, item: ICertificate) {
        return item.id;
    }

    getSelected(selectedVals: Array<any>, option: any) {
        if (selectedVals) {
            for (let i = 0; i < selectedVals.length; i++) {
                if (option.id === selectedVals[i].id) {
                    return selectedVals[i];
                }
            }
        }
        return option;
    }
    get logicalCard() {
        return this._logicalCard;
    }

    set logicalCard(logicalCard: ILogicalCard) {
        this._logicalCard = logicalCard;
    }
}
