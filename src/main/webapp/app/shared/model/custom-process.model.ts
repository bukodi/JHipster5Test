export interface ICustomProcess {
    id?: number;
    name?: string;
    interfaceFqn?: string;
    template?: boolean;
    source?: string;
    scheduling?: string;
}

export class CustomProcess implements ICustomProcess {
    constructor(
        public id?: number,
        public name?: string,
        public interfaceFqn?: string,
        public template?: boolean,
        public source?: string,
        public scheduling?: string
    ) {
        this.template = false;
    }
}
