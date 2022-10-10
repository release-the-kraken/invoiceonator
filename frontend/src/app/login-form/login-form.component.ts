import {Component, OnInit, Renderer2} from '@angular/core';
import {AuthenticationRequest} from "../model/user";
import {MatSnackBar} from "@angular/material/snack-bar";
import {AuthenticationService} from "../authentication-service/authentication.service";
import {Router} from "@angular/router";



@Component({
  selector: 'app-login-form',
  templateUrl: './login-form.component.html',
  styleUrls: ['./login-form.component.css']
})
export class LoginFormComponent implements OnInit {
  notification: string | any = null;
  // loggingIn: boolean = false;

  authenticationRequest: AuthenticationRequest = {
    login: '',
    password: ''
  }

  constructor(
    protected authService: AuthenticationService,
    private snackBar: MatSnackBar,
    private router: Router,
    private renderer: Renderer2
  ) { }

  ngOnInit(): void {
  }

  login(): void {
    this.authService.authenticate(this.authenticationRequest);
  }

  clearForm(): void {
    this.authenticationRequest = {
      login: '',
      password: ''
    }
  }
}
