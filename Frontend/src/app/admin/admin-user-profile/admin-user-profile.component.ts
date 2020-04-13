import { Component, OnInit } from '@angular/core';
import { Router, ActivatedRoute, NavigationEnd } from '@angular/router';
import { UserService } from 'src/app/user-service/user.service';
import { NgForm } from '@angular/forms';
import { User } from 'src/app/user-service/user.model';
import { Location } from '@angular/common';
import { Role } from 'src/app/services/role-service/role.model';
import { RoleService } from 'src/app/services/role-service/role.service';
import { AuthService } from 'src/app/services/auth-service/auth.service';
declare var $:any;
@Component({
  selector: 'app-admin-user-profile',
  templateUrl: './admin-user-profile.component.html',
  styleUrls: ['./admin-user-profile.component.css']
})
export class AdminUserProfileComponent implements OnInit {
  isLoggedIn: boolean = false
  roles: string[] = []
  constructor(private _router: Router,private route: ActivatedRoute, private authService: AuthService,
    private userService: UserService, private location: Location, private roleService: RoleService) {
   
  }
user_roles: Array<Role>;
 ngOnInit() {
  this.authService.authInfo.subscribe(val => {
    this.isLoggedIn = val.loggedIn;
    this.roles = val.roles;
  });
   let id = this.route.snapshot.paramMap.get('id');
   console.log(id);
   this.resetForm();
   this.getUserById(Number(id));
    this.getAllRole();
 }
 resetForm(form?: NgForm) {
   if (form)
     form.reset();
   this.userService.user = {
     userId: null,
     userName: "",
     password: "",
     phoneNumber: "",
     avatar: "",
     status: null,
     roles: Role,
   }
 }
 getUserById(id: number){
   this.userService.getUserById(id).subscribe((res) => {
   this.userService.user = res as User;
   console.log(this.userService.user);
   this.user_roles =this.userService.user.roles;
   console.log(this.user_roles);
   console.log(JSON.stringify(res));
   })
 }
getAllRole(){
  this.roleService.getRoleList().subscribe((res) => {
    this.roleService.roles = res as Role[];
  })
}
 previousUrl: string;

 cancel(){
   this.location.back();
 }
 moveToAdminCustomer(){
   this._router.navigate(['/manageCustomer']);
 }
 moveToAdminUser(){
   this._router.navigate(['/manageUser']);
 }
 onSubmit(form: NgForm) {
   let id = this.route.snapshot.paramMap.get('id');
   if (confirm('Do you want to update this user ?') == true) {
     form.value.avatar =  $('input[type=file]').val().replace(/C:\\fakepath\\/i, 'images/');
     console.log(form.value.avatar);
     form.value.userId = parseInt(id);
    //  console.log("role ->" + form.value.roles)  
    form.value.roles = [{"id":form.value.roles}];
   this.userService.updateUser(form.value).subscribe(
    data => {
      console.log(data);
      this.location.back();
   },
    error => console.log(error)
   );
  console.log('Your form data: '+  form.value)
   }
 }
}
