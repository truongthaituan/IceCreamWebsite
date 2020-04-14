import { Component, OnInit } from '@angular/core';
import { Router, ActivatedRoute } from '@angular/router';
import { OrderService } from 'src/app/services/order-service/order.service';
import { Location, DatePipe } from '@angular/common';
import { NgForm } from '@angular/forms';
import { Customer } from 'src/app/services/customer-service/customer.model';
import { Payment } from 'src/app/services/payment-service/payment.model';
import { AuthService } from 'src/app/services/auth-service/auth.service';
import { Order } from 'src/app/services/order-service/order.model';
declare var $:any;
@Component({
  selector: 'app-order-details-edit',
  templateUrl: './order-details-edit.component.html',
  styleUrls: ['./order-details-edit.component.css']
})
export class OrderDetailsEditComponent implements OnInit {
  isLoggedIn: boolean = false
  roles: string[] = []
  constructor(private _router: Router,private route: ActivatedRoute,
    private orderService: OrderService, private location: Location , private authService: AuthService) {
  
 }

ngOnInit() {
  
  this.authService.authInfo.subscribe(val => {
    this.isLoggedIn = val.loggedIn;
    this.roles = val.roles;
  });
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
  this.orderService.order.status = String(Object.values(res)[7]);
  })
}

previousUrl: string;

cancel(){
  this.location.back();
}
moveToManageOrderDetails(id){
  return this._router.navigate(["/manageOrderDetailsUser" + `/${id}`]);
 }
//  this.orderForm.value.customer = { "customerId": this.customerService.customer.customerId };
//  this.orderForm.value.payment = { "paymentId": Object.values(res)[0]}; 
//  this.orderForm.value.createDate = this.now;
//  this.orderForm.value.status = true;
//  this.orderService.createOrder(this.orderForm.value).subscribe(res => {
//    console.log(res)
//    this.orderService.order = res as Order
//    for(let i = 0;i < this.CartRecipe.length;i++) {
//    this.orderDetailForm.value.order = { "id": this.orderService.order.id };
//      this.orderDetailForm.value.recipe = { "id": this.CartRecipe[i].id };
//      this.orderDetailForm.value.quantity = this.CartRecipe[i].quantity;
//      this.orderDetailForm.value.price = this.CartRecipe[i].price;
//      this.orderDetailForm.value.notes = this.orderService.order.notes;
//      this.orderDetailsService.createOrderDetails(this.orderDetailForm.value).subscribe(res => {
//        console.log(res)

//      });
//    }  
//  })
onSubmit(form: NgForm) {
  let id = this.route.snapshot.paramMap.get('id');
  if (confirm('Do you want to change status this this order ?') == true) {
   console.log(form.value);
   form.value.id = id;
   this.orderService.getOrderById(Number(id)).subscribe((res) => {
      this.orderService.order = res as Order;
      form.value.customer = { "customerId": this.orderService.order.customer.customerId };
      form.value.payment = { "paymentId":  this.orderService.order.payment.paymentId}; 
      // form.value.status
     this.orderService.updateOrder(form.value).subscribe(
      data => {
        console.log(data);
        this.location.back();
       // this.showMsg = true;
       // sessionStorage.setItem('showMsg',String(this.showMsg))
     },
      error => console.log(error)
     );
    })
  
   console.log('Your form data: '+  form.value)
    }
  }
}
