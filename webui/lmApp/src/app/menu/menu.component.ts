import { Component, OnInit } from '@angular/core';
import { Observable } from 'rxjs';
import { Menu } from '../menu';
import { MenuService } from '../menu.service';
@Component({
  selector: 'app-menu',
  templateUrl: './menu.component.html',
  styleUrls: ['./menu.component.css']
})
export class MenuComponent implements OnInit {

  menus : Observable<Menu[]>;


  constructor(private menuService : MenuService) { 
    this.menus=menuService.showMenu();
  }

  ngOnInit() {
  }

}
