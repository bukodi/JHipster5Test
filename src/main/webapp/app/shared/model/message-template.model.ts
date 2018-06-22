import { Moment } from 'moment';

export interface IMessageTemplate {
    id?: number;
    instant?: Moment;
    eventTypeFqn?: string;
    template?: boolean;
    source?: string;
}

export class MessageTemplate implements IMessageTemplate {
    constructor(
        public id?: number,
        public instant?: Moment,
        public eventTypeFqn?: string,
        public template?: boolean,
        public source?: string
    ) {
        this.template = false;
    }
}
