import {Component, EventEmitter, Input, OnInit, Output} from '@angular/core';
import {PageEvent} from "@angular/material/paginator";
import {MatDialog} from "@angular/material/dialog";
import {AuthenticationService} from "../../../authentication-service/authentication.service";
import {InvoiceDTO} from "../../../model/invoice";
import {InvoiceFormComponent} from "../../invoice-form/invoice-form.component";

@Component({
  selector: 'app-invoice-list-container',
  templateUrl: './invoice-list-container.component.html',
  styleUrls: ['./invoice-list-container.component.css']
})
export class InvoiceListContainerComponent implements OnInit {
  @Input() invoices: InvoiceDTO[] = [];
  @Input() hiddenColumnNames: string[] =[];
  @Input('totalElements') totalElements: number = 0;

  @Output() loadInvoicesEvent = new EventEmitter<PageEvent>();

  visibleColumnNames : string[] = [
    'number',
    'description',
    'creationDate',
    'dueDate',
    'userFullName',
    'companyName'
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
    this.loadInvoicesEvent.emit({
      pageSize: 5,
      pageIndex: 0,
      length: 0
    })
  }
  loadInvoices(event?: PageEvent){
    this.loadInvoicesEvent.emit(event);
  }
  openDialog() {
    this.dialog.open(InvoiceFormComponent);
  }
}
