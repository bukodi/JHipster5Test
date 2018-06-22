import { Injectable } from '@angular/core';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs';
import * as moment from 'moment';
import { DATE_FORMAT } from 'app/shared/constants/input.constants';

import { SERVER_API_URL } from 'app/app.constants';
import { createRequestOption } from 'app/shared';
import { IMessageTemplate } from 'app/shared/model/message-template.model';

type EntityResponseType = HttpResponse<IMessageTemplate>;
type EntityArrayResponseType = HttpResponse<IMessageTemplate[]>;

@Injectable({ providedIn: 'root' })
export class MessageTemplateService {
    private resourceUrl = SERVER_API_URL + 'api/message-templates';

    constructor(private http: HttpClient) {}

    create(messageTemplate: IMessageTemplate): Observable<EntityResponseType> {
        const copy = this.convertDateFromClient(messageTemplate);
        return this.http
            .post<IMessageTemplate>(this.resourceUrl, copy, { observe: 'response' })
            .map((res: EntityResponseType) => this.convertDateFromServer(res));
    }

    update(messageTemplate: IMessageTemplate): Observable<EntityResponseType> {
        const copy = this.convertDateFromClient(messageTemplate);
        return this.http
            .put<IMessageTemplate>(this.resourceUrl, copy, { observe: 'response' })
            .map((res: EntityResponseType) => this.convertDateFromServer(res));
    }

    find(id: number): Observable<EntityResponseType> {
        return this.http
            .get<IMessageTemplate>(`${this.resourceUrl}/${id}`, { observe: 'response' })
            .map((res: EntityResponseType) => this.convertDateFromServer(res));
    }

    query(req?: any): Observable<EntityArrayResponseType> {
        const options = createRequestOption(req);
        return this.http
            .get<IMessageTemplate[]>(this.resourceUrl, { params: options, observe: 'response' })
            .map((res: EntityArrayResponseType) => this.convertDateArrayFromServer(res));
    }

    delete(id: number): Observable<HttpResponse<any>> {
        return this.http.delete<any>(`${this.resourceUrl}/${id}`, { observe: 'response' });
    }

    private convertDateFromClient(messageTemplate: IMessageTemplate): IMessageTemplate {
        const copy: IMessageTemplate = Object.assign({}, messageTemplate, {
            instant: messageTemplate.instant != null && messageTemplate.instant.isValid() ? messageTemplate.instant.toJSON() : null
        });
        return copy;
    }

    private convertDateFromServer(res: EntityResponseType): EntityResponseType {
        res.body.instant = res.body.instant != null ? moment(res.body.instant) : null;
        return res;
    }

    private convertDateArrayFromServer(res: EntityArrayResponseType): EntityArrayResponseType {
        res.body.forEach((messageTemplate: IMessageTemplate) => {
            messageTemplate.instant = messageTemplate.instant != null ? moment(messageTemplate.instant) : null;
        });
        return res;
    }
}
