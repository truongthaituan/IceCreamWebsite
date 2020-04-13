import { Injectable } from '@angular/core';
import { Role } from './role.model';
import { ApiService } from '../api-service/api.service';
import { map } from 'rxjs/operators';
import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class RoleService {
  role: Role;
  roles: Role[];
  constructor(private apiService: ApiService) { }

  getRoleList() {
    return this.apiService.get("/roles");
  }
}
