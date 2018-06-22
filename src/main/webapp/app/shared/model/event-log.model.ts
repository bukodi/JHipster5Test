import { Moment } from 'moment';

export interface IEventLog {
    id?: number;
    instant?: Moment;
    eventTypeFqn?: string;
    node?: string;
    serachKey?: string;
    payload?: string;
}

export class EventLog implements IEventLog {
    constructor(
        public id?: number,
        public instant?: Moment,
        public eventTypeFqn?: string,
        public node?: string,
        public serachKey?: string,
        public payload?: string
    ) {}
}
