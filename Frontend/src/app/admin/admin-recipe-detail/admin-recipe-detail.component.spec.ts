import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { AdminRecipeDetailComponent } from './admin-recipe-detail.component';

describe('AdminRecipeDetailComponent', () => {
  let component: AdminRecipeDetailComponent;
  let fixture: ComponentFixture<AdminRecipeDetailComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ AdminRecipeDetailComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(AdminRecipeDetailComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
