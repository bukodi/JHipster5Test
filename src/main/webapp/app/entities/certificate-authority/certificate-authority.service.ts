import { Injectable } from '@angular/core';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs';

import { SERVER_API_URL } from 'app/app.constants';
import { createRequestOption } from 'app/shared';
import { ICertificateAuthority } from 'app/shared/model/certificate-authority.model';

type EntityResponseType = HttpResponse<ICertificateAuthority>;
type EntityArrayResponseType = HttpResponse<ICertificateAuthority[]>;

@Injectable({ providedIn: 'root' })
export class CertificateAuthorityService {
    private resourceUrl = SERVER_API_URL + 'api/certificate-authorities';

    constructor(private http: HttpClient) {}

    create(certificateAuthority: ICertificateAuthority): Observable<EntityResponseType> {
        return this.http.post<ICertificateAuthority>(this.resourceUrl, certificateAuthority, { observe: 'response' });
    }

    update(certificateAuthority: ICertificateAuthority): Observable<EntityResponseType> {
        return this.http.put<ICertificateAuthority>(this.resourceUrl, certificateAuthority, { observe: 'response' });
    }

    find(id: number): Observable<EntityResponseType> {
        return this.http.get<ICertificateAuthority>(`${this.resourceUrl}/${id}`, { observe: 'response' });
    }

    query(req?: any): Observable<EntityArrayResponseType> {
        const options = createRequestOption(req);
        return this.http.get<ICertificateAuthority[]>(this.resourceUrl, { params: options, observe: 'response' });
    }

    delete(id: number): Observable<HttpResponse<any>> {
        return this.http.delete<any>(`${this.resourceUrl}/${id}`, { observe: 'response' });
    }
}
