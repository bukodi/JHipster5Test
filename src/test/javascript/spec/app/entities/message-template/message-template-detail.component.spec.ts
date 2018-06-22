/* tslint:disable max-line-length */
import { ComponentFixture, TestBed } from '@angular/core/testing';
import { ActivatedRoute } from '@angular/router';
import { of } from 'rxjs';

import { JHipster5TestTestModule } from '../../../test.module';
import { MessageTemplateDetailComponent } from 'app/entities/message-template/message-template-detail.component';
import { MessageTemplate } from 'app/shared/model/message-template.model';

describe('Component Tests', () => {
    describe('MessageTemplate Management Detail Component', () => {
        let comp: MessageTemplateDetailComponent;
        let fixture: ComponentFixture<MessageTemplateDetailComponent>;
        const route = ({ data: of({ messageTemplate: new MessageTemplate(123) }) } as any) as ActivatedRoute;

        beforeEach(() => {
            TestBed.configureTestingModule({
                imports: [JHipster5TestTestModule],
                declarations: [MessageTemplateDetailComponent],
                providers: [{ provide: ActivatedRoute, useValue: route }]
            })
                .overrideTemplate(MessageTemplateDetailComponent, '')
                .compileComponents();
            fixture = TestBed.createComponent(MessageTemplateDetailComponent);
            comp = fixture.componentInstance;
        });

        describe('OnInit', () => {
            it('Should call load all on init', () => {
                // GIVEN

                // WHEN
                comp.ngOnInit();

                // THEN
                expect(comp.messageTemplate).toEqual(jasmine.objectContaining({ id: 123 }));
            });
        });
    });
});
