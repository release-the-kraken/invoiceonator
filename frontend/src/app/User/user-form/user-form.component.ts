import { Component, OnInit } from '@angular/core';
import {UserService} from "../user-service/user.service";
import Utils from "../../utils";
import {CreateUserRequest} from "../../model/user";

@Component({
  selector: 'app-user-form',
  templateUrl: './user-form.component.html',
  styleUrls: ['./user-form.component.css']
})
export class UserFormComponent implements OnInit {
  userRequest : CreateUserRequest;
  constructor(private userService: UserService,
              private utils: Utils) {
    this.userRequest = userService.getDefaultUserRequest();
  }

  ngOnInit(): void {
  }

  addUser() {
    this.userService.registerUser(this.userRequest)
      .subscribe({
        next: () => {
          this.utils.getSnackBar('Successfully added user.');
          this.userService.getAll(0, 3);
        },
        error: ()=>{
          this.utils.getSnackBar('Failed to add user. Username or password already taken.');
        }
      })
  }
}
