import { Injectable } from '@angular/core';
import { ConfirmationToken } from './confirmation-token.model';

@Injectable({
  providedIn: 'root'
})
export class ConfirmationTokenService {
  confirmationToken: ConfirmationToken
  constructor() { }
}
