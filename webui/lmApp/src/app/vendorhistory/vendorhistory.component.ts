import { Component, OnInit } from '@angular/core';
import { Vendorservice } from '../vendorservice.service';
import { Observable} from 'rxjs';
import 'rxjs/add/observable/of';
//import {Router} from '@angular/router';
import { Vendor } from '../vendor';
@Component({
  selector: 'app-vendorhistory',
  templateUrl: './vendorhistory.component.html',
  styleUrls: ['./vendorhistory.component.css']
})
export class VendorhistoryComponent implements OnInit {
  vendorName : string;
  venId : number;
  vendor : Vendor;
  orders : Observable<Vendor[]>;
  constructor(private vendorService : Vendorservice) {
  this.vendor = localStorage.getItem('vendor')? JSON.parse(localStorage.getItem('vendor')):[];
  // alert("Customer Id is " +this.customer.cusId);
  this.orders=this.vendorService.orderTest2ById(this.vendor.vendorId);
  
  }
  ngOnInit() {
  }

}
