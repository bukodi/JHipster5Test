/* tslint:disable max-line-length */
import { ComponentFixture, TestBed, inject, fakeAsync, tick } from '@angular/core/testing';
import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';
import { Observable, of } from 'rxjs';
import { JhiEventManager } from 'ng-jhipster';

import { JHipster5TestTestModule } from '../../../test.module';
import { EventLogDeleteDialogComponent } from 'app/entities/event-log/event-log-delete-dialog.component';
import { EventLogService } from 'app/entities/event-log/event-log.service';

describe('Component Tests', () => {
    describe('EventLog Management Delete Component', () => {
        let comp: EventLogDeleteDialogComponent;
        let fixture: ComponentFixture<EventLogDeleteDialogComponent>;
        let service: EventLogService;
        let mockEventManager: any;
        let mockActiveModal: any;

        beforeEach(() => {
            TestBed.configureTestingModule({
                imports: [JHipster5TestTestModule],
                declarations: [EventLogDeleteDialogComponent]
            })
                .overrideTemplate(EventLogDeleteDialogComponent, '')
                .compileComponents();
            fixture = TestBed.createComponent(EventLogDeleteDialogComponent);
            comp = fixture.componentInstance;
            service = fixture.debugElement.injector.get(EventLogService);
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
