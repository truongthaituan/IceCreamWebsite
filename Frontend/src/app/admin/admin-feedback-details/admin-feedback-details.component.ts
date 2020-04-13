import { Component, OnInit } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { FeedbackService } from 'src/app/services/feedback-service/feedback.service';
import { Location, DatePipe } from '@angular/common';
import { NgForm } from '@angular/forms';
import { Customer } from 'src/app/services/customer-service/customer.model';
import { Payment } from 'src/app/services/payment-service/payment.model';
import { Order } from 'src/app/services/order-service/order.model';

@Component({
  selector: 'app-admin-feedback-details',
  templateUrl: './admin-feedback-details.component.html',
  styleUrls: ['./admin-feedback-details.component.css']
})
export class AdminFeedbackDetailsComponent implements OnInit {

  constructor(private _router: Router,private route: ActivatedRoute,
    private feedbackService: FeedbackService, private location: Location) {
  
 }

ngOnInit() {
 let id = this.route.snapshot.paramMap.get('id');
 console.log(id);
 this.resetForm();
 this.getFeedbackById(Number(id));
//  this.getAllUser();
//  this.getAllIceCream();
  }
resetForm(form?: NgForm) {
  if (form)
    form.reset();
  this.feedbackService.feedback = {
    feedbackId: null,
    customer: Customer,
    order: Order,
    details: "",
    createDate: null
  }
}
getFeedbackById(id: number){
  this.feedbackService.getFeedbackById(id).subscribe((res) => {
  this.feedbackService.feedback.feedbackId = Number(Object.values(res)[0]);
  this.feedbackService.feedback.order = Object.values(res)[1];
  this.feedbackService.feedback.details = String(Object.values(res)[2]);
  this.feedbackService.feedback.createDate = String(Object.values(res)[3]);
  // this.recipeService.recipe = res as Recipe;
  console.log(Object.values(res)[1])
  })
}

previousUrl: string;

cancel(){
  this.location.back();
}

}
