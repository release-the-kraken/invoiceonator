import { Injectable } from '@angular/core';
import {HttpClient} from "@angular/common/http";
import {Observable} from "rxjs";
import {BACKEND_BASE_URL} from "../../model/constants";
import {AuthenticationService} from "../../authentication-service/authentication.service";

@Injectable({
  providedIn: 'root'
})
export class CompanyService {

  constructor(private httpClient: HttpClient,
              private authService : AuthenticationService) { }

  public getAll(page?: number | null, size?: number | null): Observable<Object> {
    console.log('called companyService.getAll')
    let params = {
      page: (page ? page : 0),
      size: (size ? size : 5)
    }

    return this.httpClient.get(BACKEND_BASE_URL + "company", {params: params});
  }
  public getAllForUser(page?: number | null, size?: number | null): Observable<Object> {
    console.log('called companyService.getAllForUser')
    let params = {
      page: (page ? page : 0),
      size: (size ? size : 5)
    }
    let userId = this.authService.getUserId();
    return this.httpClient.get(BACKEND_BASE_URL + "company/" + userId, {params: params});
  }
}
