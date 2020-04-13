import { Component, OnInit, ViewChild } from '@angular/core';
import {MatPaginator} from '@angular/material/paginator';
import {MatSort} from '@angular/material/sort';
import {MatTableDataSource} from '@angular/material/table';
import { Router, ActivatedRoute } from '@angular/router';
import { AuthService } from 'src/app/services/auth-service/auth.service';
import { PaymentService } from 'src/app/services/payment-service/payment.service';
import { OrderService } from 'src/app/services/order-service/order.service';
import { Order } from 'src/app/services/order-service/order.model';
import { CustomerService } from 'src/app/services/customer-service/customer.service';
import { Customer } from 'src/app/services/customer-service/customer.model';
import { OrderDetails } from 'src/app/services/orderdetails-service/order-details.model';
import { OrderDetailsService } from 'src/app/services/orderdetails-service/order-details.service';
import { Location } from '@angular/common';

declare var $:any;
@Component({
  selector: 'app-order-history-details',
  templateUrl: './order-history-details.component.html',
  styleUrls: ['./order-history-details.component.css']
})
export class OrderHistoryDetailsComponent implements OnInit {
  isLoggedIn :Boolean = false
  roles: string[] = []
  statusCRUD: String = ""
  displayedColumns: string[] = ['orderDetailsId','order','recipe', 'quantity','price','notes'];
  dataSource: MatTableDataSource<OrderDetails>;

  @ViewChild(MatPaginator, {static: true}) paginator: MatPaginator;
  @ViewChild(MatSort, {static: true}) sort: MatSort;
 
  constructor(private _router: Router,private orderDetailsService: OrderDetailsService,private route: ActivatedRoute,private location: Location,
    private authService: AuthService) {
  }

  ngOnInit() {
    this.authService.authInfo.subscribe(val => {
      this.isLoggedIn = val.loggedIn;
      this.roles = val.roles;
      });
      let id = this.route.snapshot.paramMap.get('id');
      console.log(id);
      // this.customerService.getCustomerByUserName(localStorage.getItem("userName")).subscribe(res => {
      //   this.customerService.customer = res as Customer;
      //   this.getOrderByCustomer(this.customerService.customer.customerId);
      // })
      this.getOrderDetailByOrder(id);
  }
  getOrderDetailByOrder(orderId) {
		this.orderDetailsService.getOrderDetailsByOrder(orderId).subscribe((res) => {
      console.log(res)
      this.orderDetailsService.orderDetails = res as OrderDetails[];
      this.dataSource = new MatTableDataSource(this.orderDetailsService.orderDetails);
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

  cancel(){
    this.location.back();
  }
}
