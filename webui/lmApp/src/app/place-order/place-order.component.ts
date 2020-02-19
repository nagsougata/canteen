import { Component, OnInit } from '@angular/core';
import { Observable } from 'rxjs';
import { Customer } from '../customer';
import { CustomerService } from '../customer.service';
import { Menu } from '../menu';
import { MenuService } from '../menu.service';
import { Orders } from '../orders';
import { Vendor } from '../vendor';
import { Vendorservice } from '../vendorservice.service';
import { Wallet } from '../wallet';
import { WalletService } from '../wallet.service';

@Component({
  selector: 'app-place-order',
  templateUrl: './place-order.component.html',
  styleUrls: ['./place-order.component.css']
})
export class PlaceOrderComponent implements OnInit {
  customer : Customer;
  menuList : Observable<Menu[]>;
  vendorList : Observable<Vendor[]>;
  walletList : Observable<Wallet[]>;
  result : string;
  menuFound : Menu;
  price : number;
  constructor(private _menuService : MenuService, private _vendorService : Vendorservice, private _walletService : WalletService, private _customerService : CustomerService) {
    // alert("Place Order " +localStorage.getItem("custId"));
    this.customer = localStorage.getItem('customer')? JSON.parse(localStorage.getItem('customer')):[];
    this.model.customerId=this.customer.customerId;
    // alert("Customer Id " + this.model.customerId);
    // this.model.customerId=parseInt(localStorage.getItem("custId"));
    this.menuList = _menuService.showMenu();
    this.vendorList = _vendorService.showVendor();
    this.walletList=_walletService.validateWallet(this.customer.customerId);
    this.menu.menuItem="Biriyani";
   }
   showAmount() {
    //  alert("Hi");
    // alert(this.menu.menName);
    let menId : number;
    menId=this.model.menuId;
    //alert(menId);
    this._menuService.searchMenu(menId).subscribe(x => {
      this.menuFound = x;
      //alert(this.menuFound.menuCost);
      
      this.menu=x;
      this.price=this.menuFound.menuCost;
    });
   }
   placeOrder() {
    
    // alert("cUSTOMER ID   " +this.model.customerId);
    // alert(this.model.menuId);
    // // alert(this.menu.menuItem);
    // alert(this.model.vendorId);
    // // alert(this.model.menuPrice);
    // alert(this.model.orderQuantity);
    // alert(this.model.walletType);
    // alert(this.model.orderComments);
    // alert(this.model.orderDate);

    // this.model.menuId=parseInt(this.menuFound.menuItem);
    // this.model.vendorId=parseInt(this.vendor.vendorName);
   // this.model.walletType = this.wallet.walletType;
    this._customerService.placeOrder(this.model).subscribe(x => {
      this.result = x;
    })
   }
  model = new Orders();
  menu = new Menu();
  vendor = new Vendor();
  wallet = new Wallet();
  ngOnInit() {
  }

}
