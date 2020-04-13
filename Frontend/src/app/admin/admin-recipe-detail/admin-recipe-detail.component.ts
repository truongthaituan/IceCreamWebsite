import { Component, OnInit } from '@angular/core';
import { NgForm } from '@angular/forms';
import { Location, DatePipe } from '@angular/common';
import { Router, ActivatedRoute } from '@angular/router';
import { RecipeService } from 'src/app/services/recipe-service/recipe.service';
import { Recipe } from 'src/app/services/recipe-service/recipe.model';
import { UserService } from 'src/app/user-service/user.service';
import { User } from 'src/app/user-service/user.model';
import { IcecreamService } from 'src/app/services/icecream-service/icecream.service';
import { Icecream } from 'src/app/services/icecream-service/icecream.model';
declare var $:any;
@Component({
  selector: 'app-admin-recipe-detail',
  templateUrl: './admin-recipe-detail.component.html',
  styleUrls: ['./admin-recipe-detail.component.css']
})
export class AdminRecipeDetailComponent implements OnInit {

  constructor(private _router: Router,private route: ActivatedRoute,private userService: UserService,private iceCreamService: IcecreamService,
    private recipeService: RecipeService, private location: Location) {
  
 }

ngOnInit() {
 let id = this.route.snapshot.paramMap.get('id');
 console.log(id);
 this.resetForm();
 this.getRecipeById(Number(id));

 this.getAllUser();
 this.getAllIceCream();
  }
resetForm(form?: NgForm) {
  if (form)
    form.reset();
  this.recipeService.recipe = {
    id: null,
    user: User,
    icecream: Icecream,
    title: "",
    description: "",
    price: null,
    viewCount: null,
    image: null,
    status: null,
    uploadDate: null,
    details: null,
    quantity: null
  }
}
getRecipeById(id: number){
  this.recipeService.getRecipeById(id).subscribe((res) => {
    this.recipeService.recipe.id = Number(Object.values(res)[0]);
  this.recipeService.recipe.user = Object.values(res)[1];
  this.recipeService.recipe.icecream = Object.values(res)[2];
  this.recipeService.recipe.title = String(Object.values(res)[3]);
  this.recipeService.recipe.description = String(Object.values(res)[4]);
  this.recipeService.recipe.price = Number(Object.values(res)[5]);
  this.recipeService.recipe.status = Boolean(Object.values(res)[6]);
  this.recipeService.recipe.viewCount = Number(Object.values(res)[7]);
  this.recipeService.recipe.image = String(Object.values(res)[8]);
  this.recipeService.recipe.details = String(Object.values(res)[9]);
  this.recipeService.recipe.uploadDate = String(Object.values(res)[10]);
  // this.recipeService.recipe = res as Recipe;
  console.log(Boolean(Object.values(res)[6]))
  })
}

getAllUser(){
  this.userService.listAllUsers().subscribe((res) => {
  this.userService.users = res as User[];
  })
}
getAllIceCream(){
  this.iceCreamService.getIceCreamList().subscribe((res) => {
  this.iceCreamService.Icecreams = res as Icecream[];
  })
}
previousUrl: string;

cancel(){
  this.location.back();
}

onSubmit(form: NgForm) {
  let id = this.route.snapshot.paramMap.get('id');
  if (confirm('Do you want to update information this recipe ?') == true) {
   form.value.image =  $('input[type=file]').val().replace(/C:\\fakepath\\/i, 'images/');
   console.log(form.value.image);
   form.value.id = parseInt(id);
   form.value.user = { "userId":  form.value.userId };
   form.value.icecream = { "id":  form.value.icecreamId };
  this.recipeService.updateRecipe(form.value).subscribe(
   data => {
     console.log(data);
     this.location.back();
    // this.showMsg = true;
    // sessionStorage.setItem('showMsg',String(this.showMsg))
  },
   error => console.log(error)
  );
   console.log('Your form data: '+  form.value)
    }
  }
}