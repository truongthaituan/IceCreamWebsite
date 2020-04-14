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
import { OrderService } from 'src/app/services/order-service/order.service';
import { OrderDetailsService } from 'src/app/services/orderdetails-service/order-details.service';
import { OrderDetails } from 'src/app/services/orderdetails-service/order-details.model';
import { FeedbackService } from 'src/app/services/feedback-service/feedback.service';
import { Feedback } from 'src/app/services/feedback-service/feedback.model';
declare var $:any;
@Component({
  selector: 'app-admin-recipe-detail',
  templateUrl: './admin-recipe-detail.component.html',
  styleUrls: ['./admin-recipe-detail.component.css']
})
export class AdminRecipeDetailComponent implements OnInit {

  constructor(private _router: Router,private route: ActivatedRoute,private userService: UserService,private iceCreamService: IcecreamService,
    private recipeService: RecipeService, private location: Location, private feedbackService: FeedbackService,
    private orderDetailsService: OrderDetailsService) {
  
 }

ngOnInit() {
 let id = this.route.snapshot.paramMap.get('id');
 console.log(id);
 this.resetForm();
 this.getRecipeById(Number(id));
 this.getAllUser();
 this.getAllIceCream();
 this.getRecipeFeedback(id)
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

  moveToRecipeEdit(recipeId){
    return this._router.navigate(["/manageRecipeEdit" + `/${recipeId}`]);
  }
  feedbackList : Feedback[] = []
  getRecipeFeedback(recipeId){
    this.orderDetailsService.getOrderDetailsByRecipe(recipeId).subscribe(res => {
      this.orderDetailsService.orderDetails = res as OrderDetails[];
      console.log(res)
      for(let i = 0; i < this.orderDetailsService.orderDetails.length;i++){

        this.feedbackService.getFeedbackList().subscribe(res => {
          this.feedbackService.feedbacks = res as Feedback[];
          for(let j = 0; j <  this.feedbackService.feedbacks.length;j++ ){
            if(this.orderDetailsService.orderDetails[i].order.id == this.feedbackService.feedbacks[j].order.id) {
              console.log(this.feedbackService.feedbacks[j].order.id)
              this.feedbackList.push(this.feedbackService.feedbacks[j])
              // console.log(this.feedbackList)
            }
          }
              

        })

      }
    })
  }
}