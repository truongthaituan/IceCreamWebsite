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
  selector: 'app-admin-recipe',
  templateUrl: './admin-recipe.component.html',
  styleUrls: ['./admin-recipe.component.css']
})
export class AdminRecipeComponent implements OnInit {
  isLoggedIn :Boolean = false
  roles: string[] = []
  statusCRUD: String = ""
  displayedColumns: string[] = ['id', 'title','price','status', 'viewCount','getdetails','remove'];
  dataSource: MatTableDataSource<Recipe>;

  @ViewChild(MatPaginator, {static: true}) paginator: MatPaginator;
  @ViewChild(MatSort, {static: true}) sort: MatSort;
  // userForm: FormGroup = new FormGroup({
  //   userId: new FormControl(null),
  //   userName: new FormControl(null, [Validators.required]),
  //   status: new FormControl(null, Validators.required),
  //   phoneNumber: new FormControl(null),
  //   avatar: new FormControl(null),
  //   roles: new FormControl(null)
  // })
  constructor(private _router: Router, private recipeService: RecipeService,
    private authService: AuthService) {

  }

  ngOnInit() {
    this.authService.authInfo.subscribe(val => {
      this.isLoggedIn = val.loggedIn;
      this.roles = val.roles;
      });
    this.refreshRecipeList();
  }
  refreshRecipeList() {
		this.recipeService.getRecipeList().subscribe((res) => {
      console.log(res)
      this.recipeService.recipes = res as Recipe[];
      this.dataSource = new MatTableDataSource(this.recipeService.recipes);
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
  getRecipeById(recipeId)
  {
    return this._router.navigate(["/manageRecipeDetail" + `/${recipeId}`]);
  }

  deleteRecipeById(id)
  {
    if (confirm('Do you want to remove this recipe?') == true) {

      this.recipeService.deleteRecipe(id).subscribe(data=>{ 
          console.log(data);
         this.statusCRUD = "Delete This Recipe Successfully!";
         setTimeout(() => {  this.statusCRUD = ""; }, 4000);
          this.ngOnInit();
        },
      error=>console.log(error))   
    }
  }
moveToAddRecipe(){
  this._router.navigate(['/insertRecipe']);
}
}
