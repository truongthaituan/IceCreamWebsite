import { Component, OnInit } from '@angular/core';
import { FormGroup, FormControl, Validators } from '@angular/forms';
import { Router } from '@angular/router';
import { CustomerService } from 'src/app/services/customer-service/customer.service';
import { Customer } from 'src/app/services/customer-service/customer.model';
import { Location } from '@angular/common';

@Component({
  selector: 'app-customer-change-password',
  templateUrl: './customer-change-password.component.html',
  styleUrls: ['./customer-change-password.component.css']
})
export class CustomerChangePasswordComponent implements OnInit {
  changePasswordForm: FormGroup = new FormGroup({
    customer: new FormControl(null, [Validators.required]),
    currentPassword: new FormControl(null, [Validators.email, Validators.required]),
    newPassword: new FormControl(null, [Validators.required]),
    confirmPassword: new FormControl(null, Validators.required)
  })
  constructor(private _router: Router,private location:Location, private customerService: CustomerService) { }
  statusChangePassword = false
  alertMessage: string = ""
  userName = ""
  ngOnInit() {
    this.initialCustomerChangePassword();
    this.userName = localStorage.getItem("userName");
  }
  initialCustomerChangePassword() {
    this.customerService.customerChangePassword = ({
      customer: null,
      currentPassword: "",
      newPassword: "",
      confirmPassword: ""
    });
  }
  ChangePassword() {
    if (this.changePasswordForm.value.currentPassword == null ||
      this.changePasswordForm.value.newPassword == null || this.changePasswordForm.value.confirmPassword == null) {
      alert("Please enter full information");
      return false;
    }
    else
      if (this.changePasswordForm.controls.newPassword.value != this.changePasswordForm.controls.confirmPassword.value) {
        alert("ConfirmPassword was wrong!");
        return false;
      }
      else {
        this.userName = localStorage.getItem("userName");
        console.log(JSON.stringify(this.changePasswordForm.value));
        this.customerService.getCustomerByUserName(this.userName).subscribe(res => {
          this.customerService.customer = res as Customer;
          console.log(res)
          this.changePasswordForm.value.customer = this.customerService.customer
          console.log(this.changePasswordForm.value.customer);
          this.customerService.changePasswordCustomer(this.changePasswordForm.value).subscribe(
            res => {
              console.log("changePass" + res);
              this.statusChangePassword = true;
              this.alertMessage = String(Object.values(res)[0]);
              if (this.alertMessage == "Invalid Password!") {
                setTimeout(() => { this.statusChangePassword = false; this.alertMessage = "" }, 5000);
              }
              else {
                setTimeout(() => { this.statusChangePassword = false; this.alertMessage = "" }, 5000);
                this.changePasswordForm.controls.currentPassword.reset();
                this.changePasswordForm.controls.newPassword.reset();
                this.changePasswordForm.controls.confirmPassword.reset();
              }
            },
            error => {
              console.log(error);
            })
        })
      }
  }
  cancel(){
    this.location.back();
  }
}
