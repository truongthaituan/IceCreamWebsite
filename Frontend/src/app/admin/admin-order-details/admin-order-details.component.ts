import { Component, OnInit } from '@angular/core';
import { Router, ActivatedRoute } from '@angular/router';
import { OrderService } from 'src/app/services/order-service/order.service';
import { Location, DatePipe } from '@angular/common';
import { NgForm } from '@angular/forms';
import { Customer } from 'src/app/services/customer-service/customer.model';
import { Payment } from 'src/app/services/payment-service/payment.model';
declare var $:any;
@Component({
  selector: 'app-admin-order-details',
  templateUrl: './admin-order-details.component.html',
  styleUrls: ['./admin-order-details.component.css']
})
export class AdminOrderDetailsComponent implements OnInit {

  constructor(private _router: Router,private route: ActivatedRoute,
    private orderService: OrderService, private location: Location) {
  
 }

ngOnInit() {
 let id = this.route.snapshot.paramMap.get('id');
 console.log(id);
 this.resetForm();
 this.getOrderById(Number(id));
//  this.getAllUser();
//  this.getAllIceCream();
  }
resetForm(form?: NgForm) {
  if (form)
    form.reset();
  this.orderService.order = {
    id: null,
    customer: Customer,
    payment: Payment,
    paymentOption: "",
    createDate: "",
    deliveryDetail: null,
    notes: null,
    status: null
  }
}
getOrderById(id: number){
  this.orderService.getOrderById(id).subscribe((res) => {
  this.orderService.order.id = Number(Object.values(res)[0]);
  this.orderService.order.customer = Object.values(res)[1];
  this.orderService.order.payment = Object.values(res)[2];
  this.orderService.order.paymentOption = String(Object.values(res)[3]);
  this.orderService.order.createDate = String(Object.values(res)[4]);
  this.orderService.order.deliveryDetail = String(Object.values(res)[5]);
  this.orderService.order.notes = String(Object.values(res)[6]);
  this.orderService.order.status = Boolean(Object.values(res)[7]);
  // this.recipeService.recipe = res as Recipe;
  console.log(Boolean(Object.values(res)[6]))
  })
}

previousUrl: string;

cancel(){
  this.location.back();
}

// onSubmit(form: NgForm) {
//   let id = this.route.snapshot.paramMap.get('id');
//   if (confirm('Do you want to update information this recipe ?') == true) {
//    form.value.image =  $('input[type=file]').val().replace(/C:\\fakepath\\/i, 'images/');
//    console.log(form.value.image);
//    form.value.id = parseInt(id);
//    form.value.user = { "userId":  form.value.userId };
//    form.value.icecream = { "id":  form.value.icecreamId };
//   this.recipeService.updateRecipe(form.value).subscribe(
//    data => {
//      console.log(data);
//      this.location.back();
//     // this.showMsg = true;
//     // sessionStorage.setItem('showMsg',String(this.showMsg))
//   },
//    error => console.log(error)
//   );
//    console.log('Your form data: '+  form.value)
//     }
//   }
}
