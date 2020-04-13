import { Injectable } from '@angular/core';
import { Observable, BehaviorSubject } from 'rxjs';
import { AuthInfo } from './auth-info.model';
import { JwtHelperService } from '@auth0/angular-jwt';
import { distinctUntilChanged, map } from 'rxjs/operators';
import { ApiService } from '../api-service/api.service';
import { Router } from '@angular/router';

@Injectable({
  providedIn: 'root'
})
export class AuthService {
  response: Response
  private loggedIn = new BehaviorSubject<boolean>(this.isAuthenticated());
  private authInfoSubject = new BehaviorSubject<AuthInfo>({
    loggedIn: this.isAuthenticated(),
    roles: this.getRoles(),
    userName: this.getUsername(),
    avatar: this.getAvatar()
  });
  public authInfo = this.authInfoSubject.pipe(distinctUntilChanged());
  public isLoggedIn = this.loggedIn.asObservable().pipe(distinctUntilChanged());
  helper = new JwtHelperService();
  constructor(private apiService: ApiService, private router: Router) {
  }

  login(credentials): Observable<any> {
    return this.apiService.post('/authenticate', credentials)
      .pipe(map(
        data => {
          if(data.loggedIn == false){
            return data;
          }else{
            this.finishAuthentication(data);
            return data;
          }    
        }
      ));
  }

  finishAuthentication(data): void {
    const token = this.helper.decodeToken(data.token);
    const expiresAt = JSON.stringify((token.exp * 1000));
    // save data to local storage
    localStorage.setItem('user-name', token.sub);
    localStorage.setItem('access-token', data.token);
    localStorage.setItem('expires-at', expiresAt);
    localStorage.setItem('user-role', token.role);
    this.loggedIn.next(true);
    // set current data to observable
    this.authInfoSubject.next({
      loggedIn: true,
      roles: this.getRoles(),
      userName: this.getUsername(),
      avatar: this.getAvatar()
    });
}
// logout(): Observable<any> {
//   // localStorage.removeItem('user-name');
//   // localStorage.removeItem('access-token');
//   // localStorage.removeItem('expires-at');
//   // localStorage.removeItem('user-role');
//   // localStorage.removeItem('userName');
//   localStorage.clear();
//   sessionStorage.removeItem("response");
//   this.authInfoSubject.next({
//     loggedIn: false,
//     roles: this.getRoles(),
//     userName: this.getUsername(),
//     avatar: this.getAvatar()
//   });

//   return this.isLoggedIn;
// }
isAuthenticated(): boolean {
  const expiresAt = JSON.parse(localStorage.getItem('expires-at'));
  return Date.now() < expiresAt;
  } 
  
  getToken(): string {
    return localStorage.getItem('access-token');
  }

  getRoles(): string[] {
    const roles = localStorage.getItem('user-role');
    let rs = [];
    if (roles) {
      rs = roles.split(',');
    }
    return rs;
  }
  getUsername(): string {
    return localStorage.getItem('user-name');
  }

  getAvatar(): string {
    return localStorage.getItem('avatar');
  }

  isAdmin() {
    const roles = localStorage.getItem('user-role');
    if (roles) {
      return roles.includes('ROLE_ADMIN');
    }
    return false;
  }

  isCustomer() {
    const roles = localStorage.getItem('user-role');
    if (roles) {
      return roles.includes('ROLE_CUSTOMER');
    }
    return false;
  }

  isUser() {
    const roles = localStorage.getItem('user-role');
    if (roles) {
      return roles.includes('ROLE_USER');
    }
    return false;
  }
}