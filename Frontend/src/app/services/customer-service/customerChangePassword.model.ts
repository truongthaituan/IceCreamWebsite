import { Customer } from './customer.model';

export class CustomerChangePassword {
   customer: Customer;
   currentPassword: string;
   newPassword: string;
   confirmPassword: string;
   
}
