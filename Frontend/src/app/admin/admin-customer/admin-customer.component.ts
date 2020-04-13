import { Component, OnInit, ViewChild } from '@angular/core';
import { RecipeService } from 'src/app/services/recipe-service/recipe.service';
import { Recipe } from 'src/app/services/recipe-service/recipe.model';
declare var $:any;
import {MatPaginator} from '@angular/material/paginator';
import {MatSort} from '@angular/material/sort';
import {MatTableDataSource} from '@angular/material/table';
import { CustomerService } from 'src/app/services/customer-service/customer.service';
import { Customer } from 'src/app/services/customer-service/customer.model';
import { Router } from '@angular/router';
import { FormGroup, FormControl, Validators } from '@angular/forms';
@Component({
  selector: 'app-admin-customer',
  templateUrl: './admin-customer.component.html',
  styleUrls: ['./admin-customer.component.css']
})
export class AdminCustomerComponent implements OnInit {
  statusCRUD: String = ""
 // constructor(private recipeService: RecipeService) {
    
  //  }

  // ngOnInit() {
  //   this.refreshRecipList();
  // }
  // refreshRecipList() {
	// 	this.recipeService.getRecipeList().subscribe((res) => {
	// 	  this.recipeService.recipe = res as Recipe[];
	// 	});
  //   }
  displayedColumns: string[] = ['customerId', 'userName', 'email','phone','dateOfBirth','address', 'phone','gender','status','numberOfLoginFailed','getdetails','remove','action'];
  dataSource: MatTableDataSource<Customer>;

  @ViewChild(MatPaginator, {static: true}) paginator: MatPaginator;
  @ViewChild(MatSort, {static: true}) sort: MatSort;
  customerForm: FormGroup = new FormGroup({
    customerId: new FormControl(null),
    userName: new FormControl(null, [Validators.required]),
    email: new FormControl(null, Validators.required),
    phone: new FormControl(null),
    password: new FormControl(null),
    dateOfBirth: new FormControl(null),
    address: new FormControl(null),
    gender: new FormControl(null),
    avatar: new FormControl(null),
    status: new FormControl(null),
    numberOfLoginFailed: new FormControl(null)
  })
  constructor(private _router: Router, private customerService: CustomerService) {

  }

  ngOnInit() {
    this.refreshCustomerList();
    
  }
  refreshCustomerList() {
		this.customerService.getCustomerList().subscribe((res) => {
      console.log(res)
      this.customerService.customers = res as Customer[];
      this.dataSource = new MatTableDataSource(this.customerService.customers);
      this.dataSource.paginator = this.paginator;
      this.dataSource.sort = this.sort;
		});
    }
  applyFilter(event: Event) {
    const filterValue = (event.target as HTMLInputElement).value;
    this.dataSource.filter = filterValue.trim().toLowerCase();
    if (this.dataSource.paginator) {
      this.dataSource.paginator.firstPage();
    }
  }
  getCuctomerById(customer_id)
  {
    return this._router.navigate(["/manageCustomerProfile" + `/${customer_id}`]);
  }
  save() {
    console.log(this.dataSource);
    const myData = this.dataSource.data.map((row: Customer) => {
      if(row.status == true){
        String(row.status) == "Active";
      }
      return {status: row.status}
    });
    console.log(myData);
  }

  deleteCustomerById(id){
    if (confirm('Do you want to remove this customer?') == true) {
      this.customerService.deleteCustomer(id).subscribe((res) => {  
        console.log(res);
           this.statusCRUD = "Delete Customer Successfully!";
           setTimeout(() => {  this.statusCRUD = ""; }, 4000);
            this.ngOnInit();
          },err => console.log(err));
      }
  }
  blockCustomer(id) {
    if (confirm('Do you want to block this customer?') == true) {
        this.customerService.getCustomerById(id).subscribe((res) => {
          this.customerService.customer = res as Customer; 
      this.customerForm.value.customerId = id;
      this.customerForm.value.userName = this.customerService.customer.userName;
      this.customerForm.value.email = this.customerService.customer.email;
      this.customerForm.value.phone = this.customerService.customer.phone;
      this.customerForm.value.dateOfBirth = this.customerService.customer.dateOfBirth;
      this.customerForm.value.address = this.customerService.customer.address;
      this.customerForm.value.gender = this.customerService.customer.gender;
      this.customerForm.value.password = this.customerService.customer.password;
      this.customerForm.value.avatar = this.customerService.customer.avatar;
      this.customerForm.value.status = false;
      this.customerForm.value.numberOfLoginFailed = this.customerService.customer.numberOfLoginFailed;
      console.log(JSON.stringify(this.customerForm.value)); 
      this.customerService.updateCustomer(this.customerForm.value).subscribe((res) => {  
        console.log(res);
        this.statusCRUD = "Block Customer Successfully!";
        setTimeout(() => {  this.statusCRUD = ""; }, 4000);
        this.ngOnInit();
      },err => console.log(err));
    });
  }
}
  activeCustomer(id) {
    if (confirm('Do you want to active this customer?') == true) {
      this.customerService.getCustomerById(id).subscribe((res) => {
        this.customerService.customer = res as Customer; 
    this.customerForm.value.customerId = id;
    this.customerForm.value.userName = this.customerService.customer.userName;
    this.customerForm.value.email = this.customerService.customer.email;
    this.customerForm.value.phone = this.customerService.customer.phone;
    this.customerForm.value.dateOfBirth = this.customerService.customer.dateOfBirth;
    this.customerForm.value.address = this.customerService.customer.address;
    this.customerForm.value.gender = this.customerService.customer.gender;
    this.customerForm.value.password = this.customerService.customer.password;
    this.customerForm.value.avatar = this.customerService.customer.avatar;
    this.customerForm.value.status = true;
    this.customerForm.value.numberOfLoginFailed = this.customerService.customer.numberOfLoginFailed;
    console.log(JSON.stringify(this.customerForm.value)); 
    this.customerService.updateCustomer(this.customerForm.value).subscribe((res) => {  
      console.log(res);
      this.statusCRUD = "Active Customer Successfully!";
      setTimeout(() => {  this.statusCRUD = ""; }, 4000);
      this.ngOnInit();
    },err => console.log(err));
  });
}
  }
  moveToAdminAddCustomer(){
    this._router.navigate(['/manageAddCustomer']);
  }
  moveToAdminUser(){
    this._router.navigate(['/manageUser']);
  }
}
