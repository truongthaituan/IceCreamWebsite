import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { CustomerConfirmAccountComponent } from './customer-confirm-account.component';

describe('CustomerConfirmAccountComponent', () => {
  let component: CustomerConfirmAccountComponent;
  let fixture: ComponentFixture<CustomerConfirmAccountComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ CustomerConfirmAccountComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(CustomerConfirmAccountComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
