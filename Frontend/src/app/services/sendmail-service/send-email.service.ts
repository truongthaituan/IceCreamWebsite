import { Injectable } from '@angular/core';
import { SendEmail } from './send-email.model';
import { ApiService } from '../api-service/api.service';
import { map } from 'rxjs/operators';

@Injectable({
  providedIn: 'root'
})
export class SendEmailService {
  sendMail: SendEmail;
  sendMails: SendEmail[];
  constructor(private apiService: ApiService) { }

  actionSendMail(sendMail: SendEmail) {
    return this.apiService.post("/sendmail", sendMail).pipe(map(data => data));
  }}
