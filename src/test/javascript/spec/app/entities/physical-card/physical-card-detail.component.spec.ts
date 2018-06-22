/* tslint:disable max-line-length */
import { ComponentFixture, TestBed } from '@angular/core/testing';
import { ActivatedRoute } from '@angular/router';
import { of } from 'rxjs';

import { JHipster5TestTestModule } from '../../../test.module';
import { PhysicalCardDetailComponent } from 'app/entities/physical-card/physical-card-detail.component';
import { PhysicalCard } from 'app/shared/model/physical-card.model';

describe('Component Tests', () => {
    describe('PhysicalCard Management Detail Component', () => {
        let comp: PhysicalCardDetailComponent;
        let fixture: ComponentFixture<PhysicalCardDetailComponent>;
        const route = ({ data: of({ physicalCard: new PhysicalCard(123) }) } as any) as ActivatedRoute;

        beforeEach(() => {
            TestBed.configureTestingModule({
                imports: [JHipster5TestTestModule],
                declarations: [PhysicalCardDetailComponent],
                providers: [{ provide: ActivatedRoute, useValue: route }]
            })
                .overrideTemplate(PhysicalCardDetailComponent, '')
                .compileComponents();
            fixture = TestBed.createComponent(PhysicalCardDetailComponent);
            comp = fixture.componentInstance;
        });

        describe('OnInit', () => {
            it('Should call load all on init', () => {
                // GIVEN

                // WHEN
                comp.ngOnInit();

                // THEN
                expect(comp.physicalCard).toEqual(jasmine.objectContaining({ id: 123 }));
            });
        });
    });
});
