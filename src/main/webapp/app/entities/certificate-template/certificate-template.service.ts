import { Injectable } from '@angular/core';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs';

import { SERVER_API_URL } from 'app/app.constants';
import { createRequestOption } from 'app/shared';
import { ICertificateTemplate } from 'app/shared/model/certificate-template.model';

type EntityResponseType = HttpResponse<ICertificateTemplate>;
type EntityArrayResponseType = HttpResponse<ICertificateTemplate[]>;

@Injectable({ providedIn: 'root' })
export class CertificateTemplateService {
    private resourceUrl = SERVER_API_URL + 'api/certificate-templates';

    constructor(private http: HttpClient) {}

    create(certificateTemplate: ICertificateTemplate): Observable<EntityResponseType> {
        return this.http.post<ICertificateTemplate>(this.resourceUrl, certificateTemplate, { observe: 'response' });
    }

    update(certificateTemplate: ICertificateTemplate): Observable<EntityResponseType> {
        return this.http.put<ICertificateTemplate>(this.resourceUrl, certificateTemplate, { observe: 'response' });
    }

    find(id: number): Observable<EntityResponseType> {
        return this.http.get<ICertificateTemplate>(`${this.resourceUrl}/${id}`, { observe: 'response' });
    }

    query(req?: any): Observable<EntityArrayResponseType> {
        const options = createRequestOption(req);
        return this.http.get<ICertificateTemplate[]>(this.resourceUrl, { params: options, observe: 'response' });
    }

    delete(id: number): Observable<HttpResponse<any>> {
        return this.http.delete<any>(`${this.resourceUrl}/${id}`, { observe: 'response' });
    }
}
