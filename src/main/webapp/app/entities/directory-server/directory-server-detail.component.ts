import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { JhiDataUtils } from 'ng-jhipster';

import { IDirectoryServer } from 'app/shared/model/directory-server.model';

@Component({
    selector: '-directory-server-detail',
    templateUrl: './directory-server-detail.component.html'
})
export class DirectoryServerDetailComponent implements OnInit {
    directoryServer: IDirectoryServer;

    constructor(private dataUtils: JhiDataUtils, private activatedRoute: ActivatedRoute) {}

    ngOnInit() {
        this.activatedRoute.data.subscribe(({ directoryServer }) => {
            this.directoryServer = directoryServer;
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
