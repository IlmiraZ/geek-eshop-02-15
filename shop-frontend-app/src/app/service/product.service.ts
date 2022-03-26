import { Injectable } from '@angular/core';
import {HttpClient, HttpParams} from "@angular/common/http";
import {Observable} from "rxjs";
import {Page} from "../model/page";

@Injectable({
  providedIn: 'root'
})
export class ProductService {

  constructor(private http: HttpClient) { }

  public findAll(nameFilter?: string, page?: number) : Observable<Page> {
    let params = new HttpParams();
    if(nameFilter) {
      params = params.set('namePattern', nameFilter);
    }
    params = params.set("page", page != null ? page : 1);
    params = params.set("size", 3);
    return this.http.get<Page>('api/v1/product/all', {params})
  }
}
