import { Component, OnInit, ViewChild } from '@angular/core';
import { RecipeService } from 'src/app/services/recipe-service/recipe.service';
import { Recipe } from 'src/app/services/recipe-service/recipe.model';
declare var $:any;
import {MatPaginator} from '@angular/material/paginator';
import {MatSort} from '@angular/material/sort';
import {MatTableDataSource} from '@angular/material/table';
import { CustomerService } from 'src/app/services/customer-service/customer.service';
import { Customer } from 'src/app/services/customer-service/customer.model';
import { Router } from '@angular/router';
import { UserService } from 'src/app/user-service/user.service';
import { User } from 'src/app/user-service/user.model';
import { FormControl, FormGroup, Validators } from '@angular/forms';
import { AuthService } from 'src/app/services/auth-service/auth.service';
@Component({
  selector: 'app-admin-user',
  templateUrl: './admin-user.component.html',
  styleUrls: ['./admin-user.component.css']
})
export class AdminUserComponent implements OnInit {
  isLoggedIn :Boolean = false
  roles: string[] = []
  // constructor(private recipeService: RecipeService) {
    
  //  }

  // ngOnInit() {
  //   this.refreshRecipList();
  // }
  // refreshRecipList() {
	// 	this.recipeService.getRecipeList().subscribe((res) => {
	// 	  this.recipeService.recipe = res as Recipe[];
	// 	});
  //   }
  statusCRUD: String = ""
  displayedColumns: string[] = ['userId', 'userName','phoneNumber', 'status','empty','getdetails','remove', 'action'];
  dataSource: MatTableDataSource<User>;

  @ViewChild(MatPaginator, {static: true}) paginator: MatPaginator;
  @ViewChild(MatSort, {static: true}) sort: MatSort;
  userForm: FormGroup = new FormGroup({
    userId: new FormControl(null),
    userName: new FormControl(null, [Validators.required]),
    status: new FormControl(null, Validators.required),
    phoneNumber: new FormControl(null),
    avatar: new FormControl(null),
    roles: new FormControl(null)
  })
  constructor(private _router: Router, private userService: UserService,private authService: AuthService) {

  }

  ngOnInit() {
    this.authService.authInfo.subscribe(val => {
      this.isLoggedIn = val.loggedIn;
      this.roles = val.roles;
      });
    this.refreshUserList();
  }
  refreshUserList() {
		this.userService.listAllUsers().subscribe((res) => {
      console.log(res)
      this.userService.users = res as User[];
      this.dataSource = new MatTableDataSource(this.userService.users);
      this.dataSource.paginator = this.paginator;
      this.dataSource.sort = this.sort;
		});
    }
  applyFilter(event: Event) {
    const filterValue = (event.target as HTMLInputElement).value;
    this.dataSource.filter = filterValue.trim().toLowerCase();
    if (this.dataSource.paginator) {
      this.dataSource.paginator.firstPage();
    }
  }
  // deleteById(user_id: string) {
  //   if (confirm('Do you want to remove this user ?') == true) {
  //   this.userService.deleteUser(user_id).subscribe(
  //     data=>{ 
  //       console.log(data);
  //       this.ngOnInit();
  //     },
  //   error=>console.log(error)
  //   )
  //   }
  // }
  getUserById(user_id)
  {
    return this._router.navigate(["/manageUserProfile" + `/${user_id}`]);
  }

  save() {
    console.log(this.dataSource);
    const myData = this.dataSource.data.map((row: User) => {
      if(row.status == true){
        String(row.status) == "Active";
      }
      return {status: row.status}
    });
    console.log(myData);
  }
 
blockUser(id) {
  if (confirm('Do you want to block this user?') == true) {
    this.userService.getUserById(id).subscribe((res) => {
    this.userService.user = res as User; 
    console.log(this.userService.user)
    this.userForm.value.userId = this.userService.user.userId;
    this.userForm.value.roles = this.userService.user.roles;
    this.userForm.value.avatar = this.userService.user.avatar;
    this.userForm.value.userName = this.userService.user.userName;  
    this.userForm.value.status = false;  
    this.userForm.value.phoneNumber = this.userService.user.phoneNumber;  
    console.log(JSON.stringify(this.userForm.value)); 
    this.userService.updateUser(this.userForm.value).subscribe((res) => {  
      console.log(res);
      this.statusCRUD = "Block User Successfully!";
      setTimeout(() => {  this.statusCRUD = ""; }, 4000);
      this.ngOnInit();
    });    
    },err => console.log(err));
    }
}
activeUser(id) {
  if (confirm('Do you want to active this user?') == true) {
    this.userService.getUserById(id).subscribe((res) => {
    this.userService.user = res as User; 
    console.log(this.userService.user)
    this.userForm.value.userId = this.userService.user.userId;
    this.userForm.value.roles = this.userService.user.roles;
    this.userForm.value.avatar = this.userService.user.avatar;
    this.userForm.value.userName = this.userService.user.userName;  
    this.userForm.value.status = true;  
    this.userForm.value.phoneNumber = this.userService.user.phoneNumber;  
    console.log(JSON.stringify(this.userForm.value)); 
    this.userService.updateUser(this.userForm.value).subscribe((res) => {  
      console.log(res);
      this.statusCRUD = "Active User Successfully!";
      setTimeout(() => {  this.statusCRUD = ""; }, 4000);
      this.ngOnInit();
    });    
    },err => console.log(err));
    }
}
  deleteById(id)
  {
    if (confirm('Do you want to remove this user?') == true) {
    this.userService.getUserById(id).subscribe((res) => {
    this.userService.user = res as User; 
    console.log(this.userService.user)
    this.userForm.value.userId = this.userService.user.userId;
    this.userForm.value.roles = [];
    this.userForm.value.avatar = this.userService.user.avatar;
    this.userForm.value.userName = this.userService.user.userName;  
    this.userForm.value.status = this.userService.user.status;  
    this.userForm.value.phoneNumber = this.userService.user.phoneNumber;  
    console.log(JSON.stringify(this.userForm.value)); 
    this.userService.updateUser(this.userForm.value).subscribe((res) => {  
      console.log(res);
      this.userService.deleteUser(id).subscribe(
        data=>{ 
          console.log(data);
         this.statusCRUD = "Delete User Successfully!";
         setTimeout(() => {  this.statusCRUD = ""; }, 4000);
          this.ngOnInit();
        },
      error=>console.log(error)
      )
    });    
    },err => console.log(err));
    }
  }
  AccessDenied(){
    this.statusCRUD = "Access Denied!";
    setTimeout(() => {  this.statusCRUD = ""; }, 4000);
  }
  moveToAdminCustomer(){
    this._router.navigate(['/manageCustomer']);
  }
  moveToAdminUser(){
    this._router.navigate(['/manageUser']);
  }
  // getUserProfile(){
  //   this._router.navigate(['/adminUserProfile']);
  // }
  insertUser(){
    this._router.navigate(['/insertUser']);
  }
}
