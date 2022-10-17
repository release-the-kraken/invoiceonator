import { ComponentFixture, TestBed } from '@angular/core/testing';

import { AllInvoicesListComponent } from './all-invoices-list.component';

describe('AllInvoicesListComponent', () => {
  let component: AllInvoicesListComponent;
  let fixture: ComponentFixture<AllInvoicesListComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ AllInvoicesListComponent ]
    })
    .compileComponents();

    fixture = TestBed.createComponent(AllInvoicesListComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
