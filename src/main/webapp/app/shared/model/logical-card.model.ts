import { ICertificate } from 'app/shared/model//certificate.model';

export interface ILogicalCard {
    id?: number;
    name?: string;
    physicalCardVisualId?: string;
    physicalCardId?: number;
    certificates?: ICertificate[];
}

export class LogicalCard implements ILogicalCard {
    constructor(
        public id?: number,
        public name?: string,
        public physicalCardVisualId?: string,
        public physicalCardId?: number,
        public certificates?: ICertificate[]
    ) {}
}
