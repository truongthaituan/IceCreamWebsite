import { Component, OnInit } from '@angular/core';
import { FormControl, FormGroup, Validators } from '@angular/forms';
import { Router } from '@angular/router';
import { CustomerService } from 'src/app/services/customer-service/customer.service';
import { Location } from '@angular/common';
declare var $:any;
@Component({
  selector: 'app-admin-add-customer',
  templateUrl: './admin-add-customer.component.html',
  styleUrls: ['./admin-add-customer.component.css']
})
export class AdminAddCustomerComponent implements OnInit {
  errRegister: String = ""
  statusRegister: Boolean
  createCustomerForm: FormGroup = new FormGroup({
    userName: new FormControl(null, [Validators.email, Validators.required]),
    email: new FormControl(null, Validators.required),
    password: new FormControl(null, Validators.required),
    repassword: new FormControl(null, Validators.required),
    phone: new FormControl(null),
    dateOfBirth: new FormControl(null),
    address: new FormControl(null),
    gender: new FormControl(null),
    avatar: new FormControl(null),
    status: new FormControl(null),
    numberOfLoginFailed: new FormControl(null)
  })
  constructor(private _router: Router,
     private customerService: CustomerService, private location: Location) { }

  ngOnInit() {
    this.initialAccount();
    // this.refresherListUser();
  }
  initialAccount() {
    this.customerService.customer = ({
      customerId: null,
      userName: '',
      status: null,
      password: '',
      phone: '',
      avatar: '',
      email: '',
      dateOfBirth: null,
      address: '',
      gender: null,
      numberOfLoginFailed: null

    });
  }
  createCustomer() {
    if (this.createCustomerForm.value.repassword == null || this.createCustomerForm.value.userName == null || 
      this.createCustomerForm.value.status == null || this.createCustomerForm.value.password == null ||
      this.createCustomerForm.value.phone == null || this.createCustomerForm.value.avatar == null ||
      this.createCustomerForm.value.email == null || this.createCustomerForm.value.dateOfBirth == null ||
      this.createCustomerForm.value.address == null || this.createCustomerForm.value.gender == null) {
      alert("Mời nhập đầy đủ thông tin");
      return;
    }
    else
      if (this.createCustomerForm.controls.password.value != this.createCustomerForm.controls.repassword.value) {
        alert("Xác nhận mật khẩu không đúng!");
        return false;
      }
      else {
        this.createCustomerForm.value.avatar = $('input[type=file]').val().replace(/C:\\fakepath\\/i, 'images/');  
        console.log(this.createCustomerForm.value.avatar);
        console.log(JSON.stringify(this.createCustomerForm.value)); 
        this.createCustomerForm.value.numberOfLoginFailed = 0; 
        this.customerService.createCustomer(JSON.stringify(this.createCustomerForm.value))
          .subscribe(
            data => {
              console.log(data);
              this.statusRegister = true;
              this._router.navigate(['/manageCustomer']);
              console.log("Add Customer Successfully!");
            },
            error => {
              console.log(error);
              this.errRegister = "Tài khoản có thể đã tồn tại!";
            })
      }
  }
  cancel(){
    this.location.back();
  }

}
