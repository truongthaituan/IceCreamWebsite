import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { UserService } from 'src/app/user-service/user.service';
import { CustomerService } from 'src/app/services/customer-service/customer.service';
import { Customer } from 'src/app/services/customer-service/customer.model';
import { SendEmail } from 'src/app/services/sendmail-service/send-email.model';
import { SendEmailService } from 'src/app/services/sendmail-service/send-email.service';
import { FormGroup, FormControl, Validators, NgForm } from '@angular/forms';
import { PaymentService } from 'src/app/services/payment-service/payment.service';
import { OrderService } from 'src/app/services/order-service/order.service';
import { OrderDetailsService } from 'src/app/services/orderdetails-service/order-details.service';
import { Order } from 'src/app/services/order-service/order.model';
import { Location } from '@angular/common';

declare var $:any;
@Component({
  selector: 'app-customer-payment',
  templateUrl: './customer-payment.component.html',
  styleUrls: ['./customer-payment.component.css']
})
export class CustomerPaymentComponent implements OnInit {
  public now: Date = new Date();

  constructor(private _router: Router, private customerService: CustomerService,private paymentService: PaymentService,private location : Location,
     private sendEmailService: SendEmailService,private orderService: OrderService, private orderDetailsService: OrderDetailsService) { }
  //chứa thông tin giỏ hàng
  CartRecipe = [];
  // Lưu tổng tiền và tổng số lượng chung
  TongTien = 0;
  TongCount = 0;
  lengthCartRecipe = 0;
  userName : String = ""
  statusLogin: String = ""
  statusPayment: boolean = false
  alertMessage: string = ""
  paymentForm: FormGroup = new FormGroup({
    cardType: new FormControl(null, [Validators.required]),
    cardNumber: new FormControl(null, Validators.required),
    cvv: new FormControl(null, Validators.required),
    nameOnCard: new FormControl(null, Validators.required),
    expiredDate: new FormControl(null),
    dateOfBirth: new FormControl(null)
  })
  orderForm: FormGroup = new FormGroup({
    customer: new FormControl(null, [Validators.required]),
    payment: new FormControl(null, Validators.required),
    paymentOption: new FormControl(null, Validators.required),
    createDate: new FormControl(null, Validators.required),
    deliveryDetail: new FormControl(null),
    notes: new FormControl(null),
    status: new FormControl(null)
  })
  orderDetailForm: FormGroup = new FormGroup({
    order: new FormControl(null, [Validators.required]),
    recipe: new FormControl(null, Validators.required),
    quantity: new FormControl(null, Validators.required),
    price: new FormControl(null, Validators.required),
  })
  ngOnInit() {
    this.statusLogin = localStorage.getItem("statusLogin");
    if (this.statusLogin == null) { this._router.navigate(['/login']); }
    this.userName = localStorage.getItem("userName")
    this.getCustomerByUsername(this.userName)
     //get giỏ hàng
   this.CartRecipe = JSON.parse(localStorage.getItem("CartRecipe"));
   this.getTotalCountAndPrice();
   this.initialPayment();
   this.initialOrder();
  }

