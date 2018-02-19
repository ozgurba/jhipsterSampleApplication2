import { Injectable, Component } from '@angular/core';
import { Router } from '@angular/router';
import { NgbModal, NgbModalRef } from '@ng-bootstrap/ng-bootstrap';
import { HttpResponse } from '@angular/common/http';
import { StakeHolderMySuffix } from './stake-holder-my-suffix.model';
import { StakeHolderMySuffixService } from './stake-holder-my-suffix.service';

@Injectable()
export class StakeHolderMySuffixPopupService {
    private ngbModalRef: NgbModalRef;

    constructor(
        private modalService: NgbModal,
        private router: Router,
        private stakeHolderService: StakeHolderMySuffixService

    ) {
        this.ngbModalRef = null;
    }

    open(component: Component, id?: number | any): Promise<NgbModalRef> {
        return new Promise<NgbModalRef>((resolve, reject) => {
            const isOpen = this.ngbModalRef !== null;
            if (isOpen) {
                resolve(this.ngbModalRef);
            }

            if (id) {
                this.stakeHolderService.find(id)
                    .subscribe((stakeHolderResponse: HttpResponse<StakeHolderMySuffix>) => {
                        const stakeHolder: StakeHolderMySuffix = stakeHolderResponse.body;
                        this.ngbModalRef = this.stakeHolderModalRef(component, stakeHolder);
                        resolve(this.ngbModalRef);
                    });
            } else {
                // setTimeout used as a workaround for getting ExpressionChangedAfterItHasBeenCheckedError
                setTimeout(() => {
                    this.ngbModalRef = this.stakeHolderModalRef(component, new StakeHolderMySuffix());
                    resolve(this.ngbModalRef);
                }, 0);
            }
        });
    }

    stakeHolderModalRef(component: Component, stakeHolder: StakeHolderMySuffix): NgbModalRef {
        const modalRef = this.modalService.open(component, { size: 'lg', backdrop: 'static'});
        modalRef.componentInstance.stakeHolder = stakeHolder;
        modalRef.result.then((result) => {
            this.router.navigate([{ outlets: { popup: null }}], { replaceUrl: true, queryParamsHandling: 'merge' });
            this.ngbModalRef = null;
        }, (reason) => {
            this.router.navigate([{ outlets: { popup: null }}], { replaceUrl: true, queryParamsHandling: 'merge' });
            this.ngbModalRef = null;
        });
        return modalRef;
    }
}
