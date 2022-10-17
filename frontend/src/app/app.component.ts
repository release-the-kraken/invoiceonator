import { Component } from '@angular/core';
import {AuthenticationService} from "./authentication-service/authentication.service";

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.css']
})
export class AppComponent {
  title = 'invoice-O-matic';

  constructor(public authService: AuthenticationService) {
  }
}
