import {Component, OnInit } from '@angular/core';
import {AuthenticationRequest} from "../model/user";
import {AuthenticationService} from "../authentication-service/authentication.service";

@Component({
  selector: 'app-login-form',
  templateUrl: './login-form.component.html',
  styleUrls: ['./login-form.component.css']
})
export class LoginFormComponent implements OnInit {
  authenticationRequest: AuthenticationRequest = {
    login: '',
    password: ''
  }

  constructor(
    protected authService: AuthenticationService,

  ) { }

  ngOnInit(): void {
  }

  login(): void {
    this.authService.authenticate(this.authenticationRequest);
  }

}
