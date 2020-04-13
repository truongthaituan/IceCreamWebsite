import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { OrderIcecreamComponent } from './order-icecream.component';

describe('OrderIcecreamComponent', () => {
  let component: OrderIcecreamComponent;
  let fixture: ComponentFixture<OrderIcecreamComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ OrderIcecreamComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(OrderIcecreamComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
