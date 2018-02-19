/* tslint:disable max-line-length */
import { ComponentFixture, TestBed, async, inject, fakeAsync, tick } from '@angular/core/testing';
import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';
import { Observable } from 'rxjs/Observable';
import { JhiEventManager } from 'ng-jhipster';

import { JhipsterSampleApplication2TestModule } from '../../../test.module';
import { StakeHolderMySuffixDeleteDialogComponent } from '../../../../../../main/webapp/app/entities/stake-holder-my-suffix/stake-holder-my-suffix-delete-dialog.component';
import { StakeHolderMySuffixService } from '../../../../../../main/webapp/app/entities/stake-holder-my-suffix/stake-holder-my-suffix.service';

describe('Component Tests', () => {

    describe('StakeHolderMySuffix Management Delete Component', () => {
        let comp: StakeHolderMySuffixDeleteDialogComponent;
        let fixture: ComponentFixture<StakeHolderMySuffixDeleteDialogComponent>;
        let service: StakeHolderMySuffixService;
        let mockEventManager: any;
        let mockActiveModal: any;

        beforeEach(async(() => {
            TestBed.configureTestingModule({
                imports: [JhipsterSampleApplication2TestModule],
                declarations: [StakeHolderMySuffixDeleteDialogComponent],
                providers: [
                    StakeHolderMySuffixService
                ]
            })
            .overrideTemplate(StakeHolderMySuffixDeleteDialogComponent, '')
            .compileComponents();
        }));

        beforeEach(() => {
            fixture = TestBed.createComponent(StakeHolderMySuffixDeleteDialogComponent);
            comp = fixture.componentInstance;
            service = fixture.debugElement.injector.get(StakeHolderMySuffixService);
            mockEventManager = fixture.debugElement.injector.get(JhiEventManager);
            mockActiveModal = fixture.debugElement.injector.get(NgbActiveModal);
        });

        describe('confirmDelete', () => {
            it('Should call delete service on confirmDelete',
                inject([],
                    fakeAsync(() => {
                        // GIVEN
                        spyOn(service, 'delete').and.returnValue(Observable.of({}));

                        // WHEN
                        comp.confirmDelete(123);
                        tick();

                        // THEN
                        expect(service.delete).toHaveBeenCalledWith(123);
                        expect(mockActiveModal.dismissSpy).toHaveBeenCalled();
                        expect(mockEventManager.broadcastSpy).toHaveBeenCalled();
                    })
                )
            );
        });
    });

});
