/* tslint:disable max-line-length */
import { ComponentFixture, TestBed } from '@angular/core/testing';
import { ActivatedRoute } from '@angular/router';
import { of } from 'rxjs';

import { JHipster5TestTestModule } from '../../../test.module';
import { LogicalCardDetailComponent } from 'app/entities/logical-card/logical-card-detail.component';
import { LogicalCard } from 'app/shared/model/logical-card.model';

describe('Component Tests', () => {
    describe('LogicalCard Management Detail Component', () => {
        let comp: LogicalCardDetailComponent;
        let fixture: ComponentFixture<LogicalCardDetailComponent>;
        const route = ({ data: of({ logicalCard: new LogicalCard(123) }) } as any) as ActivatedRoute;

        beforeEach(() => {
            TestBed.configureTestingModule({
                imports: [JHipster5TestTestModule],
                declarations: [LogicalCardDetailComponent],
                providers: [{ provide: ActivatedRoute, useValue: route }]
            })
                .overrideTemplate(LogicalCardDetailComponent, '')
                .compileComponents();
            fixture = TestBed.createComponent(LogicalCardDetailComponent);
            comp = fixture.componentInstance;
        });

        describe('OnInit', () => {
            it('Should call load all on init', () => {
                // GIVEN

                // WHEN
                comp.ngOnInit();

                // THEN
                expect(comp.logicalCard).toEqual(jasmine.objectContaining({ id: 123 }));
            });
        });
    });
});