  initialPayment() {
    this.paymentService.payment = ({
      paymentId: null,
      cardType: '',
      cardNumber: null,
      cvv: '',
      nameOnCard: '',
      expiredDate: null,
      dateOfBirth: null
    });
  }
  initialOrder() {
    this.orderService.order = ({
      id: null,
      customer: null,
      payment: null,
      paymentOption: '',
      createDate: '',
      deliveryDetail: null,
      notes: null,
      status: null
    });
  }
  initialOrderDetails() {
    this.orderDetailsService.orderDetail = ({
      orderDetailsId: null,
      order: null,
      recipe: null,
      quantity: null,
      price: null
    });
  }
    // set độ dài của giỏ hàng
    cartRecipeLength(CartRecipe) {
      if (CartRecipe == null) {
        this.lengthCartRecipe = 0;
      } else {
        this.lengthCartRecipe = CartRecipe.length;
      }
    }
 //get total count and price trên header
 getTotalCountAndPrice() {
  this.TongTien = 0;
  this.TongCount = 0;
  this.CartRecipe = JSON.parse(localStorage.getItem("CartRecipe"));
  this.cartRecipeLength(this.CartRecipe);
  if (this.CartRecipe != null) {
    for (var i = 0; i < this.lengthCartRecipe; i++) {
      this.TongTien += parseInt(this.CartRecipe[i].price) * parseInt(this.CartRecipe[i].quantity);
      this.TongCount += parseInt(this.CartRecipe[i].quantity);
    }
  }
    $('.item-total').html("&nbsp;" + this.TongTien.toString());
    $('.item-count').html(this.TongCount.toString());
    localStorage.setItem("TongTien", this.TongTien.toString());
    localStorage.setItem("TongCount", this.TongCount.toString());
  }
  getCustomerByUsername(userName){
    this.customerService.getCustomerByUserName(userName).subscribe(res => {
      this.customerService.customer = res as Customer;
      console.log(res);
    })
  }

createPayment(){
  this.now = new Date();
  this.paymentForm.value.dateOfBirth = this.customerService.customer.dateOfBirth;
  console.log(this.paymentForm.value)
  this.paymentService.createPayment(this.paymentForm.value).subscribe(res => {
    console.log(res)
    this.orderForm.value.customer = { "customerId": this.customerService.customer.customerId };
    this.orderForm.value.payment = { "paymentId": Object.values(res)[0]}; 
    this.orderForm.value.createDate = this.now;
    this.orderForm.value.status = "New";
    // this.orderForm.value.notes = this.orderService.order.notes;
    this.orderService.createOrder(this.orderForm.value).subscribe(res => {
      console.log(res)
      this.orderService.order = res as Order
      for(let i = 0;i < this.CartRecipe.length;i++) {
      this.orderDetailForm.value.order = { "id": this.orderService.order.id };
        this.orderDetailForm.value.recipe = { "id": this.CartRecipe[i].id };
        this.orderDetailForm.value.quantity = this.CartRecipe[i].quantity;
        this.orderDetailForm.value.price = this.CartRecipe[i].price;
        this.orderDetailsService.createOrderDetails(this.orderDetailForm.value).subscribe(res => {
          console.log(res)

        });
      }  
    })
  });
}

  SendMail(sendEmail: SendEmail){
    this.now = new Date();
    var dem=0;
    sendEmail.email= this.customerService.customer.email;
    sendEmail.userName = this.customerService.customer.userName;
    sendEmail.address = this.customerService.customer.address;
    sendEmail.phone = this.customerService.customer.phone;
    sendEmail.title = "";
    sendEmail.price = "";
    sendEmail.quantity = "";
    sendEmail.subTotal = "";
    sendEmail.createDate = "";
    sendEmail.total = this.TongTien.toString();
    sendEmail.createDate = this.now.toString().substring(0,24);
    for(let i of this.CartRecipe){  
          sendEmail.title += i.title +"next";
          sendEmail.price += i.price +"next";
          sendEmail.quantity += i.quantity +"next";  
          sendEmail.subTotal += (i.price * i.quantity)+"next";
      }
      console.log(sendEmail.title)
      this.sendEmailService.actionSendMail(sendEmail).subscribe(
        res =>{
           console.log(res); 
           this.statusPayment = true;
           this.alertMessage = "Order Successfully! You can check this order in your gmail!"
          //  setTimeout(() => { this.alertMessage = ""; this.statusPayment = false }, 4000);
           if(Object.values(res)[0] == "Send Mail Successfully!"){   
             localStorage.removeItem('CartRecipe');
             localStorage.removeItem('TongCount');
             localStorage.removeItem('TongTien');     
             setTimeout(() => { window.location.href = "/orderHistory" }, 4000);
          } 
      },
        error => console.log(error)
      )
    }
backToCart(){
  this.location.back();
}
}
