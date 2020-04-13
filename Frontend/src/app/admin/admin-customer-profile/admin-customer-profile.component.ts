import { Component, OnInit } from '@angular/core';
import { Router, ActivatedRoute } from '@angular/router';
import { CustomerService } from 'src/app/services/customer-service/customer.service';
import { Location } from '@angular/common';
import { Customer } from 'src/app/services/customer-service/customer.model';
import { NgForm } from '@angular/forms';
declare var $:any;
@Component({
  selector: 'app-admin-customer-profile',
  templateUrl: './admin-customer-profile.component.html',
  styleUrls: ['./admin-customer-profile.component.css']
})
export class AdminCustomerProfileComponent implements OnInit {

  constructor(private _router: Router,private route: ActivatedRoute,
     private customerService: CustomerService, private location: Location) {
   
  }

 ngOnInit() {
  let id = this.route.snapshot.paramMap.get('id');
  console.log(id);
  this.resetForm();
  this.getCustomerById(Number(id));
;
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
 getCustomerById(id: number){
   this.customerService.getCustomerById(id).subscribe((res) => {
   this.customerService.customer = res as Customer;
   console.log(res);
   })
 }
 previousUrl: string;

 cancel(){
   this.location.back();
 }

 onSubmit(form: NgForm) {
   let id = this.route.snapshot.paramMap.get('id');
   if (confirm('Do you want to update information customer ?') == true) {
    form.value.avatar =  $('input[type=file]').val().replace(/C:\\fakepath\\/i, 'images/');
    console.log(form.value.avatar);
    form.value.customerId = parseInt(id);
    form.value.password = this.customerService.customer.password;
   this.customerService.updateCustomer(form.value).subscribe(
    data => {console.log(data);
      this.location.back();
     // this.showMsg = true;
     // sessionStorage.setItem('showMsg',String(this.showMsg))
   },
    error => console.log(error)
   );
    console.log('Your form data: '+  form.value)
   }
 }
}
