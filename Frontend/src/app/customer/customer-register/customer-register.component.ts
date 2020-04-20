import { Component, OnInit } from '@angular/core';
import { FormGroup, FormControl, Validators } from '@angular/forms';
import { Router, ActivatedRoute } from '@angular/router';
import { CustomerService } from 'src/app/services/customer-service/customer.service';
import { Location } from '@angular/common';
declare var $:any;
@Component({
  selector: 'app-customer-register',
  templateUrl: './customer-register.component.html',
  styleUrls: ['./customer-register.component.css']
})
export class CustomerRegisterComponent implements OnInit {
  errRegister: String = ""
  statusRegister: Boolean
  alertMessage: string = ""
  registerForm: FormGroup = new FormGroup({
    userName: new FormControl(null, [Validators.required]),
    email: new FormControl(null, [Validators.email, Validators.required]),
    phone: new FormControl(null, [Validators.required]),
    password: new FormControl(null, Validators.required),
    repassword: new FormControl(null, Validators.required),
    dateOfBirth: new FormControl(null, Validators.required),
    address: new FormControl(null),
    gender: new FormControl(null),
    avatar: new FormControl(null),
    status: new FormControl(null),
    numberOfLoginFailed: new FormControl(null)
  })
  constructor(private _router: Router,private route: ActivatedRoute,
     private customerService: CustomerService, private location: Location) { }

  ngOnInit() {
    this.initialAccount();
    // this.refresherListUser();
  }
  initialAccount() {
    this.customerService.customer = ({
      customerId: null,
      userName: '',
      email: null,
      phone: '',
      password: '',
      dateOfBirth: null,
      address: '',
      gender: null,
      avatar: '',
      status: null,
      numberOfLoginFailed: null
    });
  }

  // refresherListUser(){
  //   this.userService.listAllUsers().subscribe((res) => {
  //     this.userService.users = res as User[]
  //     console.log(res)
  //   })
  // }
  registerCustomer() {
    if (this.registerForm.value.password == null || this.registerForm.value.repassword == null || 
      this.registerForm.value.userName == null || this.registerForm.value.email == null ||
      this.registerForm.value.gender == null) {
      alert("Mời nhập đầy đủ thông tin");
      return false;
    }
    else
      if (this.registerForm.controls.password.value != this.registerForm.controls.repassword.value) {
        alert("Xác nhận mật khẩu không đúng!");
        return false;
      }
      else {
        console.log(this.registerForm.value.avatar);
        console.log(JSON.stringify(this.registerForm.value)); 
        this.registerForm.value.numberOfLoginFailed = 0;
        this.registerForm.value.status = false;
        this.customerService.createCustomer(JSON.stringify(this.registerForm.value))
          .subscribe(
            data => {
              console.log(data);
              localStorage.setItem("tokenCustomer",JSON.stringify(data));
              this.statusRegister = true;
              this.alertMessage = "A verification email has been sent to : " +this.registerForm.value.email;
            },
            error => {
              console.log(error);
              this.errRegister = "Account is already exist!";
            })
      }
  }
  cancel(){
    this.location.back();
  }
}
