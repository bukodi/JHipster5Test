import { Injectable } from '@angular/core';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs';

import { SERVER_API_URL } from 'app/app.constants';
import { createRequestOption } from 'app/shared';
import { ICustomProcess } from 'app/shared/model/custom-process.model';

type EntityResponseType = HttpResponse<ICustomProcess>;
type EntityArrayResponseType = HttpResponse<ICustomProcess[]>;

@Injectable({ providedIn: 'root' })
export class CustomProcessService {
    private resourceUrl = SERVER_API_URL + 'api/custom-processes';

    constructor(private http: HttpClient) {}

    create(customProcess: ICustomProcess): Observable<EntityResponseType> {
        return this.http.post<ICustomProcess>(this.resourceUrl, customProcess, { observe: 'response' });
    }

    update(customProcess: ICustomProcess): Observable<EntityResponseType> {
        return this.http.put<ICustomProcess>(this.resourceUrl, customProcess, { observe: 'response' });
    }

    find(id: number): Observable<EntityResponseType> {
        return this.http.get<ICustomProcess>(`${this.resourceUrl}/${id}`, { observe: 'response' });
    }

    query(req?: any): Observable<EntityArrayResponseType> {
        const options = createRequestOption(req);
        return this.http.get<ICustomProcess[]>(this.resourceUrl, { params: options, observe: 'response' });
    }

    delete(id: number): Observable<HttpResponse<any>> {
        return this.http.delete<any>(`${this.resourceUrl}/${id}`, { observe: 'response' });
    }
}
