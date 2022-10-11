import {Component, OnInit, Renderer2} from '@angular/core';
import {AuthenticationRequest} from "../model/user";
import {MatSnackBar} from "@angular/material/snack-bar";
import {AuthenticationService} from "../authentication-service/authentication.service";
import {Router} from "@angular/router";
import {MatDialog} from "@angular/material/dialog";
import {UserFormComponent} from "../user-form/user-form.component";



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
    private renderer: Renderer2,
    private dialog: MatDialog
  ) { }

  ngOnInit(): void {
  }
  openDialog() {
    this.dialog.open(UserFormComponent);
  }
  login(): void {
    this.authService.authenticate(this.authenticationRequest);
  }

  addUser() {

  }
}
