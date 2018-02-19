import { Injectable } from '@angular/core';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs/Observable';
import { SERVER_API_URL } from '../../app.constants';

import { StakeHolderMySuffix } from './stake-holder-my-suffix.model';
import { createRequestOption } from '../../shared';

export type EntityResponseType = HttpResponse<StakeHolderMySuffix>;

@Injectable()
export class StakeHolderMySuffixService {

    private resourceUrl =  SERVER_API_URL + 'api/stake-holders';

    constructor(private http: HttpClient) { }

    create(stakeHolder: StakeHolderMySuffix): Observable<EntityResponseType> {
        const copy = this.convert(stakeHolder);
        return this.http.post<StakeHolderMySuffix>(this.resourceUrl, copy, { observe: 'response' })
            .map((res: EntityResponseType) => this.convertResponse(res));
    }

    update(stakeHolder: StakeHolderMySuffix): Observable<EntityResponseType> {
        const copy = this.convert(stakeHolder);
        return this.http.put<StakeHolderMySuffix>(this.resourceUrl, copy, { observe: 'response' })
            .map((res: EntityResponseType) => this.convertResponse(res));
    }

    find(id: number): Observable<EntityResponseType> {
        return this.http.get<StakeHolderMySuffix>(`${this.resourceUrl}/${id}`, { observe: 'response'})
            .map((res: EntityResponseType) => this.convertResponse(res));
    }

    query(req?: any): Observable<HttpResponse<StakeHolderMySuffix[]>> {
        const options = createRequestOption(req);
        return this.http.get<StakeHolderMySuffix[]>(this.resourceUrl, { params: options, observe: 'response' })
            .map((res: HttpResponse<StakeHolderMySuffix[]>) => this.convertArrayResponse(res));
    }

    delete(id: number): Observable<HttpResponse<any>> {
        return this.http.delete<any>(`${this.resourceUrl}/${id}`, { observe: 'response'});
    }

    private convertResponse(res: EntityResponseType): EntityResponseType {
        const body: StakeHolderMySuffix = this.convertItemFromServer(res.body);
        return res.clone({body});
    }

    private convertArrayResponse(res: HttpResponse<StakeHolderMySuffix[]>): HttpResponse<StakeHolderMySuffix[]> {
        const jsonResponse: StakeHolderMySuffix[] = res.body;
        const body: StakeHolderMySuffix[] = [];
        for (let i = 0; i < jsonResponse.length; i++) {
            body.push(this.convertItemFromServer(jsonResponse[i]));
        }
        return res.clone({body});
    }

    /**
     * Convert a returned JSON object to StakeHolderMySuffix.
     */
    private convertItemFromServer(stakeHolder: StakeHolderMySuffix): StakeHolderMySuffix {
        const copy: StakeHolderMySuffix = Object.assign({}, stakeHolder);
        return copy;
    }

    /**
     * Convert a StakeHolderMySuffix to a JSON which can be sent to the server.
     */
    private convert(stakeHolder: StakeHolderMySuffix): StakeHolderMySuffix {
        const copy: StakeHolderMySuffix = Object.assign({}, stakeHolder);
        return copy;
    }
}
