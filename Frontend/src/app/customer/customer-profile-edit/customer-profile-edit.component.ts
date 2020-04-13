import { Component, OnInit } from '@angular/core';
import { Router, ActivatedRoute, NavigationEnd } from '@angular/router';
import { UserService } from 'src/app/user-service/user.service';
import { NgForm } from '@angular/forms';
import { User } from 'src/app/user-service/user.model';
import { Location } from '@angular/common';
import { CustomerService } from 'src/app/services/customer-service/customer.service';
import { Customer } from 'src/app/services/customer-service/customer.model';
declare var $:any;
@Component({
  selector: 'app-customer-profile-edit',
  templateUrl: './customer-profile-edit.component.html',
  styleUrls: ['./customer-profile-edit.component.css']
})
export class CustomerProfileEditComponent implements OnInit {

  constructor(private _router: Router,private route: ActivatedRoute,
    private customerService: CustomerService, private location: Location) {
  
 }

ngOnInit() {
 let userName = this.route.snapshot.paramMap.get('userName');
 console.log(userName);
 this.resetForm();
 this.getCustomerByuserName(userName);
  }
resetForm(form?: NgForm) {
  if (form)
    form.reset();
  this.customerService.customer = {
    customerId: null,
    userName: "",
    email: "",
    phone: "",
    password: "",
    dateOfBirth: null,
    address: "",
    gender: null,
    avatar: "",
    status: null,
    numberOfLoginFailed: null
  }
}
getCustomerByuserName(userName: string){
  this.customerService.getCustomerByUserName(userName).subscribe((res) => {
  this.customerService.customer = res as Customer;
  console.log(res);
  })
}
previousUrl: string;

cancel(){
  this.location.back();
}

onSubmit(form: NgForm) {
  if (confirm('Do you want to update information customer ?') == true) {
   form.value.avatar =  $('input[type=file]').val().replace(/C:\\fakepath\\/i, 'images/');
   form.value.customerId = this.customerService.customer.customerId;
   console.log(form.value.customerId);
   form.value.password = this.customerService.customer.password;
  this.customerService.updateCustomer(form.value).subscribe(
   data => {console.log(data);
     this._router.navigate(['/']);
  },
   error => console.log(error)
  );
   console.log('Your form data: '+  form.value)
    }
  }
  moveToChangePassword(){
    return this._router.navigate(['/customerChangePassword']);
  }
}
