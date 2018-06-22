/* tslint:disable max-line-length */
import { ComponentFixture, TestBed } from '@angular/core/testing';
import { ActivatedRoute } from '@angular/router';
import { of } from 'rxjs';

import { JHipster5TestTestModule } from '../../../test.module';
import { CustomProcessDetailComponent } from 'app/entities/custom-process/custom-process-detail.component';
import { CustomProcess } from 'app/shared/model/custom-process.model';

describe('Component Tests', () => {
    describe('CustomProcess Management Detail Component', () => {
        let comp: CustomProcessDetailComponent;
        let fixture: ComponentFixture<CustomProcessDetailComponent>;
        const route = ({ data: of({ customProcess: new CustomProcess(123) }) } as any) as ActivatedRoute;

        beforeEach(() => {
            TestBed.configureTestingModule({
                imports: [JHipster5TestTestModule],
                declarations: [CustomProcessDetailComponent],
                providers: [{ provide: ActivatedRoute, useValue: route }]
            })
                .overrideTemplate(CustomProcessDetailComponent, '')
                .compileComponents();
            fixture = TestBed.createComponent(CustomProcessDetailComponent);
            comp = fixture.componentInstance;
        });

        describe('OnInit', () => {
            it('Should call load all on init', () => {
                // GIVEN

                // WHEN
                comp.ngOnInit();

                // THEN
                expect(comp.customProcess).toEqual(jasmine.objectContaining({ id: 123 }));
            });
        });
    });
});
