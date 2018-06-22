/* tslint:disable max-line-length */
import { ComponentFixture, TestBed, inject, fakeAsync, tick } from '@angular/core/testing';
import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';
import { Observable, of } from 'rxjs';
import { JhiEventManager } from 'ng-jhipster';

import { JHipster5TestTestModule } from '../../../test.module';
import { DirectoryServerDeleteDialogComponent } from 'app/entities/directory-server/directory-server-delete-dialog.component';
import { DirectoryServerService } from 'app/entities/directory-server/directory-server.service';

describe('Component Tests', () => {
    describe('DirectoryServer Management Delete Component', () => {
        let comp: DirectoryServerDeleteDialogComponent;
        let fixture: ComponentFixture<DirectoryServerDeleteDialogComponent>;
        let service: DirectoryServerService;
        let mockEventManager: any;
        let mockActiveModal: any;

        beforeEach(() => {
            TestBed.configureTestingModule({
                imports: [JHipster5TestTestModule],
                declarations: [DirectoryServerDeleteDialogComponent]
            })
                .overrideTemplate(DirectoryServerDeleteDialogComponent, '')
                .compileComponents();
            fixture = TestBed.createComponent(DirectoryServerDeleteDialogComponent);
            comp = fixture.componentInstance;
            service = fixture.debugElement.injector.get(DirectoryServerService);
            mockEventManager = fixture.debugElement.injector.get(JhiEventManager);
            mockActiveModal = fixture.debugElement.injector.get(NgbActiveModal);
        });

        describe('confirmDelete', () => {
            it(
                'Should call delete service on confirmDelete',
                inject(
                    [],
                    fakeAsync(() => {
                        // GIVEN
                        spyOn(service, 'delete').and.returnValue(of({}));

                        // WHEN
                        comp.confirmDelete(123);
                        tick();

                        // THEN
                        expect(service.delete).toHaveBeenCalledWith(123);
                        expect(mockActiveModal.dismissSpy).toHaveBeenCalled();
                        expect(mockEventManager.broadcastSpy).toHaveBeenCalled();
                    })
                )
            );
        });
    });
});
