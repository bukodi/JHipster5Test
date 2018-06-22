import { Injectable } from '@angular/core';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs';

import { SERVER_API_URL } from 'app/app.constants';
import { createRequestOption } from 'app/shared';
import { IPhysicalCard } from 'app/shared/model/physical-card.model';

type EntityResponseType = HttpResponse<IPhysicalCard>;
type EntityArrayResponseType = HttpResponse<IPhysicalCard[]>;

@Injectable({ providedIn: 'root' })
export class PhysicalCardService {
    private resourceUrl = SERVER_API_URL + 'api/physical-cards';

    constructor(private http: HttpClient) {}

    create(physicalCard: IPhysicalCard): Observable<EntityResponseType> {
        return this.http.post<IPhysicalCard>(this.resourceUrl, physicalCard, { observe: 'response' });
    }

    update(physicalCard: IPhysicalCard): Observable<EntityResponseType> {
        return this.http.put<IPhysicalCard>(this.resourceUrl, physicalCard, { observe: 'response' });
    }

    find(id: number): Observable<EntityResponseType> {
        return this.http.get<IPhysicalCard>(`${this.resourceUrl}/${id}`, { observe: 'response' });
    }

    query(req?: any): Observable<EntityArrayResponseType> {
        const options = createRequestOption(req);
        return this.http.get<IPhysicalCard[]>(this.resourceUrl, { params: options, observe: 'response' });
    }

    delete(id: number): Observable<HttpResponse<any>> {
        return this.http.delete<any>(`${this.resourceUrl}/${id}`, { observe: 'response' });
    }
}
