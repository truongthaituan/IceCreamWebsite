import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { ManageOrderDetailsComponent } from './manage-order-details.component';

describe('ManageOrderDetailsComponent', () => {
  let component: ManageOrderDetailsComponent;
  let fixture: ComponentFixture<ManageOrderDetailsComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ ManageOrderDetailsComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(ManageOrderDetailsComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
