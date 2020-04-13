import { Injectable } from '@angular/core';
import { Customer } from './customer.model';
import { HttpClient } from '@angular/common/http';
import { ApiService } from '../api-service/api.service';
import { map } from 'rxjs/operators';
import { CustomerChangePassword } from './customerChangePassword.model';

@Injectable({
  providedIn: 'root'
})
export class CustomerService {
  customer: Customer;
  customers: Customer[];
  customerChangePassword: CustomerChangePassword
  constructor(private apiService: ApiService) { }
  getCustomerList() {
    return this.apiService.get("/customers").pipe(map(data => data));
  }
  getCustomerById(_id: number){
    return this.apiService.get("/customers"  + `/${_id}`);
  }
  getCustomerByUserName(userName: string){
    return this.apiService.get("/customers/username"  + `/${userName}`);
  }
  updateCustomer(customer: Customer){
    return this.apiService.put("/customers" + `/${customer.customerId}`, customer).pipe(map(data => data));
  }
  createCustomer(body:any){
    return this.apiService.post("/customers",body).pipe(map(data => data));
  }
  deleteCustomer(customerId: number) {
    return this.apiService.delete("/customers" + `/${customerId}`).pipe(map(data => data))
  }
  changePasswordCustomer(customerChangePassword: CustomerChangePassword){
    return this.apiService.put("/customers/changePasswordCustomer",customerChangePassword).pipe(map(data => data))
  }
}
