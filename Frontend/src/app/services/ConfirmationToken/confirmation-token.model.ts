import { Customer } from '../customer-service/customer.model';

export class ConfirmationToken {
    tokenid: number;
    confirmationToken: string;
    createdDate: string;
    customer: Customer;
}
