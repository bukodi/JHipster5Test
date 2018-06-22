/* tslint:disable max-line-length */
import { ComponentFixture, TestBed, fakeAsync, tick } from '@angular/core/testing';
import { HttpResponse } from '@angular/common/http';
import { Observable, of } from 'rxjs';

import { JHipster5TestTestModule } from '../../../test.module';
import { CertificateAuthorityUpdateComponent } from 'app/entities/certificate-authority/certificate-authority-update.component';
import { CertificateAuthorityService } from 'app/entities/certificate-authority/certificate-authority.service';
import { CertificateAuthority } from 'app/shared/model/certificate-authority.model';

describe('Component Tests', () => {
    describe('CertificateAuthority Management Update Component', () => {
        let comp: CertificateAuthorityUpdateComponent;
        let fixture: ComponentFixture<CertificateAuthorityUpdateComponent>;
        let service: CertificateAuthorityService;

        beforeEach(() => {
            TestBed.configureTestingModule({
                imports: [JHipster5TestTestModule],
                declarations: [CertificateAuthorityUpdateComponent]
            })
                .overrideTemplate(CertificateAuthorityUpdateComponent, '')
                .compileComponents();

            fixture = TestBed.createComponent(CertificateAuthorityUpdateComponent);
            comp = fixture.componentInstance;
            service = fixture.debugElement.injector.get(CertificateAuthorityService);
        });

        describe('save', () => {
            it(
                'Should call update service on save for existing entity',
                fakeAsync(() => {
                    // GIVEN
                    const entity = new CertificateAuthority(123);
                    spyOn(service, 'update').and.returnValue(of(new HttpResponse({ body: entity })));
                    comp.certificateAuthority = entity;
                    // WHEN
                    comp.save();
                    tick(); // simulate async

                    // THEN
                    expect(service.update).toHaveBeenCalledWith(entity);
                    expect(comp.isSaving).toEqual(false);
                })
            );

            it(
                'Should call create service on save for new entity',
                fakeAsync(() => {
                    // GIVEN
                    const entity = new CertificateAuthority();
                    spyOn(service, 'create').and.returnValue(of(new HttpResponse({ body: entity })));
                    comp.certificateAuthority = entity;
                    // WHEN
                    comp.save();
                    tick(); // simulate async

                    // THEN
                    expect(service.create).toHaveBeenCalledWith(entity);
                    expect(comp.isSaving).toEqual(false);
                })
            );
        });
    });
});
