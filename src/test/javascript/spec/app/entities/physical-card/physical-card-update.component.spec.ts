/* tslint:disable max-line-length */
import { ComponentFixture, TestBed, fakeAsync, tick } from '@angular/core/testing';
import { HttpResponse } from '@angular/common/http';
import { Observable, of } from 'rxjs';

import { JHipster5TestTestModule } from '../../../test.module';
import { PhysicalCardUpdateComponent } from 'app/entities/physical-card/physical-card-update.component';
import { PhysicalCardService } from 'app/entities/physical-card/physical-card.service';
import { PhysicalCard } from 'app/shared/model/physical-card.model';

describe('Component Tests', () => {
    describe('PhysicalCard Management Update Component', () => {
        let comp: PhysicalCardUpdateComponent;
        let fixture: ComponentFixture<PhysicalCardUpdateComponent>;
        let service: PhysicalCardService;

        beforeEach(() => {
            TestBed.configureTestingModule({
                imports: [JHipster5TestTestModule],
                declarations: [PhysicalCardUpdateComponent]
            })
                .overrideTemplate(PhysicalCardUpdateComponent, '')
                .compileComponents();

            fixture = TestBed.createComponent(PhysicalCardUpdateComponent);
            comp = fixture.componentInstance;
            service = fixture.debugElement.injector.get(PhysicalCardService);
        });

        describe('save', () => {
            it(
                'Should call update service on save for existing entity',
                fakeAsync(() => {
                    // GIVEN
                    const entity = new PhysicalCard(123);
                    spyOn(service, 'update').and.returnValue(of(new HttpResponse({ body: entity })));
                    comp.physicalCard = entity;
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
                    const entity = new PhysicalCard();
                    spyOn(service, 'create').and.returnValue(of(new HttpResponse({ body: entity })));
                    comp.physicalCard = entity;
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
