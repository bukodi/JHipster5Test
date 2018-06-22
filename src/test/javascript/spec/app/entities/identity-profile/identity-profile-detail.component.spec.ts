/* tslint:disable max-line-length */
import { ComponentFixture, TestBed } from '@angular/core/testing';
import { ActivatedRoute } from '@angular/router';
import { of } from 'rxjs';

import { JHipster5TestTestModule } from '../../../test.module';
import { IdentityProfileDetailComponent } from 'app/entities/identity-profile/identity-profile-detail.component';
import { IdentityProfile } from 'app/shared/model/identity-profile.model';

describe('Component Tests', () => {
    describe('IdentityProfile Management Detail Component', () => {
        let comp: IdentityProfileDetailComponent;
        let fixture: ComponentFixture<IdentityProfileDetailComponent>;
        const route = ({ data: of({ identityProfile: new IdentityProfile(123) }) } as any) as ActivatedRoute;

        beforeEach(() => {
            TestBed.configureTestingModule({
                imports: [JHipster5TestTestModule],
                declarations: [IdentityProfileDetailComponent],
                providers: [{ provide: ActivatedRoute, useValue: route }]
            })
                .overrideTemplate(IdentityProfileDetailComponent, '')
                .compileComponents();
            fixture = TestBed.createComponent(IdentityProfileDetailComponent);
            comp = fixture.componentInstance;
        });

        describe('OnInit', () => {
            it('Should call load all on init', () => {
                // GIVEN

                // WHEN
                comp.ngOnInit();

                // THEN
                expect(comp.identityProfile).toEqual(jasmine.objectContaining({ id: 123 }));
            });
        });
    });
});
