import { Component, OnInit, ViewChild } from '@angular/core';
import {MatPaginator} from '@angular/material/paginator';
import {MatSort} from '@angular/material/sort';
import {MatTableDataSource} from '@angular/material/table';
import { Router } from '@angular/router';
import { FormControl, FormGroup, Validators } from '@angular/forms';
import { AuthService } from 'src/app/services/auth-service/auth.service';
import { OrderService } from 'src/app/services/order-service/order.service';
import { Order } from 'src/app/services/order-service/order.model';
import { FeedbackService } from 'src/app/services/feedback-service/feedback.service';
import { Feedback } from 'src/app/services/feedback-service/feedback.model';
declare var $:any;
@Component({
  selector: 'app-admin-feedback',
  templateUrl: './admin-feedback.component.html',
  styleUrls: ['./admin-feedback.component.css']
})
export class AdminFeedbackComponent implements OnInit {

  isLoggedIn :Boolean = false
  roles: string[] = []
  statusCRUD: String = ""
  displayedColumns: string[] = ['feedbackId','customer','order', 'details','createDate','getdetails','remove'];
  dataSource: MatTableDataSource<Feedback>;

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
  constructor(private _router: Router,private feedbackService: FeedbackService,
    private authService: AuthService) {

  }

  ngOnInit() {
    this.authService.authInfo.subscribe(val => {
      this.isLoggedIn = val.loggedIn;
      this.roles = val.roles;
      });
    this.refreshFeedbackList();
  }
  refreshFeedbackList() {
		this.feedbackService.getFeedbackList().subscribe((res) => {
      console.log(res)
      this.feedbackService.feedbacks = res as Feedback[];
      this.dataSource = new MatTableDataSource(this.feedbackService.feedbacks);
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
  deleteById(feedbackId: number) {
    if (confirm('Do you want to remove this feedback ?') == true) {
    this.feedbackService.deleteFeedback(feedbackId).subscribe(
      data=>{ 
        console.log(data);
        this.statusCRUD = "Delete Feedback Successfully!";
        setTimeout(() => {  this.statusCRUD = ""; }, 4000);
        this.ngOnInit();
      },
    error=>console.log(error)
    )
    }
  }
  getFeedbackById(feedbackId)
  {
    return this._router.navigate(["/manageFeedbackDetails" + `/${feedbackId}`]);
  }

  // deleteOrderById(id)
  // {
  //   if (confirm('Do you want to remove this order?') == true) {

  //     this.orderService.deleteOrder(id).subscribe(data=>{ 
  //         console.log(data);
  //        this.statusCRUD = "Delete This Order Successfully!";
  //        setTimeout(() => {  this.statusCRUD = ""; }, 4000);
  //         this.ngOnInit();
  //       },
  //     error=>console.log(error))   
  //   }
  // }

}
