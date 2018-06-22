/* tslint:disable max-line-length */
import { ComponentFixture, TestBed, inject, fakeAsync, tick } from '@angular/core/testing';
import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';
import { Observable, of } from 'rxjs';
import { JhiEventManager } from 'ng-jhipster';

import { JHipster5TestTestModule } from '../../../test.module';
import { PhysicalCardDeleteDialogComponent } from 'app/entities/physical-card/physical-card-delete-dialog.component';
import { PhysicalCardService } from 'app/entities/physical-card/physical-card.service';

describe('Component Tests', () => {
    describe('PhysicalCard Management Delete Component', () => {
        let comp: PhysicalCardDeleteDialogComponent;
        let fixture: ComponentFixture<PhysicalCardDeleteDialogComponent>;
        let service: PhysicalCardService;
        let mockEventManager: any;
        let mockActiveModal: any;

        beforeEach(() => {
            TestBed.configureTestingModule({
                imports: [JHipster5TestTestModule],
                declarations: [PhysicalCardDeleteDialogComponent]
            })
                .overrideTemplate(PhysicalCardDeleteDialogComponent, '')
                .compileComponents();
            fixture = TestBed.createComponent(PhysicalCardDeleteDialogComponent);
            comp = fixture.componentInstance;
            service = fixture.debugElement.injector.get(PhysicalCardService);
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
