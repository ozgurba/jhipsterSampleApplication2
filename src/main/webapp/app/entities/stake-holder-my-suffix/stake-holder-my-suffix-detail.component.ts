import { Component, OnInit, OnDestroy } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { HttpResponse } from '@angular/common/http';
import { Subscription } from 'rxjs/Subscription';
import { JhiEventManager } from 'ng-jhipster';

import { StakeHolderMySuffix } from './stake-holder-my-suffix.model';
import { StakeHolderMySuffixService } from './stake-holder-my-suffix.service';

@Component({
    selector: 'jhi-stake-holder-my-suffix-detail',
    templateUrl: './stake-holder-my-suffix-detail.component.html'
})
export class StakeHolderMySuffixDetailComponent implements OnInit, OnDestroy {

    stakeHolder: StakeHolderMySuffix;
    private subscription: Subscription;
    private eventSubscriber: Subscription;

    constructor(
        private eventManager: JhiEventManager,
        private stakeHolderService: StakeHolderMySuffixService,
        private route: ActivatedRoute
    ) {
    }

    ngOnInit() {
        this.subscription = this.route.params.subscribe((params) => {
            this.load(params['id']);
        });
        this.registerChangeInStakeHolders();
    }

    load(id) {
        this.stakeHolderService.find(id)
            .subscribe((stakeHolderResponse: HttpResponse<StakeHolderMySuffix>) => {
                this.stakeHolder = stakeHolderResponse.body;
            });
    }
    previousState() {
        window.history.back();
    }

    ngOnDestroy() {
        this.subscription.unsubscribe();
        this.eventManager.destroy(this.eventSubscriber);
    }

    registerChangeInStakeHolders() {
        this.eventSubscriber = this.eventManager.subscribe(
            'stakeHolderListModification',
            (response) => this.load(this.stakeHolder.id)
        );
    }
}
