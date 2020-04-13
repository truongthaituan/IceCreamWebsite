import { Component, OnInit, ViewChild } from '@angular/core';
import { RecipeService } from 'src/app/services/recipe-service/recipe.service';
import { Recipe } from 'src/app/services/recipe-service/recipe.model';
declare var $:any;
import {MatPaginator} from '@angular/material/paginator';
import {MatSort} from '@angular/material/sort';
import {MatTableDataSource} from '@angular/material/table';
import { CustomerService } from 'src/app/services/customer-service/customer.service';
import { Customer } from 'src/app/services/customer-service/customer.model';
import { Router } from '@angular/router';
import { FormControl, FormGroup, Validators } from '@angular/forms';
import { AuthService } from 'src/app/services/auth-service/auth.service';
import { PaymentService } from 'src/app/services/payment-service/payment.service';
import { OrderService } from 'src/app/services/order-service/order.service';
import { Order } from 'src/app/services/order-service/order.model';
@Component({
  selector: 'app-admin-order',
  templateUrl: './admin-order.component.html',
  styleUrls: ['./admin-order.component.css']
})
export class AdminOrderComponent implements OnInit {
  isLoggedIn :Boolean = false
  roles: string[] = []
  statusCRUD: String = ""
  displayedColumns: string[] = ['id','customer','paymentOption', 'createDate','deliveryDetail','notes','status','getdetails','remove'];
  dataSource: MatTableDataSource<Order>;

  @ViewChild(MatPaginator, {static: true}) paginator: MatPaginator;
  @ViewChild(MatSort, {static: true}) sort: MatSort;
  // userForm: FormGroup = new FormGroup({
  //   userId: new FormControl(null),
  //   userName: new FormControl(null, [Validators.required]),
  //   status: new FormControl(null, Validators.required),
  //   phoneNumber: new FormControl(null),
  //   avatar: new FormControl(null),
  //   roles: new FormControl(null)
  // })
  constructor(private _router: Router,private orderService: OrderService,
    private authService: AuthService) {

  }

  ngOnInit() {
    this.authService.authInfo.subscribe(val => {
      this.isLoggedIn = val.loggedIn;
      this.roles = val.roles;
      });
    this.refreshOrderList();
  }
  refreshOrderList() {
		this.orderService.getOrderList().subscribe((res) => {
      console.log(res)
      this.orderService.orders = res as Order[];
      this.dataSource = new MatTableDataSource(this.orderService.orders);
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
  // deleteById(user_id: string) {
  //   if (confirm('Do you want to remove this user ?') == true) {
  //   this.userService.deleteUser(user_id).subscribe(
  //     data=>{ 
  //       console.log(data);
  //       this.ngOnInit();
  //     },
  //   error=>console.log(error)
  //   )
  //   }
  // }
  getOrderById(orderId)
  {
    return this._router.navigate(["/manageOrderDetails" + `/${orderId}`]);
  }

  deleteOrderById(id)
  {
    if (confirm('Do you want to remove this order?') == true) {

      this.orderService.deleteOrder(id).subscribe(data=>{ 
          console.log(data);
         this.statusCRUD = "Delete This Order Successfully!";
         setTimeout(() => {  this.statusCRUD = ""; }, 4000);
          this.ngOnInit();
        },
      error=>console.log(error))   
    }
  }

}
