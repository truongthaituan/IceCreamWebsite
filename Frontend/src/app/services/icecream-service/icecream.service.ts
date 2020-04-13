import { Injectable } from '@angular/core';
import { Icecream } from './icecream.model';
import { ApiService } from '../api-service/api.service';

@Injectable({
  providedIn: 'root'
})
export class IcecreamService {
  Icecream: Icecream;
  Icecreams: Icecream[];
  constructor(private apiService: ApiService) { }
  getIceCreamList() {
    return this.apiService.get("/icecreams");
  }
}
