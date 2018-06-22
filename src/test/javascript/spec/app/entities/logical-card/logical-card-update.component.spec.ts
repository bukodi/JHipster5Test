/* tslint:disable max-line-length */
import { ComponentFixture, TestBed, fakeAsync, tick } from '@angular/core/testing';
import { HttpResponse } from '@angular/common/http';
import { Observable, of } from 'rxjs';

import { JHipster5TestTestModule } from '../../../test.module';
import { LogicalCardUpdateComponent } from 'app/entities/logical-card/logical-card-update.component';
import { LogicalCardService } from 'app/entities/logical-card/logical-card.service';
import { LogicalCard } from 'app/shared/model/logical-card.model';

describe('Component Tests', () => {
    describe('LogicalCard Management Update Component', () => {
        let comp: LogicalCardUpdateComponent;
        let fixture: ComponentFixture<LogicalCardUpdateComponent>;
        let service: LogicalCardService;

        beforeEach(() => {
            TestBed.configureTestingModule({
                imports: [JHipster5TestTestModule],
                declarations: [LogicalCardUpdateComponent]
            })
                .overrideTemplate(LogicalCardUpdateComponent, '')
                .compileComponents();

            fixture = TestBed.createComponent(LogicalCardUpdateComponent);
            comp = fixture.componentInstance;
            service = fixture.debugElement.injector.get(LogicalCardService);
        });

        describe('save', () => {
            it(
                'Should call update service on save for existing entity',
                fakeAsync(() => {
                    // GIVEN
                    const entity = new LogicalCard(123);
                    spyOn(service, 'update').and.returnValue(of(new HttpResponse({ body: entity })));
                    comp.logicalCard = entity;
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
                    const entity = new LogicalCard();
                    spyOn(service, 'create').and.returnValue(of(new HttpResponse({ body: entity })));
                    comp.logicalCard = entity;
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
