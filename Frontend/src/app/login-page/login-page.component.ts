import { Component, OnInit } from '@angular/core';
import { FormGroup, FormControl, Validators } from '@angular/forms';
import { Router } from '@angular/router';
import { UserService } from '../user-service/user.service';
import { AuthInfo } from '../services/auth-service/auth-info.model';
import { AuthService } from '../services/auth-service/auth.service';



@Component({
  selector: 'app-login-page',
  templateUrl: './login-page.component.html',
  styleUrls: ['./login-page.component.css']
})
export class LoginPageComponent implements OnInit {
  isLoggedIn :Boolean = false
  statusLogin: Boolean = false
  roles: string[] = []
  errString: String = ""
  showErrorMessage: Boolean = false;
  loginForm: FormGroup = new FormGroup({
    userName: new FormControl(null, [Validators.required]),
    password: new FormControl(null, Validators.required)
  });
  constructor(private _router: Router, private authService: AuthService) {

   }

  ngOnInit() {
      this.authService.authInfo.subscribe(val => {
        this.isLoggedIn = val.loggedIn;
        this.roles = val.roles;
        });
}
roleNames: string
  login() {
    if (!this.loginForm.valid) {
      alert("Mời nhập đầy đủ thông tin");
      return;
    }
    else {
      const credentials = this.loginForm.value;
      this.showErrorMessage = false;
      //goij method login từ userService
      
      this.authService.login(credentials).subscribe((res) => {
        //gọi object response
        console.log(res)
        const response: AuthInfo = res as AuthInfo;
        this.roleNames = String(response.roles);
        if (response.loggedIn == false) {
          this.errString = "Email hoặc password không đúng!";
          this.showErrorMessage = true;
          setTimeout(() => { this.errString = ""; this.showErrorMessage = false }, 4000);
        }
        else  if (response.loggedIn == true){
          for(let i = 0;i <  this.roleNames.trim().split(",").length;i++){
          //admin
          if ( this.roleNames.trim().split(",")[i] == "ROLE_ADMIN") {
            window.location.href = "/manageUser"
            this.statusLogin = true;
            localStorage.setItem('userName', response.userName);
            localStorage.setItem('statusLogin', String(this.statusLogin));
            // sessionStorage.setItem('loginBy', "loginbt");
            console.log("admin")
          }
          //member
          else if( this.roleNames.trim().split(",")[i] == "ROLE_USER") {
            window.location.href = "/manageUser"
            this.statusLogin = true;
            localStorage.setItem('userName', response.userName);
            localStorage.setItem('statusLogin', String(this.statusLogin));
            // sessionStorage.setItem('loginBy', "loginbt");
            console.log("user")
             }
             else if( this.roleNames.trim().split(",")[i] == "ROLE_CUSTOMER") {
              window.location.href = "/"
              this.statusLogin = true;
              localStorage.setItem('userName', response.userName);
              localStorage.setItem('statusLogin', String(this.statusLogin));
              // sessionStorage.setItem('loginBy', "loginbt");
              console.log("customer")
               }
          }
        }
      });
    }
  }

}
