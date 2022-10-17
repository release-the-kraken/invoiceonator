import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';

import { AppComponent } from './app.component';
import { BrowserAnimationsModule } from '@angular/platform-browser/animations';
import { AppRoutingModule } from './app-routing.module';
import { LoginFormComponent } from './login-form/login-form.component';
import { TestComponent } from './test/test.component';
import {MatButtonModule} from "@angular/material/button";
import {MatCardModule} from "@angular/material/card";
import {MatFormFieldModule} from "@angular/material/form-field";
import {MatInputModule} from "@angular/material/input";
import {FormsModule} from "@angular/forms";
import {UserService} from "./User/user-service/user.service";
import { HttpClientModule, HTTP_INTERCEPTORS} from "@angular/common/http";
import {AuthenticationInterceptor} from "./authentication-service/authentication.interceptor";
import {AdminRoleGuard} from "./authentication-service/admin-role.guard";
import {AuthenticationGuard} from "./authentication-service/authentication.guard";
import {AuthenticationService} from "./authentication-service/authentication.service";
import {MatSnackBarModule} from "@angular/material/snack-bar";
import { NgbModule } from '@ng-bootstrap/ng-bootstrap';
import {MatDialogModule} from "@angular/material/dialog";
import { UserFormComponent } from './User/user-form/user-form.component';
import { ListContainerComponent } from './Company/list/list-container/list-container.component';
import { AllCompaniesListComponent } from './Company/list/all-companies-list/all-companies-list.component';
import { UserCompaniesListComponent } from './Company/list/user-companies-list/user-companies-list.component';
import { CompanyFormComponent } from './Company/company-form/company-form.component';
import { InvoiceListContainerComponent } from './Invoice/list/invoice-list-container/invoice-list-container.component';
import { AllInvoicesListComponent } from './Invoice/list/all-invoices-list/all-invoices-list.component';
import { UserInvoicesListComponent } from './Invoice/list/user-invoices-list/user-invoices-list.component';
import { InvoiceFormComponent } from './Invoice/invoice-form/invoice-form.component';
import { UserPanelComponent } from './user-panel/user-panel.component';
import {MatButtonToggleModule} from "@angular/material/button-toggle";
import { UserListComponent } from './User/user-list/user-list.component';
import {MatTableModule} from "@angular/material/table";
import {MatPaginatorModule} from "@angular/material/paginator";

@NgModule({
  declarations: [
    AppComponent,
    LoginFormComponent,
    TestComponent,
    UserFormComponent,
    ListContainerComponent,
    AllCompaniesListComponent,
    UserCompaniesListComponent,
    CompanyFormComponent,
    InvoiceListContainerComponent,
    AllInvoicesListComponent,
    UserInvoicesListComponent,
    InvoiceFormComponent,
    UserPanelComponent,
    UserListComponent
  ],
  imports: [
    BrowserModule,
    BrowserAnimationsModule,
    AppRoutingModule,
    MatButtonModule,
    MatCardModule,
    MatFormFieldModule,
    MatInputModule,
    FormsModule,
    HttpClientModule,
    MatSnackBarModule,
    NgbModule,
    MatDialogModule,
    MatButtonToggleModule,
    MatTableModule,
    MatPaginatorModule
  ],
  providers: [
    UserService,
    {
      provide: HTTP_INTERCEPTORS,
      useClass: AuthenticationInterceptor,
      multi: true
    },
    AdminRoleGuard,
    AuthenticationGuard,
    AuthenticationService
  ],
  bootstrap: [AppComponent]
})
export class AppModule { }
