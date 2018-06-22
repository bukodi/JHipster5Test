/* tslint:disable max-line-length */
import { ComponentFixture, TestBed } from '@angular/core/testing';
import { ActivatedRoute } from '@angular/router';
import { of } from 'rxjs';

import { JHipster5TestTestModule } from '../../../test.module';
import { EventLogDetailComponent } from 'app/entities/event-log/event-log-detail.component';
import { EventLog } from 'app/shared/model/event-log.model';

describe('Component Tests', () => {
    describe('EventLog Management Detail Component', () => {
        let comp: EventLogDetailComponent;
        let fixture: ComponentFixture<EventLogDetailComponent>;
        const route = ({ data: of({ eventLog: new EventLog(123) }) } as any) as ActivatedRoute;

        beforeEach(() => {
            TestBed.configureTestingModule({
                imports: [JHipster5TestTestModule],
                declarations: [EventLogDetailComponent],
                providers: [{ provide: ActivatedRoute, useValue: route }]
            })
                .overrideTemplate(EventLogDetailComponent, '')
                .compileComponents();
            fixture = TestBed.createComponent(EventLogDetailComponent);
            comp = fixture.componentInstance;
        });

        describe('OnInit', () => {
            it('Should call load all on init', () => {
                // GIVEN

                // WHEN
                comp.ngOnInit();

                // THEN
                expect(comp.eventLog).toEqual(jasmine.objectContaining({ id: 123 }));
            });
        });
    });
});
