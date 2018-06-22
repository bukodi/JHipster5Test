/* tslint:disable max-line-length */
import { ComponentFixture, TestBed, fakeAsync, tick } from '@angular/core/testing';
import { HttpResponse } from '@angular/common/http';
import { Observable, of } from 'rxjs';

import { JHipster5TestTestModule } from '../../../test.module';
import { DirectoryServerUpdateComponent } from 'app/entities/directory-server/directory-server-update.component';
import { DirectoryServerService } from 'app/entities/directory-server/directory-server.service';
import { DirectoryServer } from 'app/shared/model/directory-server.model';

describe('Component Tests', () => {
    describe('DirectoryServer Management Update Component', () => {
        let comp: DirectoryServerUpdateComponent;
        let fixture: ComponentFixture<DirectoryServerUpdateComponent>;
        let service: DirectoryServerService;

        beforeEach(() => {
            TestBed.configureTestingModule({
                imports: [JHipster5TestTestModule],
                declarations: [DirectoryServerUpdateComponent]
            })
                .overrideTemplate(DirectoryServerUpdateComponent, '')
                .compileComponents();

            fixture = TestBed.createComponent(DirectoryServerUpdateComponent);
            comp = fixture.componentInstance;
            service = fixture.debugElement.injector.get(DirectoryServerService);
        });

        describe('save', () => {
            it(
                'Should call update service on save for existing entity',
                fakeAsync(() => {
                    // GIVEN
                    const entity = new DirectoryServer(123);
                    spyOn(service, 'update').and.returnValue(of(new HttpResponse({ body: entity })));
                    comp.directoryServer = entity;
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
                    const entity = new DirectoryServer();
                    spyOn(service, 'create').and.returnValue(of(new HttpResponse({ body: entity })));
                    comp.directoryServer = entity;
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
