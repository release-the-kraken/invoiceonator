import { Injectable } from '@angular/core';
import {CreateUserRequest} from "../../model/user";
import {HttpClient} from "@angular/common/http";
import {Observable} from "rxjs";

@Injectable({
  providedIn: 'root'
})
export class UserService {

  constructor(private httpClient: HttpClient) { }

  public getDefaultUserRequest(): CreateUserRequest {
    return {
      login: "",
      password: "",
      name: "",
      surname: ""
    }
  }
  public getAll(page?: number | null, size?: number | null): Observable<Object> {
    console.log('called userService.getAll')
    let params = {
      page: (page ? page : 0),
      size: (size ? size : 3)
    }

    return this.httpClient.get("http://localhost:8080/api/user", {params: params});

  }
  public registerUser(createUserRequest: CreateUserRequest) : Observable<Object>{
    return this.httpClient.post("http://localhost:8080/api/user", createUserRequest);
  }
}
