import { Component, OnInit } from '@angular/core';
import { Router, ActivatedRoute, NavigationEnd } from '@angular/router';
import { UserService } from 'src/app/user-service/user.service';
import { NgForm } from '@angular/forms';
import { User } from 'src/app/user-service/user.model';
import { Location } from '@angular/common';
import { Role } from 'src/app/services/role-service/role.model';
import { RoleService } from 'src/app/services/role-service/role.service';
declare var $:any;
@Component({
  selector: 'app-user-profile-edit',
  templateUrl: './user-profile-edit.component.html',
  styleUrls: ['./user-profile-edit.component.css']
})
export class UserProfileEditComponent implements OnInit {

  constructor(private _router: Router,private route: ActivatedRoute, 
    private userService: UserService, private location: Location, private roleService: RoleService) {
   
  }
user_roles: Array<Role>;
 ngOnInit() {
   let userName = this.route.snapshot.paramMap.get('userName');
   console.log(userName);
   this.resetForm();
   this.getUserByUserName(userName);
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
 getUserByUserName(userName: string){
   this.userService.getUserByUserName(userName).subscribe((res) => {
   this.userService.user = res as User;
   console.log(this.userService.user);
   this.user_roles =this.userService.user.roles;
   console.log(this.user_roles);
   console.log(JSON.stringify(res));
   })
 }
 previousUrl: string;
 cancel(){
   this.location.back();
 }

 onSubmit(form: NgForm) {
   if (confirm('Do you want to update this user ?') == true) {
     form.value.avatar =  $('input[type=file]').val().replace(/C:\\fakepath\\/i, 'images/');
     console.log(form.value.avatar);
     form.value.userId =  this.userService.user.userId;
    //  console.log("role ->" + form.value.roles)  
    form.value.roles = [{"id": 2 }];
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
 moveToUserChangePassword(){
   return this._router.navigate(['/userChangePassword']);
 }
}
