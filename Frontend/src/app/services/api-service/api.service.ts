import { Injectable } from '@angular/core';
import { HttpHeaders, HttpClient, HttpParams } from '@angular/common/http';
import { throwError, Observable } from 'rxjs';
import { environment } from 'src/environments/environment';
import { catchError } from 'rxjs/operators';

@Injectable({
  providedIn: 'root'
})
export class ApiService {

  headers = new HttpHeaders().append('Accept', 'application/json')
    .append('Content-Type', 'application/json');

  constructor(private http: HttpClient) { }

  private formatErrors(error: any) {
    return throwError(error.error);
  }

  get(path: string): Observable<any> {
    return this.http.get(`${environment.api_url}${path}`)
      .pipe(catchError(this.formatErrors));
  }

  post(path: string, body: {}): Observable<any> {
    return this.http.post(
      `${environment.api_url}${path}`,body
      , { headers: this.headers }).pipe(catchError(this.formatErrors));
  }

  put(path: string, body: {}): Observable<any> {
    return this.http.put(
      `${environment.api_url}${path}`, body
      , { headers: this.headers }).pipe(catchError(this.formatErrors)
    );
  }

  delete(path): Observable<any> {
    return this.http.delete(
      `${environment.api_url}${path}`
    ).pipe(catchError(this.formatErrors));
  }
}
