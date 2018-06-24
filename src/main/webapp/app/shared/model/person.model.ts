import { IIdentity } from 'app/shared/model//identity.model';
import { IPhysicalCard } from 'app/shared/model//physical-card.model';

export interface IPerson {
    id?: number;
    name?: string;
    title?: string;
    firstName?: string;
    lastName?: string;
    idCard?: string;
    zipCode?: string;
    city?: string;
    address?: string;
    email?: string;
    phone?: string;
    additionalInfo?: string;
    userLogin?: string;
    userId?: number;
    identities?: IIdentity[];
    physicalCards?: IPhysicalCard[];
}

export class Person implements IPerson {
    constructor(
        public id?: number,
        public name?: string,
        public title?: string,
        public firstName?: string,
        public lastName?: string,
        public idCard?: string,
        public zipCode?: string,
        public city?: string,
        public address?: string,
        public email?: string,
        public phone?: string,
        public additionalInfo?: string,
        public userLogin?: string,
        public userId?: number,
        public identities?: IIdentity[],
        public physicalCards?: IPhysicalCard[]
    ) {}
}
