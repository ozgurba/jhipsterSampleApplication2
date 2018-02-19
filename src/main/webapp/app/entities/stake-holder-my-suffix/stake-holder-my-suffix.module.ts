import { NgModule, CUSTOM_ELEMENTS_SCHEMA } from '@angular/core';
import { RouterModule } from '@angular/router';

import { JhipsterSampleApplication2SharedModule } from '../../shared';
import {
    StakeHolderMySuffixService,
    StakeHolderMySuffixPopupService,
    StakeHolderMySuffixComponent,
    StakeHolderMySuffixDetailComponent,
    StakeHolderMySuffixDialogComponent,
    StakeHolderMySuffixPopupComponent,
    StakeHolderMySuffixDeletePopupComponent,
    StakeHolderMySuffixDeleteDialogComponent,
    stakeHolderRoute,
    stakeHolderPopupRoute,
} from './';

const ENTITY_STATES = [
    ...stakeHolderRoute,
    ...stakeHolderPopupRoute,
];

@NgModule({
    imports: [
        JhipsterSampleApplication2SharedModule,
        RouterModule.forChild(ENTITY_STATES)
    ],
    declarations: [
        StakeHolderMySuffixComponent,
        StakeHolderMySuffixDetailComponent,
        StakeHolderMySuffixDialogComponent,
        StakeHolderMySuffixDeleteDialogComponent,
        StakeHolderMySuffixPopupComponent,
        StakeHolderMySuffixDeletePopupComponent,
    ],
    entryComponents: [
        StakeHolderMySuffixComponent,
        StakeHolderMySuffixDialogComponent,
        StakeHolderMySuffixPopupComponent,
        StakeHolderMySuffixDeleteDialogComponent,
        StakeHolderMySuffixDeletePopupComponent,
    ],
    providers: [
        StakeHolderMySuffixService,
        StakeHolderMySuffixPopupService,
    ],
    schemas: [CUSTOM_ELEMENTS_SCHEMA]
})
export class JhipsterSampleApplication2StakeHolderMySuffixModule {}
