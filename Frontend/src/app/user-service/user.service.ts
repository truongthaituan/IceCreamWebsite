import { Injectable } from '@angular/core';
import { User } from './user.model';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { ApiService } from '../services/api-service/api.service';
import { map } from 'rxjs/operators';
import { UserChangePassword } from './userChangePassword.model';

@Injectable({
  providedIn: 'root'
})
export class UserService {
  user: User
  users: User[]
  userChangePassword: UserChangePassword
  constructor(private apiService: ApiService) { }
  getUserById(_id: number){
    return this.apiService.get("/users"  + `/${_id}`);
  }
  getUserByUserName(userName: string){
    return this.apiService.get("/users/username"  + `/${userName}`);
  }
  listAllUsers(){
    return this.apiService.get('/users').pipe(map(data => data));
  }
  updateUser(user: User){
    return this.apiService.put("/users"+ `/${user.userId}`, user);
  }
  register(body:any){
    return this.apiService.post("/users",body).pipe(map(data => data));
  }
  deleteUser(userId: string) {
    return this.apiService.delete("/users" + `/${userId}`).pipe(map(data => data))
  }
  changePasswordUser(userChangePassword: UserChangePassword){
    return this.apiService.put("/users/changePassword",userChangePassword).pipe(map(data => data))
  }
}
