/* tslint:disable max-line-length */
import { ComponentFixture, TestBed } from '@angular/core/testing';
import { ActivatedRoute } from '@angular/router';
import { of } from 'rxjs';

import { JHipster5TestTestModule } from '../../../test.module';
import { CertificateTemplateDetailComponent } from 'app/entities/certificate-template/certificate-template-detail.component';
import { CertificateTemplate } from 'app/shared/model/certificate-template.model';

describe('Component Tests', () => {
    describe('CertificateTemplate Management Detail Component', () => {
        let comp: CertificateTemplateDetailComponent;
        let fixture: ComponentFixture<CertificateTemplateDetailComponent>;
        const route = ({ data: of({ certificateTemplate: new CertificateTemplate(123) }) } as any) as ActivatedRoute;

        beforeEach(() => {
            TestBed.configureTestingModule({
                imports: [JHipster5TestTestModule],
                declarations: [CertificateTemplateDetailComponent],
                providers: [{ provide: ActivatedRoute, useValue: route }]
            })
                .overrideTemplate(CertificateTemplateDetailComponent, '')
                .compileComponents();
            fixture = TestBed.createComponent(CertificateTemplateDetailComponent);
            comp = fixture.componentInstance;
        });

        describe('OnInit', () => {
            it('Should call load all on init', () => {
                // GIVEN

                // WHEN
                comp.ngOnInit();

                // THEN
                expect(comp.certificateTemplate).toEqual(jasmine.objectContaining({ id: 123 }));
            });
        });
    });
});
