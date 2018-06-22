export const enum CertificateType {
    SIGN = 'SIGN',
    AUTH = 'AUTH',
    ENCRYPT = 'ENCRYPT',
    DEVICE = 'DEVICE'
}

export const enum PKILocation {
    ENDENTITY = 'ENDENTITY',
    CMS = 'CMS',
    CA = 'CA'
}

export interface ICertificateTemplate {
    id?: number;
    name?: string;
    type?: CertificateType;
    keyGeneration?: PKILocation;
    keyArchivation?: PKILocation;
    caName?: string;
    caId?: number;
    ca2Name?: string;
    ca2Id?: number;
}

export class CertificateTemplate implements ICertificateTemplate {
    constructor(
        public id?: number,
        public name?: string,
        public type?: CertificateType,
        public keyGeneration?: PKILocation,
        public keyArchivation?: PKILocation,
        public caName?: string,
        public caId?: number,
        public ca2Name?: string,
        public ca2Id?: number
    ) {}
}
