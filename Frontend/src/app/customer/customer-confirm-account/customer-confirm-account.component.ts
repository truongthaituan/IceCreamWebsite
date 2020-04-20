import { Component, OnInit } from '@angular/core';
import { CustomerService } from 'src/app/services/customer-service/customer.service';
import { Router, ActivatedRoute } from '@angular/router';
import { Customer } from 'src/app/services/customer-service/customer.model';
import { ConfirmationToken } from 'src/app/services/ConfirmationToken/confirmation-token.model';

@Component({
  selector: 'app-customer-confirm-account',
  templateUrl: './customer-confirm-account.component.html',
  styleUrls: ['./customer-confirm-account.component.css']
})
export class CustomerConfirmAccountComponent implements OnInit {
  alertMessage: string = ""

  constructor(private _router: Router,private customerService: CustomerService, private activatedRoute: ActivatedRoute) { }
  tokenAccount : ConfirmationToken
  message
  ngOnInit() {
    let token = this.activatedRoute.snapshot.paramMap.get('token');
    this.tokenAccount = JSON.parse(localStorage.getItem("tokenCustomer"));
    if(token != null){
      this.activeCustomer(this.tokenAccount.customer.customerId);
      this.alertMessage = "Congratulation! Your account has been activated and email is verified!"
    }else{
      this.alertMessage = "Token is valid!"
    }
  }
  activeCustomer(id) {
     this.customerService.getCustomerById(id).subscribe((res) => {
    this.customerService.customer = res as Customer; 
     this.customerService.customer.status = true;
    this.customerService.updateCustomer(this.customerService.customer).subscribe((res) => {  
        console.log(res)
      // setTimeout(() => {  this.statusCRUD = ""; }, 4000);
      // this.ngOnInit();
     },err => console.log(err));
    });
  }
}
