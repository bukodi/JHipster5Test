/* tslint:disable max-line-length */
import { ComponentFixture, TestBed, inject, fakeAsync, tick } from '@angular/core/testing';
import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';
import { Observable, of } from 'rxjs';
import { JhiEventManager } from 'ng-jhipster';

import { JHipster5TestTestModule } from '../../../test.module';
import { CertificateAuthorityDeleteDialogComponent } from 'app/entities/certificate-authority/certificate-authority-delete-dialog.component';
import { CertificateAuthorityService } from 'app/entities/certificate-authority/certificate-authority.service';

describe('Component Tests', () => {
    describe('CertificateAuthority Management Delete Component', () => {
        let comp: CertificateAuthorityDeleteDialogComponent;
        let fixture: ComponentFixture<CertificateAuthorityDeleteDialogComponent>;
        let service: CertificateAuthorityService;
        let mockEventManager: any;
        let mockActiveModal: any;

        beforeEach(() => {
            TestBed.configureTestingModule({
                imports: [JHipster5TestTestModule],
                declarations: [CertificateAuthorityDeleteDialogComponent]
            })
                .overrideTemplate(CertificateAuthorityDeleteDialogComponent, '')
                .compileComponents();
            fixture = TestBed.createComponent(CertificateAuthorityDeleteDialogComponent);
            comp = fixture.componentInstance;
            service = fixture.debugElement.injector.get(CertificateAuthorityService);
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
