import { Injectable } from '@angular/core';
import { OrderDetails } from './order-details.model';
import { ApiService } from '../api-service/api.service';
import { Observable } from 'rxjs';
import { map } from 'rxjs/operators';

@Injectable({
  providedIn: 'root'
})
export class OrderDetailsService {
  orderDetail: OrderDetails;
  orderDetails: OrderDetails[];
  constructor(private apiService: ApiService) { }

  getOrderDetailList(): Observable<OrderDetails[]> {
    return this.apiService.get("/order_details").pipe(map(data => data));
  }
  getOrderDetailsByOrder(orderId: number){
    return this.apiService.get("/order_details/orders"  + `/${orderId}`);
  }
  getOrderDetailsByRecipe(recipeId: number){
    return this.apiService.get("/order_details/recipes"  + `/${recipeId}`);
  }
  getOrderDetailsById(orderDetailsId: number){
    return this.apiService.get("/order_details"  + `/${orderDetailsId}`);
  }
  // updateOrder(order: Order){
  //   return this.apiService.put("/orders"+ `/${order.id}`, order);
  // }
  createOrderDetails(body:any){
    return this.apiService.post("/order_details",body).pipe(map(data => data));
  }
  deleteOrderDetails(orderDetailsId: number) {
    return this.apiService.delete("/order_details" + `/${orderDetailsId}`).pipe(map(data => data))
  }
}
