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
  selector: 'app-add-recipe',
  templateUrl: './add-recipe.component.html',
  styleUrls: ['./add-recipe.component.css']
})
export class AddRecipeComponent implements OnInit {

  constructor(private _router: Router,private route: ActivatedRoute,private userService: UserService,private iceCreamService: IcecreamService,
    private recipeService: RecipeService, private location: Location) {
  
 }

ngOnInit() {
 this.resetForm();
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
statusAddRecipe: boolean = false
alertMessage: string = ""
onSubmit(form: NgForm) {
  let id = this.route.snapshot.paramMap.get('id');
  if (confirm('Do you want to update information this recipe ?') == true) {
   form.value.image =  $('input[type=file]').val().replace(/C:\\fakepath\\/i, 'images/');
   console.log(form.value.image);
   form.value.id = parseInt(id);
   form.value.user = { "userId":  form.value.userId };
   form.value.icecream = { "id":  form.value.icecreamId };
  this.recipeService.createRecipe(form.value).subscribe(
   data => {
     console.log(data);
     this.statusAddRecipe = true;
     this.alertMessage = "Insert Recipe Successfully!"
     this.ngOnInit();
     setTimeout(() => {this.alertMessage="";this.statusAddRecipe=false}, 4000); 
         // this.showMsg = true;
    // sessionStorage.setItem('showMsg',String(this.showMsg))
  },
   error => console.log(error)
  );
   console.log('Your form data: '+  form.value)
    }
  }
}
