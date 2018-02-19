import { Routes } from '@angular/router';

import { UserRouteAccessService } from '../../shared';
import { StakeHolderMySuffixComponent } from './stake-holder-my-suffix.component';
import { StakeHolderMySuffixDetailComponent } from './stake-holder-my-suffix-detail.component';
import { StakeHolderMySuffixPopupComponent } from './stake-holder-my-suffix-dialog.component';
import { StakeHolderMySuffixDeletePopupComponent } from './stake-holder-my-suffix-delete-dialog.component';

export const stakeHolderRoute: Routes = [
    {
        path: 'stake-holder-my-suffix',
        component: StakeHolderMySuffixComponent,
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'StakeHolders'
        },
        canActivate: [UserRouteAccessService]
    }, {
        path: 'stake-holder-my-suffix/:id',
        component: StakeHolderMySuffixDetailComponent,
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'StakeHolders'
        },
        canActivate: [UserRouteAccessService]
    }
];

export const stakeHolderPopupRoute: Routes = [
    {
        path: 'stake-holder-my-suffix-new',
        component: StakeHolderMySuffixPopupComponent,
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'StakeHolders'
        },
        canActivate: [UserRouteAccessService],
        outlet: 'popup'
    },
    {
        path: 'stake-holder-my-suffix/:id/edit',
        component: StakeHolderMySuffixPopupComponent,
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'StakeHolders'
        },
        canActivate: [UserRouteAccessService],
        outlet: 'popup'
    },
    {
        path: 'stake-holder-my-suffix/:id/delete',
        component: StakeHolderMySuffixDeletePopupComponent,
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'StakeHolders'
        },
        canActivate: [UserRouteAccessService],
        outlet: 'popup'
    }
];
