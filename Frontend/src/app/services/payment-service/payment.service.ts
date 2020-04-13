import { Injectable } from '@angular/core';
import { ApiService } from '../api-service/api.service';
import { Payment } from './payment.model';
import { Observable } from 'rxjs';
import { map } from 'rxjs/operators';

@Injectable({
  providedIn: 'root'
})
export class PaymentService {
  payment: Payment;
  payments: Payment[];
  constructor(private apiService: ApiService) { }

  getPaymentList(): Observable<Payment[]> {
    return this.apiService.get("/payments").pipe(map(data => data));
  }
  getPaymentById(_id: number){
    return this.apiService.get("/payments"  + `/${_id}`);
  }
  updatePayment(payment: Payment){
    return this.apiService.put("/payments"+ `/${payment.paymentId}`, payment);
  }
  createPayment(body:any){
    return this.apiService.post("/payments",body).pipe(map(data => data));
  }
  deletePayment(paymentId: number) {
    return this.apiService.delete("/payments" + `/${paymentId}`).pipe(map(data => data))
  }
}
