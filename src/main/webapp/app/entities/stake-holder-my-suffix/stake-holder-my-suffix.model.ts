import { BaseEntity } from './../../shared';

export class StakeHolderMySuffix implements BaseEntity {
    constructor(
        public id?: number,
        public description?: string,
        public agency?: string,
        public unit?: string,
        public stakeHolderId?: number,
        public upperStakeHolders?: BaseEntity[],
    ) {
    }
}
