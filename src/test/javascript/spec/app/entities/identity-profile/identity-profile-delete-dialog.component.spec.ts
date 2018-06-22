/* tslint:disable max-line-length */
import { ComponentFixture, TestBed, inject, fakeAsync, tick } from '@angular/core/testing';
import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';
import { Observable, of } from 'rxjs';
import { JhiEventManager } from 'ng-jhipster';

import { JHipster5TestTestModule } from '../../../test.module';
import { IdentityProfileDeleteDialogComponent } from 'app/entities/identity-profile/identity-profile-delete-dialog.component';
import { IdentityProfileService } from 'app/entities/identity-profile/identity-profile.service';

describe('Component Tests', () => {
    describe('IdentityProfile Management Delete Component', () => {
        let comp: IdentityProfileDeleteDialogComponent;
        let fixture: ComponentFixture<IdentityProfileDeleteDialogComponent>;
        let service: IdentityProfileService;
        let mockEventManager: any;
        let mockActiveModal: any;

        beforeEach(() => {
            TestBed.configureTestingModule({
                imports: [JHipster5TestTestModule],
                declarations: [IdentityProfileDeleteDialogComponent]
            })
                .overrideTemplate(IdentityProfileDeleteDialogComponent, '')
                .compileComponents();
            fixture = TestBed.createComponent(IdentityProfileDeleteDialogComponent);
            comp = fixture.componentInstance;
            service = fixture.debugElement.injector.get(IdentityProfileService);
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
