/* tslint:disable max-line-length */
import { ComponentFixture, TestBed } from '@angular/core/testing';
import { ActivatedRoute } from '@angular/router';
import { of } from 'rxjs';

import { JHipster5TestTestModule } from '../../../test.module';
import { CertificateAuthorityDetailComponent } from 'app/entities/certificate-authority/certificate-authority-detail.component';
import { CertificateAuthority } from 'app/shared/model/certificate-authority.model';

describe('Component Tests', () => {
    describe('CertificateAuthority Management Detail Component', () => {
        let comp: CertificateAuthorityDetailComponent;
        let fixture: ComponentFixture<CertificateAuthorityDetailComponent>;
        const route = ({ data: of({ certificateAuthority: new CertificateAuthority(123) }) } as any) as ActivatedRoute;

        beforeEach(() => {
            TestBed.configureTestingModule({
                imports: [JHipster5TestTestModule],
                declarations: [CertificateAuthorityDetailComponent],
                providers: [{ provide: ActivatedRoute, useValue: route }]
            })
                .overrideTemplate(CertificateAuthorityDetailComponent, '')
                .compileComponents();
            fixture = TestBed.createComponent(CertificateAuthorityDetailComponent);
            comp = fixture.componentInstance;
        });

        describe('OnInit', () => {
            it('Should call load all on init', () => {
                // GIVEN

                // WHEN
                comp.ngOnInit();

                // THEN
                expect(comp.certificateAuthority).toEqual(jasmine.objectContaining({ id: 123 }));
            });
        });
    });
});
