import { Injectable } from '@angular/core';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs';

import { SERVER_API_URL } from 'app/app.constants';
import { createRequestOption } from 'app/shared';
import { ILogicalCard } from 'app/shared/model/logical-card.model';

type EntityResponseType = HttpResponse<ILogicalCard>;
type EntityArrayResponseType = HttpResponse<ILogicalCard[]>;

@Injectable({ providedIn: 'root' })
export class LogicalCardService {
    private resourceUrl = SERVER_API_URL + 'api/logical-cards';

    constructor(private http: HttpClient) {}

    create(logicalCard: ILogicalCard): Observable<EntityResponseType> {
        return this.http.post<ILogicalCard>(this.resourceUrl, logicalCard, { observe: 'response' });
    }

    update(logicalCard: ILogicalCard): Observable<EntityResponseType> {
        return this.http.put<ILogicalCard>(this.resourceUrl, logicalCard, { observe: 'response' });
    }

    find(id: number): Observable<EntityResponseType> {
        return this.http.get<ILogicalCard>(`${this.resourceUrl}/${id}`, { observe: 'response' });
    }

    query(req?: any): Observable<EntityArrayResponseType> {
        const options = createRequestOption(req);
        return this.http.get<ILogicalCard[]>(this.resourceUrl, { params: options, observe: 'response' });
    }

    delete(id: number): Observable<HttpResponse<any>> {
        return this.http.delete<any>(`${this.resourceUrl}/${id}`, { observe: 'response' });
    }
}
