import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { CustomerRecipeDetailsComponent } from './customer-recipe-details.component';

describe('CustomerRecipeDetailsComponent', () => {
  let component: CustomerRecipeDetailsComponent;
  let fixture: ComponentFixture<CustomerRecipeDetailsComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ CustomerRecipeDetailsComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(CustomerRecipeDetailsComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
