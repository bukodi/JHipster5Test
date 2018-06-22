export interface IDirectoryServer {
    id?: number;
    name?: string;
    host?: string;
    customizationSource?: string;
    customizationClassContentType?: string;
    customizationClass?: any;
}

export class DirectoryServer implements IDirectoryServer {
    constructor(
        public id?: number,
        public name?: string,
        public host?: string,
        public customizationSource?: string,
        public customizationClassContentType?: string,
        public customizationClass?: any
    ) {}
}
