/* tslint:disable max-line-length */
import { ComponentFixture, TestBed, fakeAsync, tick } from '@angular/core/testing';
import { HttpResponse } from '@angular/common/http';
import { Observable, of } from 'rxjs';

import { JHipster5TestTestModule } from '../../../test.module';
import { IdentityProfileUpdateComponent } from 'app/entities/identity-profile/identity-profile-update.component';
import { IdentityProfileService } from 'app/entities/identity-profile/identity-profile.service';
import { IdentityProfile } from 'app/shared/model/identity-profile.model';

describe('Component Tests', () => {
    describe('IdentityProfile Management Update Component', () => {
        let comp: IdentityProfileUpdateComponent;
        let fixture: ComponentFixture<IdentityProfileUpdateComponent>;
        let service: IdentityProfileService;

        beforeEach(() => {
            TestBed.configureTestingModule({
                imports: [JHipster5TestTestModule],
                declarations: [IdentityProfileUpdateComponent]
            })
                .overrideTemplate(IdentityProfileUpdateComponent, '')
                .compileComponents();

            fixture = TestBed.createComponent(IdentityProfileUpdateComponent);
            comp = fixture.componentInstance;
            service = fixture.debugElement.injector.get(IdentityProfileService);
        });

        describe('save', () => {
            it(
                'Should call update service on save for existing entity',
                fakeAsync(() => {
                    // GIVEN
                    const entity = new IdentityProfile(123);
                    spyOn(service, 'update').and.returnValue(of(new HttpResponse({ body: entity })));
                    comp.identityProfile = entity;
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
                    const entity = new IdentityProfile();
                    spyOn(service, 'create').and.returnValue(of(new HttpResponse({ body: entity })));
                    comp.identityProfile = entity;
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
