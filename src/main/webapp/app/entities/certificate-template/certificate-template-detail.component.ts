import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';

import { ICertificateTemplate } from 'app/shared/model/certificate-template.model';

@Component({
    selector: '-certificate-template-detail',
    templateUrl: './certificate-template-detail.component.html'
})
export class CertificateTemplateDetailComponent implements OnInit {
    certificateTemplate: ICertificateTemplate;

    constructor(private activatedRoute: ActivatedRoute) {}

    ngOnInit() {
        this.activatedRoute.data.subscribe(({ certificateTemplate }) => {
            this.certificateTemplate = certificateTemplate;
        });
    }

    previousState() {
        window.history.back();
    }
}
