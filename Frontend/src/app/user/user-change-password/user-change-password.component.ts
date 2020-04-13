import { Component, OnInit } from '@angular/core';
import { FormGroup, FormControl, Validators } from '@angular/forms';
import { Router } from '@angular/router';
import { CustomerService } from 'src/app/services/customer-service/customer.service';
import { Customer } from 'src/app/services/customer-service/customer.model';
import { Location } from '@angular/common';
import { UserService } from 'src/app/user-service/user.service';
import { User } from 'src/app/user-service/user.model';
@Component({
  selector: 'app-user-change-password',
  templateUrl: './user-change-password.component.html',
  styleUrls: ['./user-change-password.component.css']
})
export class UserChangePasswordComponent implements OnInit {

  changePasswordForm: FormGroup = new FormGroup({
    user: new FormControl(null, [Validators.required]),
    currentPassword: new FormControl(null, [Validators.email, Validators.required]),
    newPassword: new FormControl(null, [Validators.required]),
    confirmPassword: new FormControl(null, Validators.required)
  })
  constructor(private _router: Router,private location:Location, private userService: UserService) { }
  statusChangePassword = false
  alertMessage: string = ""
  userName = ""
  ngOnInit() {
    this.initialUserChangePassword();
    this.userName = localStorage.getItem("userName");
  }
  initialUserChangePassword() {
    this.userService.userChangePassword = ({
      user: null,
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
        this.userService.getUserByUserName(this.userName).subscribe(res => {
          this.userService.user = res as User;
          console.log(res)
          this.changePasswordForm.value.user = this.userService.user
          console.log(this.changePasswordForm.value.user);
          this.userService.changePasswordUser(this.changePasswordForm.value).subscribe(
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
