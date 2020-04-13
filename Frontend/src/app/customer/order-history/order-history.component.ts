import { Component, OnInit, ViewChild } from '@angular/core';
import {MatPaginator} from '@angular/material/paginator';
import {MatSort} from '@angular/material/sort';
import {MatTableDataSource} from '@angular/material/table';
import { Router } from '@angular/router';
import { AuthService } from 'src/app/services/auth-service/auth.service';
import { PaymentService } from 'src/app/services/payment-service/payment.service';
import { OrderService } from 'src/app/services/order-service/order.service';
import { Order } from 'src/app/services/order-service/order.model';
import { CustomerService } from 'src/app/services/customer-service/customer.service';
import { Customer } from 'src/app/services/customer-service/customer.model';
import { OrderDetailsService } from 'src/app/services/orderdetails-service/order-details.service';
import { OrderDetails } from 'src/app/services/orderdetails-service/order-details.model';

declare var $:any;

@Component({
  selector: 'app-order-history',
  templateUrl: './order-history.component.html',
  styleUrls: ['./order-history.component.css']
})
export class OrderHistoryComponent implements OnInit {
  isLoggedIn :Boolean = false
  roles: string[] = []
  statusCRUD: String = ""
  displayedColumns: string[] = ['id','customer','paymentOption', 'createDate','deliveryDetail','notes','status','getdetails','remove'];
  dataSource: MatTableDataSource<Order>;

  @ViewChild(MatPaginator, {static: true}) paginator: MatPaginator;
  @ViewChild(MatSort, {static: true}) sort: MatSort;
 
  constructor(private _router: Router,private orderService: OrderService,private orderDetailsService: OrderDetailsService,
    private authService: AuthService, private customerService: CustomerService) {

  }

  ngOnInit() {
    this.authService.authInfo.subscribe(val => {
      this.isLoggedIn = val.loggedIn;
      this.roles = val.roles;
      });
      this.customerService.getCustomerByUserName(localStorage.getItem("userName")).subscribe(res => {
        this.customerService.customer = res as Customer;
        this.getOrderByCustomer(this.customerService.customer.customerId);
      })
  }

  getOrderByCustomer(customerId) {
		this.orderService.getOrderByCustomer(customerId).subscribe((res) => {
      console.log(res)
      this.orderService.orders = res as Order[];
      this.dataSource = new MatTableDataSource(this.orderService.orders);
      this.dataSource.paginator = this.paginator;
      this.dataSource.sort = this.sort;
		});
  }
  deleteByOrderId(orderId){
    if (confirm('Do you want to delete this order?') == true) {
        this.orderService.deleteOrder(orderId).subscribe(res => {
          console.log(res)
        });
    }
  }

  deleteOrderDetailByOrder(orderId) {
    if (confirm('Do you want to delete this order?') == true) {
		this.orderDetailsService.getOrderDetailsByOrder(orderId).subscribe((res) => {
      console.log(res)
      this.orderDetailsService.orderDetails = res as OrderDetails[];
      for(let i = 0; i <  this.orderDetailsService.orderDetails.length;i++){
        this.orderDetailsService.deleteOrderDetails(this.orderDetailsService.orderDetails[i].orderDetailsId).subscribe(res =>{
          console.log(res)   
          this.orderService.deleteOrder(orderId).subscribe(res => {
            console.log(res)
            this.ngOnInit();
          })  
        })
      }   
    });  
  }
}

  applyFilter(event: Event) {
    const filterValue = (event.target as HTMLInputElement).value;
    this.dataSource.filter = filterValue.trim().toLowerCase();
    if (this.dataSource.paginator) {
      this.dataSource.paginator.firstPage();
    }
  }
  
  getOrderDetailsById(orderId)
  {
    return this._router.navigate(["/orderHistoryDetails" + `/${orderId}`]);
  }
}
