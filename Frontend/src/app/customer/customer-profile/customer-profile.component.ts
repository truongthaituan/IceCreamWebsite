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
  selector: 'app-customer-profile',
  templateUrl: './customer-profile.component.html',
  styleUrls: ['./customer-profile.component.css']
})
export class CustomerProfileComponent implements OnInit {

  constructor(private _router: Router,private route: ActivatedRoute,
    private customerService: CustomerService, private location: Location) {
  
 }

ngOnInit() {
 let userName = this.route.snapshot.paramMap.get('userName');
 console.log(userName);
 this.getCustomerByuserName(userName);
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
moveToCustomerEdit(userName){
  return this._router.navigate(["/customerProfileEdit" + `/${userName}`]);
}
moveToChangePassword(){
  return this._router.navigate(['/customerChangePassword']);
}
}
