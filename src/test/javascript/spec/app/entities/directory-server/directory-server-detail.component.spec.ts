/* tslint:disable max-line-length */
import { ComponentFixture, TestBed } from '@angular/core/testing';
import { ActivatedRoute } from '@angular/router';
import { of } from 'rxjs';

import { JHipster5TestTestModule } from '../../../test.module';
import { DirectoryServerDetailComponent } from 'app/entities/directory-server/directory-server-detail.component';
import { DirectoryServer } from 'app/shared/model/directory-server.model';

describe('Component Tests', () => {
    describe('DirectoryServer Management Detail Component', () => {
        let comp: DirectoryServerDetailComponent;
        let fixture: ComponentFixture<DirectoryServerDetailComponent>;
        const route = ({ data: of({ directoryServer: new DirectoryServer(123) }) } as any) as ActivatedRoute;

        beforeEach(() => {
            TestBed.configureTestingModule({
                imports: [JHipster5TestTestModule],
                declarations: [DirectoryServerDetailComponent],
                providers: [{ provide: ActivatedRoute, useValue: route }]
            })
                .overrideTemplate(DirectoryServerDetailComponent, '')
                .compileComponents();
            fixture = TestBed.createComponent(DirectoryServerDetailComponent);
            comp = fixture.componentInstance;
        });

        describe('OnInit', () => {
            it('Should call load all on init', () => {
                // GIVEN

                // WHEN
                comp.ngOnInit();

                // THEN
                expect(comp.directoryServer).toEqual(jasmine.objectContaining({ id: 123 }));
            });
        });
    });
});
