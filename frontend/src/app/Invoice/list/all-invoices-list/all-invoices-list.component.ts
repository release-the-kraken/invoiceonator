import {Component, OnInit} from '@angular/core';
import {PageResponse} from "../../../model/pagination";
import {CompanyDTO} from "../../../model/company";
import {PageEvent} from "@angular/material/paginator";
import {InvoiceDTO} from "../../../model/invoice";
import {InvoiceService} from "../../service/invoice.service";

@Component({
  selector: 'app-all-invoices-list',
  templateUrl: './all-invoices-list.component.html',
  styleUrls: ['./all-invoices-list.component.css']
})
export class AllInvoicesListComponent implements OnInit {
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
    this.invoiceService.getAll(pageEvent?.pageIndex, pageEvent?.pageSize)
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
    return [];
  }
}
