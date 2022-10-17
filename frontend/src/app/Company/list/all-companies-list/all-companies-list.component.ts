import {Component, OnInit} from '@angular/core';
import {CompanyDTO} from "../../../model/company";
import {CompanyService} from "../../service/company.service";
import {PageResponse} from "../../../model/pagination";
import {PageEvent} from "@angular/material/paginator";

@Component({
  selector: 'app-all-companies-list',
  templateUrl: './all-companies-list.component.html',
  styleUrls: ['./all-companies-list.component.css']
})
export class AllCompaniesListComponent implements OnInit {
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
    this.companyService.getAll(pageEvent?.pageIndex, pageEvent?.pageSize)
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
    return [];
  }
}
