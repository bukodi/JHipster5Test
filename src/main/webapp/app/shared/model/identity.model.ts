import { ICertificate } from 'app/shared/model//certificate.model';

export interface IIdentity {
    id?: number;
    name?: string;
    externalName?: string;
    certificates?: ICertificate[];
    profileName?: string;
    profileId?: number;
    sourceSystemName?: string;
    sourceSystemId?: number;
    realPersonName?: string;
    realPersonId?: number;
}

export class Identity implements IIdentity {
    constructor(
        public id?: number,
        public name?: string,
        public externalName?: string,
        public certificates?: ICertificate[],
        public profileName?: string,
        public profileId?: number,
        public sourceSystemName?: string,
        public sourceSystemId?: number,
        public realPersonName?: string,
        public realPersonId?: number
    ) {}
}
