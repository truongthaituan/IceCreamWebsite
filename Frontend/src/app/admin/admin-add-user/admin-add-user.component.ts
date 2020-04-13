import { Component, OnInit } from '@angular/core';
import { Router, ActivatedRoute, NavigationEnd } from '@angular/router';
import { UserService } from 'src/app/user-service/user.service';
import { NgForm, FormGroup, FormControl, Validators } from '@angular/forms';
import { User } from 'src/app/user-service/user.model';
import { Location } from '@angular/common';
import { RoleService } from 'src/app/services/role-service/role.service';
import { Role } from 'src/app/services/role-service/role.model';
declare var $:any;
@Component({
  selector: 'app-admin-add-user',
  templateUrl: './admin-add-user.component.html',
  styleUrls: ['./admin-add-user.component.css']
})
export class AdminAddUserComponent implements OnInit {
  errRegister: String = ""
  statusRegister: Boolean
  registerForm: FormGroup = new FormGroup({
    userName: new FormControl(null, [Validators.email, Validators.required]),
    status: new FormControl(null, Validators.required),
    password: new FormControl(null, Validators.required),
    repassword: new FormControl(null, Validators.required),
    phoneNumber: new FormControl(null),
    avatar: new FormControl(null),
    roles: new FormControl(null)
  })
  constructor(private _router: Router,private route: ActivatedRoute, private roleService: RoleService,
     private userService: UserService, private location: Location) { }

  ngOnInit() {
    this.initialAccount();
    // this.refresherListUser();
    this.getAllRole();
  }
  initialAccount() {
    this.userService.user = ({
      userId: null,
      userName: '',
      status: null,
      password: '',
      phoneNumber: '',
      avatar: '',
      roles: Role
    });
  }

  getAllRole(){
    this.roleService.getRoleList().subscribe((res) => {
      this.roleService.roles = res as Role[];
    })
  }
  // refresherListUser(){
  //   this.userService.listAllUsers().subscribe((res) => {
  //     this.userService.users = res as User[]
  //     console.log(res)
  //   })
  // }
  register() {
    if (this.registerForm.value.password == null || this.registerForm.value.repassword == null || 
      this.registerForm.value.roles == null || this.registerForm.value.avatar == null ||
      this.registerForm.value.userName == null || this.registerForm.value.phoneNumber == null) {
      alert("Mời nhập đầy đủ thông tin");
      return;
    }
    else
      if (this.registerForm.controls.password.value != this.registerForm.controls.repassword.value) {
        alert("Xác nhận mật khẩu không đúng!");
        return false;
      }
      else {
        this.registerForm.value.roles = [{"id": parseInt(this.registerForm.value.roles)}];
        this.registerForm.value.avatar = $('input[type=file]').val().replace(/C:\\fakepath\\/i, 'images/');  
        console.log(this.registerForm.value.avatar);
        console.log(JSON.stringify(this.registerForm.value)); 
        this.userService.register(JSON.stringify(this.registerForm.value))
          .subscribe(
            data => {
              console.log(data);
              this.statusRegister = true;
              this._router.navigate(['/manageUser']);
              console.log("Add User Successfully!");
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
