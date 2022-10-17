import { ComponentFixture, TestBed } from '@angular/core/testing';

import { UserInvoicesListComponent } from './user-invoices-list.component';

describe('UserInvoicesListComponent', () => {
  let component: UserInvoicesListComponent;
  let fixture: ComponentFixture<UserInvoicesListComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ UserInvoicesListComponent ]
    })
    .compileComponents();

    fixture = TestBed.createComponent(UserInvoicesListComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
