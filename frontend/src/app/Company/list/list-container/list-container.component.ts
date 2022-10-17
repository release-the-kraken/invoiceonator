import {Component, EventEmitter, Input, OnInit, Output} from '@angular/core';
import {CompanyDTO} from "../../../model/company";
import {PageEvent} from "@angular/material/paginator";
import {MatDialog} from "@angular/material/dialog";
import {CompanyFormComponent} from "../../company-form/company-form.component";
import {AuthenticationService} from "../../../authentication-service/authentication.service";

@Component({
  selector: 'app-list-container',
  templateUrl: './list-container.component.html',
  styleUrls: ['./list-container.component.css']
})
export class ListContainerComponent implements OnInit {
  @Input() companies: CompanyDTO[] = [];
  @Input() hiddenColumnNames: string[] =[];
  @Input('totalElements') totalElements: number = 0;

  @Output() loadCompaniesEvent = new EventEmitter<PageEvent>();

  visibleColumnNames : string[] = [
    'name',
    'countryOfOrigin',
    'industry',
    'userFullName'
  ]

  constructor( private dialog: MatDialog,
               public authService: AuthenticationService) {
  }
  getVisibleColumnNames(): string[]{
    const visibleColumns = []
    for (let i = 0; i < this.visibleColumnNames.length; i++) {
      if(this.hiddenColumnNames.includes(this.visibleColumnNames[i])){
        continue
      }

      visibleColumns.push(this.visibleColumnNames[i]);
    }
    return visibleColumns
  }
  ngOnInit(): void {
    this.loadCompaniesEvent.emit({
      pageSize: 5,
      pageIndex: 0,
      length: 0
    })
  }
  loadCompanies(event?: PageEvent){
    this.loadCompaniesEvent.emit(event);
  }
  openDialog() {
    this.dialog.open(CompanyFormComponent);
  }
}
