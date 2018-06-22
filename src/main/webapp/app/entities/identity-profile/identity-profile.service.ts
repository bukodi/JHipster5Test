import { Injectable } from '@angular/core';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs';

import { SERVER_API_URL } from 'app/app.constants';
import { createRequestOption } from 'app/shared';
import { IIdentityProfile } from 'app/shared/model/identity-profile.model';

type EntityResponseType = HttpResponse<IIdentityProfile>;
type EntityArrayResponseType = HttpResponse<IIdentityProfile[]>;

@Injectable({ providedIn: 'root' })
export class IdentityProfileService {
    private resourceUrl = SERVER_API_URL + 'api/identity-profiles';

    constructor(private http: HttpClient) {}

    create(identityProfile: IIdentityProfile): Observable<EntityResponseType> {
        return this.http.post<IIdentityProfile>(this.resourceUrl, identityProfile, { observe: 'response' });
    }

    update(identityProfile: IIdentityProfile): Observable<EntityResponseType> {
        return this.http.put<IIdentityProfile>(this.resourceUrl, identityProfile, { observe: 'response' });
    }

    find(id: number): Observable<EntityResponseType> {
        return this.http.get<IIdentityProfile>(`${this.resourceUrl}/${id}`, { observe: 'response' });
    }

    query(req?: any): Observable<EntityArrayResponseType> {
        const options = createRequestOption(req);
        return this.http.get<IIdentityProfile[]>(this.resourceUrl, { params: options, observe: 'response' });
    }

    delete(id: number): Observable<HttpResponse<any>> {
        return this.http.delete<any>(`${this.resourceUrl}/${id}`, { observe: 'response' });
    }
}
