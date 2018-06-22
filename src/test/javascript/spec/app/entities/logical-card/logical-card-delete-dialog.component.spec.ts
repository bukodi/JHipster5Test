/* tslint:disable max-line-length */
import { ComponentFixture, TestBed, inject, fakeAsync, tick } from '@angular/core/testing';
import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';
import { Observable, of } from 'rxjs';
import { JhiEventManager } from 'ng-jhipster';

import { JHipster5TestTestModule } from '../../../test.module';
import { LogicalCardDeleteDialogComponent } from 'app/entities/logical-card/logical-card-delete-dialog.component';
import { LogicalCardService } from 'app/entities/logical-card/logical-card.service';

describe('Component Tests', () => {
    describe('LogicalCard Management Delete Component', () => {
        let comp: LogicalCardDeleteDialogComponent;
        let fixture: ComponentFixture<LogicalCardDeleteDialogComponent>;
        let service: LogicalCardService;
        let mockEventManager: any;
        let mockActiveModal: any;

        beforeEach(() => {
            TestBed.configureTestingModule({
                imports: [JHipster5TestTestModule],
                declarations: [LogicalCardDeleteDialogComponent]
            })
                .overrideTemplate(LogicalCardDeleteDialogComponent, '')
                .compileComponents();
            fixture = TestBed.createComponent(LogicalCardDeleteDialogComponent);
            comp = fixture.componentInstance;
            service = fixture.debugElement.injector.get(LogicalCardService);
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
