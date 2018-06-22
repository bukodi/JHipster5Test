import { ILogicalCard } from 'app/shared/model//logical-card.model';

export interface IPhysicalCard {
    id?: number;
    visualId?: string;
    ownerLogin?: string;
    ownerId?: number;
    typeName?: string;
    typeId?: number;
    logicalCards?: ILogicalCard[];
}

export class PhysicalCard implements IPhysicalCard {
    constructor(
        public id?: number,
        public visualId?: string,
        public ownerLogin?: string,
        public ownerId?: number,
        public typeName?: string,
        public typeId?: number,
        public logicalCards?: ILogicalCard[]
    ) {}
}
