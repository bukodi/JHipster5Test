/* tslint:disable max-line-length */
import { ComponentFixture, TestBed, fakeAsync, tick } from '@angular/core/testing';
import { HttpResponse } from '@angular/common/http';
import { Observable, of } from 'rxjs';

import { JHipster5TestTestModule } from '../../../test.module';
import { MessageTemplateUpdateComponent } from 'app/entities/message-template/message-template-update.component';
import { MessageTemplateService } from 'app/entities/message-template/message-template.service';
import { MessageTemplate } from 'app/shared/model/message-template.model';

describe('Component Tests', () => {
    describe('MessageTemplate Management Update Component', () => {
        let comp: MessageTemplateUpdateComponent;
        let fixture: ComponentFixture<MessageTemplateUpdateComponent>;
        let service: MessageTemplateService;

        beforeEach(() => {
            TestBed.configureTestingModule({
                imports: [JHipster5TestTestModule],
                declarations: [MessageTemplateUpdateComponent]
            })
                .overrideTemplate(MessageTemplateUpdateComponent, '')
                .compileComponents();

            fixture = TestBed.createComponent(MessageTemplateUpdateComponent);
            comp = fixture.componentInstance;
            service = fixture.debugElement.injector.get(MessageTemplateService);
        });

        describe('save', () => {
            it(
                'Should call update service on save for existing entity',
                fakeAsync(() => {
                    // GIVEN
                    const entity = new MessageTemplate(123);
                    spyOn(service, 'update').and.returnValue(of(new HttpResponse({ body: entity })));
                    comp.messageTemplate = entity;
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
                    const entity = new MessageTemplate();
                    spyOn(service, 'create').and.returnValue(of(new HttpResponse({ body: entity })));
                    comp.messageTemplate = entity;
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
