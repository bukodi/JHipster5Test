import { Injectable } from '@angular/core';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs';

import { SERVER_API_URL } from 'app/app.constants';
import { createRequestOption } from 'app/shared';
import { IDirectoryServer } from 'app/shared/model/directory-server.model';

type EntityResponseType = HttpResponse<IDirectoryServer>;
type EntityArrayResponseType = HttpResponse<IDirectoryServer[]>;

@Injectable({ providedIn: 'root' })
export class DirectoryServerService {
    private resourceUrl = SERVER_API_URL + 'api/directory-servers';

    constructor(private http: HttpClient) {}

    create(directoryServer: IDirectoryServer): Observable<EntityResponseType> {
        return this.http.post<IDirectoryServer>(this.resourceUrl, directoryServer, { observe: 'response' });
    }

    update(directoryServer: IDirectoryServer): Observable<EntityResponseType> {
        return this.http.put<IDirectoryServer>(this.resourceUrl, directoryServer, { observe: 'response' });
    }

    find(id: number): Observable<EntityResponseType> {
        return this.http.get<IDirectoryServer>(`${this.resourceUrl}/${id}`, { observe: 'response' });
    }

    query(req?: any): Observable<EntityArrayResponseType> {
        const options = createRequestOption(req);
        return this.http.get<IDirectoryServer[]>(this.resourceUrl, { params: options, observe: 'response' });
    }

    delete(id: number): Observable<HttpResponse<any>> {
        return this.http.delete<any>(`${this.resourceUrl}/${id}`, { observe: 'response' });
    }
}
