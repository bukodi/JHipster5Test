import { Injectable } from '@angular/core';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs';

import { SERVER_API_URL } from 'app/app.constants';
import { createRequestOption } from 'app/shared';
import { IIdentity } from 'app/shared/model/identity.model';

type EntityResponseType = HttpResponse<IIdentity>;
type EntityArrayResponseType = HttpResponse<IIdentity[]>;

@Injectable({ providedIn: 'root' })
export class IdentityService {
    private resourceUrl = SERVER_API_URL + 'api/identities';

    constructor(private http: HttpClient) {}

    create(identity: IIdentity): Observable<EntityResponseType> {
        return this.http.post<IIdentity>(this.resourceUrl, identity, { observe: 'response' });
    }

    update(identity: IIdentity): Observable<EntityResponseType> {
        return this.http.put<IIdentity>(this.resourceUrl, identity, { observe: 'response' });
    }

    find(id: number): Observable<EntityResponseType> {
        return this.http.get<IIdentity>(`${this.resourceUrl}/${id}`, { observe: 'response' });
    }

    query(req?: any): Observable<EntityArrayResponseType> {
        const options = createRequestOption(req);
        return this.http.get<IIdentity[]>(this.resourceUrl, { params: options, observe: 'response' });
    }

    delete(id: number): Observable<HttpResponse<any>> {
        return this.http.delete<any>(`${this.resourceUrl}/${id}`, { observe: 'response' });
    }
}
