import { Component, OnInit, OnDestroy } from '@angular/core';
import { ActivatedRoute } from '@angular/router';

import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';
import { JhiEventManager } from 'ng-jhipster';

import { StakeHolderMySuffix } from './stake-holder-my-suffix.model';
import { StakeHolderMySuffixPopupService } from './stake-holder-my-suffix-popup.service';
import { StakeHolderMySuffixService } from './stake-holder-my-suffix.service';

@Component({
    selector: 'jhi-stake-holder-my-suffix-delete-dialog',
    templateUrl: './stake-holder-my-suffix-delete-dialog.component.html'
})
export class StakeHolderMySuffixDeleteDialogComponent {

    stakeHolder: StakeHolderMySuffix;

    constructor(
        private stakeHolderService: StakeHolderMySuffixService,
        public activeModal: NgbActiveModal,
        private eventManager: JhiEventManager
    ) {
    }

    clear() {
        this.activeModal.dismiss('cancel');
    }

    confirmDelete(id: number) {
        this.stakeHolderService.delete(id).subscribe((response) => {
            this.eventManager.broadcast({
                name: 'stakeHolderListModification',
                content: 'Deleted an stakeHolder'
            });
            this.activeModal.dismiss(true);
        });
    }
}

@Component({
    selector: 'jhi-stake-holder-my-suffix-delete-popup',
    template: ''
})
export class StakeHolderMySuffixDeletePopupComponent implements OnInit, OnDestroy {

    routeSub: any;

    constructor(
        private route: ActivatedRoute,
        private stakeHolderPopupService: StakeHolderMySuffixPopupService
    ) {}

    ngOnInit() {
        this.routeSub = this.route.params.subscribe((params) => {
            this.stakeHolderPopupService
                .open(StakeHolderMySuffixDeleteDialogComponent as Component, params['id']);
        });
    }

    ngOnDestroy() {
        this.routeSub.unsubscribe();
    }
}
