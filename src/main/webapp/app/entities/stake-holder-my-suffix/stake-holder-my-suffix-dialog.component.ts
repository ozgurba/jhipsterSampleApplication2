import { Component, OnInit, OnDestroy } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { HttpResponse, HttpErrorResponse } from '@angular/common/http';

import { Observable } from 'rxjs/Observable';
import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';
import { JhiEventManager, JhiAlertService } from 'ng-jhipster';

import { StakeHolderMySuffix } from './stake-holder-my-suffix.model';
import { StakeHolderMySuffixPopupService } from './stake-holder-my-suffix-popup.service';
import { StakeHolderMySuffixService } from './stake-holder-my-suffix.service';

@Component({
    selector: 'jhi-stake-holder-my-suffix-dialog',
    templateUrl: './stake-holder-my-suffix-dialog.component.html'
})
export class StakeHolderMySuffixDialogComponent implements OnInit {

    stakeHolder: StakeHolderMySuffix;
    isSaving: boolean;

    stakeholders: StakeHolderMySuffix[];

    constructor(
        public activeModal: NgbActiveModal,
        private jhiAlertService: JhiAlertService,
        private stakeHolderService: StakeHolderMySuffixService,
        private eventManager: JhiEventManager
    ) {
    }

    ngOnInit() {
        this.isSaving = false;
        this.stakeHolderService.query()
            .subscribe((res: HttpResponse<StakeHolderMySuffix[]>) => { this.stakeholders = res.body; }, (res: HttpErrorResponse) => this.onError(res.message));
    }

    clear() {
        this.activeModal.dismiss('cancel');
    }

    save() {
        this.isSaving = true;
        if (this.stakeHolder.id !== undefined) {
            this.subscribeToSaveResponse(
                this.stakeHolderService.update(this.stakeHolder));
        } else {
            this.subscribeToSaveResponse(
                this.stakeHolderService.create(this.stakeHolder));
        }
    }

    private subscribeToSaveResponse(result: Observable<HttpResponse<StakeHolderMySuffix>>) {
        result.subscribe((res: HttpResponse<StakeHolderMySuffix>) =>
            this.onSaveSuccess(res.body), (res: HttpErrorResponse) => this.onSaveError());
    }

    private onSaveSuccess(result: StakeHolderMySuffix) {
        this.eventManager.broadcast({ name: 'stakeHolderListModification', content: 'OK'});
        this.isSaving = false;
        this.activeModal.dismiss(result);
    }

    private onSaveError() {
        this.isSaving = false;
    }

    private onError(error: any) {
        this.jhiAlertService.error(error.message, null, null);
    }

    trackStakeHolderById(index: number, item: StakeHolderMySuffix) {
        return item.id;
    }
}

@Component({
    selector: 'jhi-stake-holder-my-suffix-popup',
    template: ''
})
export class StakeHolderMySuffixPopupComponent implements OnInit, OnDestroy {

    routeSub: any;

    constructor(
        private route: ActivatedRoute,
        private stakeHolderPopupService: StakeHolderMySuffixPopupService
    ) {}

    ngOnInit() {
        this.routeSub = this.route.params.subscribe((params) => {
            if ( params['id'] ) {
                this.stakeHolderPopupService
                    .open(StakeHolderMySuffixDialogComponent as Component, params['id']);
            } else {
                this.stakeHolderPopupService
                    .open(StakeHolderMySuffixDialogComponent as Component);
            }
        });
    }

    ngOnDestroy() {
        this.routeSub.unsubscribe();
    }
}
