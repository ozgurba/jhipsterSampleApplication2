/* tslint:disable max-line-length */
import { ComponentFixture, TestBed, async } from '@angular/core/testing';
import { HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs/Observable';

import { JhipsterSampleApplication2TestModule } from '../../../test.module';
import { StakeHolderMySuffixDetailComponent } from '../../../../../../main/webapp/app/entities/stake-holder-my-suffix/stake-holder-my-suffix-detail.component';
import { StakeHolderMySuffixService } from '../../../../../../main/webapp/app/entities/stake-holder-my-suffix/stake-holder-my-suffix.service';
import { StakeHolderMySuffix } from '../../../../../../main/webapp/app/entities/stake-holder-my-suffix/stake-holder-my-suffix.model';

describe('Component Tests', () => {

    describe('StakeHolderMySuffix Management Detail Component', () => {
        let comp: StakeHolderMySuffixDetailComponent;
        let fixture: ComponentFixture<StakeHolderMySuffixDetailComponent>;
        let service: StakeHolderMySuffixService;

        beforeEach(async(() => {
            TestBed.configureTestingModule({
                imports: [JhipsterSampleApplication2TestModule],
                declarations: [StakeHolderMySuffixDetailComponent],
                providers: [
                    StakeHolderMySuffixService
                ]
            })
            .overrideTemplate(StakeHolderMySuffixDetailComponent, '')
            .compileComponents();
        }));

        beforeEach(() => {
            fixture = TestBed.createComponent(StakeHolderMySuffixDetailComponent);
            comp = fixture.componentInstance;
            service = fixture.debugElement.injector.get(StakeHolderMySuffixService);
        });

        describe('OnInit', () => {
            it('Should call load all on init', () => {
                // GIVEN

                spyOn(service, 'find').and.returnValue(Observable.of(new HttpResponse({
                    body: new StakeHolderMySuffix(123)
                })));

                // WHEN
                comp.ngOnInit();

                // THEN
                expect(service.find).toHaveBeenCalledWith(123);
                expect(comp.stakeHolder).toEqual(jasmine.objectContaining({id: 123}));
            });
        });
    });

});
