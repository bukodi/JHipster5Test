import { IIdentity } from 'app/shared/model//identity.model';

export interface IIdentityProfile {
    id?: number;
    name?: string;
    identities?: IIdentity[];
}

export class IdentityProfile implements IIdentityProfile {
    constructor(public id?: number, public name?: string, public identities?: IIdentity[]) {}
}
