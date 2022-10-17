import { Component, OnInit } from '@angular/core';
import {UserDTO} from "../../model/user";
import {PageResponse} from "../../model/pagination";
import {PageEvent} from "@angular/material/paginator";
import {UserService} from "../user-service/user.service";
import {UserFormComponent} from "../user-form/user-form.component";
import {MatDialog} from "@angular/material/dialog";

@Component({
  selector: 'app-user-list',
  templateUrl: './user-list.component.html',
  styleUrls: ['./user-list.component.css']
})
export class UserListComponent implements OnInit {
  pageResponse: PageResponse<UserDTO> = {
    content: [],
    totalElements: 0
  }
  pageEvent?: PageEvent;
  users: UserDTO[] = [];
  displayedColumns: string[] = [
    'id',
    'login',
    'name',
    'surname'
  ]
  constructor(private userService: UserService,
              private dialog: MatDialog) { }

  ngOnInit(): void {
    this.changePage(this.pageEvent)
  }
  openDialog() {
    this.dialog.open(UserFormComponent);
  }
  changePage(pageEvent?: PageEvent): void {
    this.pageEvent = pageEvent;
    console.log('users list changePage');
    this.userService.getAll(pageEvent?.pageIndex, pageEvent?.pageSize)
      .subscribe({
        next: (responseData) => {
          console.log(responseData);
          let usersFromResponse = responseData as PageResponse<UserDTO>;
          console.log('users from response: ' + usersFromResponse)
          this.pageResponse = usersFromResponse;
        },
        error: (e) => {
          console.log(e.message);
        }
      })
  }
}
