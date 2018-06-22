/* tslint:disable max-line-length */
import { ComponentFixture, TestBed, fakeAsync, tick } from '@angular/core/testing';
import { HttpResponse } from '@angular/common/http';
import { Observable, of } from 'rxjs';

import { JHipster5TestTestModule } from '../../../test.module';
import { CustomProcessUpdateComponent } from 'app/entities/custom-process/custom-process-update.component';
import { CustomProcessService } from 'app/entities/custom-process/custom-process.service';
import { CustomProcess } from 'app/shared/model/custom-process.model';

describe('Component Tests', () => {
    describe('CustomProcess Management Update Component', () => {
        let comp: CustomProcessUpdateComponent;
        let fixture: ComponentFixture<CustomProcessUpdateComponent>;
        let service: CustomProcessService;

        beforeEach(() => {
            TestBed.configureTestingModule({
                imports: [JHipster5TestTestModule],
                declarations: [CustomProcessUpdateComponent]
            })
                .overrideTemplate(CustomProcessUpdateComponent, '')
                .compileComponents();

            fixture = TestBed.createComponent(CustomProcessUpdateComponent);
            comp = fixture.componentInstance;
            service = fixture.debugElement.injector.get(CustomProcessService);
        });

        describe('save', () => {
            it(
                'Should call update service on save for existing entity',
                fakeAsync(() => {
                    // GIVEN
                    const entity = new CustomProcess(123);
                    spyOn(service, 'update').and.returnValue(of(new HttpResponse({ body: entity })));
                    comp.customProcess = entity;
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
                    const entity = new CustomProcess();
                    spyOn(service, 'create').and.returnValue(of(new HttpResponse({ body: entity })));
                    comp.customProcess = entity;
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
