import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { CustomerChangePasswordComponent } from './customer-change-password.component';

describe('CustomerChangePasswordComponent', () => {
  let component: CustomerChangePasswordComponent;
  let fixture: ComponentFixture<CustomerChangePasswordComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ CustomerChangePasswordComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(CustomerChangePasswordComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
