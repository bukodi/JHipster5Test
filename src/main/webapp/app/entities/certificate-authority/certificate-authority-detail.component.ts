import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { JhiDataUtils } from 'ng-jhipster';

import { ICertificateAuthority } from 'app/shared/model/certificate-authority.model';

@Component({
    selector: '-certificate-authority-detail',
    templateUrl: './certificate-authority-detail.component.html'
})
export class CertificateAuthorityDetailComponent implements OnInit {
    certificateAuthority: ICertificateAuthority;

    constructor(private dataUtils: JhiDataUtils, private activatedRoute: ActivatedRoute) {}

    ngOnInit() {
        this.activatedRoute.data.subscribe(({ certificateAuthority }) => {
            this.certificateAuthority = certificateAuthority;
        });
    }

    byteSize(field) {
        return this.dataUtils.byteSize(field);
    }

    openFile(contentType, field) {
        return this.dataUtils.openFile(contentType, field);
    }
    previousState() {
        window.history.back();
    }
}
