import {Component, OnInit} from '@angular/core';
import {PageResponse} from "../../../model/pagination";
import {InvoiceDTO} from "../../../model/invoice";
import {PageEvent} from "@angular/material/paginator";
import {CompanyDTO} from "../../../model/company";
import {InvoiceService} from "../../service/invoice.service";

@Component({
  selector: 'app-user-invoices-list',
  templateUrl: './user-invoices-list.component.html',
  styleUrls: ['./user-invoices-list.component.css']
})
export class UserInvoicesListComponent implements OnInit {
  pageResponse: PageResponse<InvoiceDTO> = {
    content: [],
    totalElements: 0
  }
  pageEvent?: PageEvent;
  companies: CompanyDTO[] = [];
  constructor(private invoiceService: InvoiceService) { }

  ngOnInit(): void {

  }
  loadPage(pageEvent?: PageEvent):void{
    this.invoiceService.getAllForUser(pageEvent?.pageIndex, pageEvent?.pageSize)
      .subscribe({
        next: responseData => {
          this.pageResponse = responseData as PageResponse<InvoiceDTO>;
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
