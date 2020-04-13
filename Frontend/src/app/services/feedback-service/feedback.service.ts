import { Injectable } from '@angular/core';
import { Feedback } from './feedback.model';
import { ApiService } from '../api-service/api.service';
import { Observable } from 'rxjs';
import { map } from 'rxjs/operators';

@Injectable({
  providedIn: 'root'
})
export class FeedbackService {
  feedback: Feedback;
  feedbacks: Feedback[];
  constructor(private apiService: ApiService) { }

  getFeedbackList(): Observable<Feedback[]> {
    return this.apiService.get("/feedbacks").pipe(map(data => data));
  }
  getFeedbackById(_id: number){
    return this.apiService.get("/feedbacks"  + `/${_id}`);
  }
  updateFeedback(feedback: Feedback){
    return this.apiService.put("/feedbacks"+ `/${feedback.feedbackId}`, feedback);
  }
  createFeedback(body:any){
    return this.apiService.post("/feedbacks",body).pipe(map(data => data));
  }
  deleteFeedback(feedbackId: number) {
    return this.apiService.delete("/feedbacks" + `/${feedbackId}`).pipe(map(data => data))
  }
}
