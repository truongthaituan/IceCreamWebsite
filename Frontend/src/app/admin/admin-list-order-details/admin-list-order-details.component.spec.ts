import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { AdminListOrderDetailsComponent } from './admin-list-order-details.component';

describe('AdminListOrderDetailsComponent', () => {
  let component: AdminListOrderDetailsComponent;
  let fixture: ComponentFixture<AdminListOrderDetailsComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ AdminListOrderDetailsComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(AdminListOrderDetailsComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
