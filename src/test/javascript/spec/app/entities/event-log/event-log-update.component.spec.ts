/* tslint:disable max-line-length */
import { ComponentFixture, TestBed, fakeAsync, tick } from '@angular/core/testing';
import { HttpResponse } from '@angular/common/http';
import { Observable, of } from 'rxjs';

import { JHipster5TestTestModule } from '../../../test.module';
import { EventLogUpdateComponent } from 'app/entities/event-log/event-log-update.component';
import { EventLogService } from 'app/entities/event-log/event-log.service';
import { EventLog } from 'app/shared/model/event-log.model';

describe('Component Tests', () => {
    describe('EventLog Management Update Component', () => {
        let comp: EventLogUpdateComponent;
        let fixture: ComponentFixture<EventLogUpdateComponent>;
        let service: EventLogService;

        beforeEach(() => {
            TestBed.configureTestingModule({
                imports: [JHipster5TestTestModule],
                declarations: [EventLogUpdateComponent]
            })
                .overrideTemplate(EventLogUpdateComponent, '')
                .compileComponents();

            fixture = TestBed.createComponent(EventLogUpdateComponent);
            comp = fixture.componentInstance;
            service = fixture.debugElement.injector.get(EventLogService);
        });

        describe('save', () => {
            it(
                'Should call update service on save for existing entity',
                fakeAsync(() => {
                    // GIVEN
                    const entity = new EventLog(123);
                    spyOn(service, 'update').and.returnValue(of(new HttpResponse({ body: entity })));
                    comp.eventLog = entity;
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
                    const entity = new EventLog();
                    spyOn(service, 'create').and.returnValue(of(new HttpResponse({ body: entity })));
                    comp.eventLog = entity;
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
