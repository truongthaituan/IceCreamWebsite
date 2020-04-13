import { Injectable } from '@angular/core';
import { Order } from './order.model';
import { Observable } from 'rxjs';
import { map } from 'rxjs/operators';
import { ApiService } from '../api-service/api.service';

@Injectable({
  providedIn: 'root'
})
export class OrderService {
  order: Order;
  orders: Order[];
  constructor(private apiService: ApiService) { }

  getOrderList(): Observable<Order[]> {
    return this.apiService.get("/orders").pipe(map(data => data));
  }
  getOrderById(_id: number){
    return this.apiService.get("/orders"  + `/${_id}`);
  }
  getOrderByCustomer(customerId: number){
    return this.apiService.get("/orders/customer"  + `/${customerId}`);
  }
  updateOrder(order: Order){
    return this.apiService.put("/orders"+ `/${order.id}`, order);
  }
  createOrder(body:any){
    return this.apiService.post("/orders",body).pipe(map(data => data));
  }
  deleteOrder(orderId: number) {
    return this.apiService.delete("/orders" + `/${orderId}`).pipe(map(data => data))
  }
}
