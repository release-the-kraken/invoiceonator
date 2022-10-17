import {Component, OnInit} from '@angular/core';
import {PageResponse} from "../../../model/pagination";
import {CompanyDTO} from "../../../model/company";
import {PageEvent} from "@angular/material/paginator";
import {CompanyService} from "../../service/company.service";

@Component({
  selector: 'app-user-companies-list',
  templateUrl: './user-companies-list.component.html',
  styleUrls: ['./user-companies-list.component.css']
})
export class UserCompaniesListComponent implements OnInit {
  pageResponse: PageResponse<CompanyDTO> = {
    content: [],
    totalElements: 0
  }
  pageEvent?: PageEvent;
  companies: CompanyDTO[] = [];
  constructor(private companyService: CompanyService) { }

  ngOnInit(): void {

  }
  loadPage(pageEvent?: PageEvent):void{
    this.companyService.getAllForUser(pageEvent?.pageIndex, pageEvent?.pageSize)
      .subscribe({
        next: responseData => {
          this.pageResponse = responseData as PageResponse<CompanyDTO>;
        },
        error: err => {
          console.log(err)
        }
      })

  }
  getHiddenColumnNames(): string[]{
    return ['userFullName'];
  }
}
