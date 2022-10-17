import { ComponentFixture, TestBed } from '@angular/core/testing';

import { UserCompaniesListComponent } from './user-companies-list.component';

describe('UserCompaniesListComponent', () => {
  let component: UserCompaniesListComponent;
  let fixture: ComponentFixture<UserCompaniesListComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ UserCompaniesListComponent ]
    })
    .compileComponents();

    fixture = TestBed.createComponent(UserCompaniesListComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
