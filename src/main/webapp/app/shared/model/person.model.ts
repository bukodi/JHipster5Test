import { IIdentity } from 'app/shared/model//identity.model';

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
    identities?: IIdentity[];
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
        public identities?: IIdentity[]
    ) {}
}
