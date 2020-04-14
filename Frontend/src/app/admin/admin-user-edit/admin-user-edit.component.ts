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
  selector: 'app-admin-user-edit',
  templateUrl: './admin-user-edit.component.html',
  styleUrls: ['./admin-user-edit.component.css']
})
export class AdminUserEditComponent implements OnInit {

  constructor(private _router: Router,private route: ActivatedRoute, private authService: AuthService,
    private userService: UserService, private location: Location, private roleService: RoleService) {
   
  }
  ngOnInit() {
    let id = this.route.snapshot.paramMap.get('id');    
    this.resetForm();
    this.getAllRole();    
    this.getUserById(Number(id));

  }
 
  getUserById(id: number){
    this.userService.getUserById(id).subscribe((res) => {
    this.userService.user = res as User;
    })
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
  getAllRole(){
    this.roleService.getRoleList().subscribe((res) => {
      this.roleService.roles = res as Role[];
    })
  }
  cancel(){
    this.location.back();
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
      this._router.navigate(['/manageUser'])
    },
     error => console.log(error)
    );
   console.log('Your form data: '+  form.value)
    }
  }
}
