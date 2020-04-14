import { Component, OnInit } from '@angular/core';
import { Router, ActivatedRoute } from '@angular/router';
import { OrderService } from 'src/app/services/order-service/order.service';
import { Location, DatePipe } from '@angular/common';
import { NgForm } from '@angular/forms';
import { Customer } from 'src/app/services/customer-service/customer.model';
import { Payment } from 'src/app/services/payment-service/payment.model';
import { AuthService } from 'src/app/services/auth-service/auth.service';
declare var $:any;
@Component({
  selector: 'app-admin-order-details',
  templateUrl: './admin-order-details.component.html',
  styleUrls: ['./admin-order-details.component.css']
})
export class AdminOrderDetailsComponent implements OnInit {
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
 moveToManageOrderEdit(id){
  return this._router.navigate(["/manageOrderEdit" + `/${id}`]);
 }

}
