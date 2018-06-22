/* tslint:disable max-line-length */
import { ComponentFixture, TestBed, fakeAsync, tick } from '@angular/core/testing';
import { HttpResponse } from '@angular/common/http';
import { Observable, of } from 'rxjs';

import { JHipster5TestTestModule } from '../../../test.module';
import { CertificateTemplateUpdateComponent } from 'app/entities/certificate-template/certificate-template-update.component';
import { CertificateTemplateService } from 'app/entities/certificate-template/certificate-template.service';
import { CertificateTemplate } from 'app/shared/model/certificate-template.model';

describe('Component Tests', () => {
    describe('CertificateTemplate Management Update Component', () => {
        let comp: CertificateTemplateUpdateComponent;
        let fixture: ComponentFixture<CertificateTemplateUpdateComponent>;
        let service: CertificateTemplateService;

        beforeEach(() => {
            TestBed.configureTestingModule({
                imports: [JHipster5TestTestModule],
                declarations: [CertificateTemplateUpdateComponent]
            })
                .overrideTemplate(CertificateTemplateUpdateComponent, '')
                .compileComponents();

            fixture = TestBed.createComponent(CertificateTemplateUpdateComponent);
            comp = fixture.componentInstance;
            service = fixture.debugElement.injector.get(CertificateTemplateService);
        });

        describe('save', () => {
            it(
                'Should call update service on save for existing entity',
                fakeAsync(() => {
                    // GIVEN
                    const entity = new CertificateTemplate(123);
                    spyOn(service, 'update').and.returnValue(of(new HttpResponse({ body: entity })));
                    comp.certificateTemplate = entity;
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
                    const entity = new CertificateTemplate();
                    spyOn(service, 'create').and.returnValue(of(new HttpResponse({ body: entity })));
                    comp.certificateTemplate = entity;
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
