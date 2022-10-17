import { Component, OnInit } from '@angular/core';
import {HttpClient} from "@angular/common/http";
import {MatSnackBar} from "@angular/material/snack-bar";
import {AuthenticationService} from "../authentication-service/authentication.service";

@Component({
  selector: 'app-test',
  templateUrl: './test.component.html',
  styleUrls: ['./test.component.css']
})
export class TestComponent implements OnInit {

  constructor(
    private httpClient: HttpClient,
    private snackBar: MatSnackBar,
    public authService: AuthenticationService
  ) { }

  ngOnInit(): void {
  }

  call(type: string) {
    this.httpClient.get("http://localhost:8080/api/test/" + type)
      .subscribe({
        next: () => {
          this.snackBar.open(`${type} request succeeded`, undefined, {
            verticalPosition: 'top',
            horizontalPosition: 'end',
            duration: 1500
          })
        },
        error: () => {
          this.snackBar.open(`${type} request FAILED`, undefined, {
            verticalPosition: 'bottom',
            horizontalPosition: 'end',
            duration: 1500
          })
        }
      })
  }

  callPublic() {
    console.log("Call Public")
    this.call("public");
  }

  callAnyone() {
    console.log("Call Anyone")
    this.call("anyone");
  }

  callModerator() {
    console.log("Call Moderator")
    this.call("moderator");
  }
  callAdmin() {
    console.log("Call Admin")
    this.call("admin");
  }
}
