import { Injectable } from '@angular/core';
import {CreateUserRequest} from "../model/user";
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
      pass: "",
      name: "",
      surname: ""
    }
  }

  public registerUser(createUserRequest: CreateUserRequest) : Observable<Object>{
    return this.httpClient.post("http://localhost:8080/api/user", createUserRequest);
  }
}
