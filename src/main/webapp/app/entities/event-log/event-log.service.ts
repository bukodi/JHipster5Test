import { Injectable } from '@angular/core';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs';
import * as moment from 'moment';
import { DATE_FORMAT } from 'app/shared/constants/input.constants';

import { SERVER_API_URL } from 'app/app.constants';
import { createRequestOption } from 'app/shared';
import { IEventLog } from 'app/shared/model/event-log.model';

type EntityResponseType = HttpResponse<IEventLog>;
type EntityArrayResponseType = HttpResponse<IEventLog[]>;

@Injectable({ providedIn: 'root' })
export class EventLogService {
    private resourceUrl = SERVER_API_URL + 'api/event-logs';

    constructor(private http: HttpClient) {}

    create(eventLog: IEventLog): Observable<EntityResponseType> {
        const copy = this.convertDateFromClient(eventLog);
        return this.http
            .post<IEventLog>(this.resourceUrl, copy, { observe: 'response' })
            .map((res: EntityResponseType) => this.convertDateFromServer(res));
    }

    update(eventLog: IEventLog): Observable<EntityResponseType> {
        const copy = this.convertDateFromClient(eventLog);
        return this.http
            .put<IEventLog>(this.resourceUrl, copy, { observe: 'response' })
            .map((res: EntityResponseType) => this.convertDateFromServer(res));
    }

    find(id: number): Observable<EntityResponseType> {
        return this.http
            .get<IEventLog>(`${this.resourceUrl}/${id}`, { observe: 'response' })
            .map((res: EntityResponseType) => this.convertDateFromServer(res));
    }

    query(req?: any): Observable<EntityArrayResponseType> {
        const options = createRequestOption(req);
        return this.http
            .get<IEventLog[]>(this.resourceUrl, { params: options, observe: 'response' })
            .map((res: EntityArrayResponseType) => this.convertDateArrayFromServer(res));
    }

    delete(id: number): Observable<HttpResponse<any>> {
        return this.http.delete<any>(`${this.resourceUrl}/${id}`, { observe: 'response' });
    }

    private convertDateFromClient(eventLog: IEventLog): IEventLog {
        const copy: IEventLog = Object.assign({}, eventLog, {
            instant: eventLog.instant != null && eventLog.instant.isValid() ? eventLog.instant.toJSON() : null
        });
        return copy;
    }

    private convertDateFromServer(res: EntityResponseType): EntityResponseType {
        res.body.instant = res.body.instant != null ? moment(res.body.instant) : null;
        return res;
    }

    private convertDateArrayFromServer(res: EntityArrayResponseType): EntityArrayResponseType {
        res.body.forEach((eventLog: IEventLog) => {
            eventLog.instant = eventLog.instant != null ? moment(eventLog.instant) : null;
        });
        return res;
    }
}
