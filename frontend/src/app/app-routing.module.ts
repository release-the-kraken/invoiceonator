import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import {RouterModule, Routes} from "@angular/router";
import {TestComponent} from "./test/test.component";
import {LoginFormComponent} from "./login-form/login-form.component";
import {UserPanelComponent} from "./user-panel/user-panel.component";
import {AllCompaniesListComponent} from "./Company/list/all-companies-list/all-companies-list.component";
import {UserCompaniesListComponent} from "./Company/list/user-companies-list/user-companies-list.component";
import {AllInvoicesListComponent} from "./Invoice/list/all-invoices-list/all-invoices-list.component";
import {UserInvoicesListComponent} from "./Invoice/list/user-invoices-list/user-invoices-list.component";
import {UserListComponent} from "./User/user-list/user-list.component";

const routes: Routes = [
  { path: "", redirectTo: "login", pathMatch: "full" },
  { path: "test", component: TestComponent },
  { path: "login", component: LoginFormComponent },
  { path: "user-panel", component: UserPanelComponent },
  { path: "all-companies", component: AllCompaniesListComponent },
  { path: "user-companies", component: UserCompaniesListComponent },
  { path: "all-invoices", component: AllInvoicesListComponent },
  { path: "user-invoices", component: UserInvoicesListComponent },
  { path: "users", component:  UserListComponent},
];

@NgModule({
  declarations: [],
  imports: [
    CommonModule,
    RouterModule.forRoot(routes)
  ],
  exports: [RouterModule]
})
export class AppRoutingModule { }
