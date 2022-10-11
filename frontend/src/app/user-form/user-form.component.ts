import { Component, OnInit } from '@angular/core';
import {UserService} from "../user-service/user.service";
import Utils from "../utils";
import {Router} from "@angular/router";
import {CreateUserRequest} from "../model/user";
import {MatDialog} from "@angular/material/dialog";

@Component({
  selector: 'app-user-form',
  templateUrl: './user-form.component.html',
  styleUrls: ['./user-form.component.css']
})
export class UserFormComponent implements OnInit {
  userRequest : CreateUserRequest;
  constructor(private userService: UserService,
              private utils: Utils,
              private dialog: MatDialog) {
    this.userRequest = userService.getDefaultUserRequest();
  }

  ngOnInit(): void {
  }

  addUser() {
    this.userService.registerUser(this.userRequest)
      .subscribe({
        next: () => {
          this.utils.getSnackBar('Successfully added user.');
        },
        error: (e)=>{
          this.utils.getSnackBar('Failed to add user. Username or password already taken.');
        }
      })
  }
}
