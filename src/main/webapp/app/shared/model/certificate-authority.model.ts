import { ICertificateTemplate } from 'app/shared/model//certificate-template.model';

export interface ICertificateAuthority {
    id?: number;
    name?: string;
    host?: string;
    customizationSource?: string;
    customizationClassContentType?: string;
    customizationClass?: any;
    template2S?: ICertificateTemplate[];
    templates?: ICertificateTemplate[];
}

export class CertificateAuthority implements ICertificateAuthority {
    constructor(
        public id?: number,
        public name?: string,
        public host?: string,
        public customizationSource?: string,
        public customizationClassContentType?: string,
        public customizationClass?: any,
        public template2S?: ICertificateTemplate[],
        public templates?: ICertificateTemplate[]
    ) {}
}
