import { Injectable } from '@angular/core';
import { Http, Response } from '@angular/http';
import { Observable } from 'rxjs';
import { Menu } from './menu';
import 'rxjs/add/operator/map';
@Injectable({
  providedIn: 'root'
})
export class MenuService {

  constructor(private http : Http) { }
  showMenu() : Observable<Menu[]> {
  return this.http.get("http://localhost:8080/MLP174/api/menu").
  map((res : Response) => res.json());
  }
  searchMenu(menuId : number) : Observable<Menu>{
    return this.
    http.get("http://localhost:8080/MLP174/api/menu/"+menuId).
    map((res : Response)=> res.json());
}
}
