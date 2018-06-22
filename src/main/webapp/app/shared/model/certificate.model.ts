import { ILogicalCard } from 'app/shared/model//logical-card.model';

export const enum CertificateType {
    SIGN = 'SIGN',
    AUTH = 'AUTH',
    ENCRYPT = 'ENCRYPT',
    DEVICE = 'DEVICE'
}

export interface ICertificate {
    id?: number;
    subjectName?: string;
    serialNumber?: string;
    type?: CertificateType;
    certDataContentType?: string;
    certData?: any;
    privateKeyContentType?: string;
    privateKey?: any;
    caName?: string;
    caId?: number;
    templateName?: string;
    templateId?: number;
    identityName?: string;
    identityId?: number;
    logicalCards?: ILogicalCard[];
}

export class Certificate implements ICertificate {
    constructor(
        public id?: number,
        public subjectName?: string,
        public serialNumber?: string,
        public type?: CertificateType,
        public certDataContentType?: string,
        public certData?: any,
        public privateKeyContentType?: string,
        public privateKey?: any,
        public caName?: string,
        public caId?: number,
        public templateName?: string,
        public templateId?: number,
        public identityName?: string,
        public identityId?: number,
        public logicalCards?: ILogicalCard[]
    ) {}
}
