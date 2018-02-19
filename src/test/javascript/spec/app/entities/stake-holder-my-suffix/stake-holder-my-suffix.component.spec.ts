/* tslint:disable max-line-length */
import { ComponentFixture, TestBed, async } from '@angular/core/testing';
import { Observable } from 'rxjs/Observable';
import { HttpHeaders, HttpResponse } from '@angular/common/http';

import { JhipsterSampleApplication2TestModule } from '../../../test.module';
import { StakeHolderMySuffixComponent } from '../../../../../../main/webapp/app/entities/stake-holder-my-suffix/stake-holder-my-suffix.component';
import { StakeHolderMySuffixService } from '../../../../../../main/webapp/app/entities/stake-holder-my-suffix/stake-holder-my-suffix.service';
import { StakeHolderMySuffix } from '../../../../../../main/webapp/app/entities/stake-holder-my-suffix/stake-holder-my-suffix.model';

describe('Component Tests', () => {

    describe('StakeHolderMySuffix Management Component', () => {
        let comp: StakeHolderMySuffixComponent;
        let fixture: ComponentFixture<StakeHolderMySuffixComponent>;
        let service: StakeHolderMySuffixService;

        beforeEach(async(() => {
            TestBed.configureTestingModule({
                imports: [JhipsterSampleApplication2TestModule],
                declarations: [StakeHolderMySuffixComponent],
                providers: [
                    StakeHolderMySuffixService
                ]
            })
            .overrideTemplate(StakeHolderMySuffixComponent, '')
            .compileComponents();
        }));

        beforeEach(() => {
            fixture = TestBed.createComponent(StakeHolderMySuffixComponent);
            comp = fixture.componentInstance;
            service = fixture.debugElement.injector.get(StakeHolderMySuffixService);
        });

        describe('OnInit', () => {
            it('Should call load all on init', () => {
                // GIVEN
                const headers = new HttpHeaders().append('link', 'link;link');
                spyOn(service, 'query').and.returnValue(Observable.of(new HttpResponse({
                    body: [new StakeHolderMySuffix(123)],
                    headers
                })));

                // WHEN
                comp.ngOnInit();

                // THEN
                expect(service.query).toHaveBeenCalled();
                expect(comp.stakeHolders[0]).toEqual(jasmine.objectContaining({id: 123}));
            });
        });
    });

});
