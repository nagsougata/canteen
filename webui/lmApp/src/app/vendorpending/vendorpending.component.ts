import { Component, OnInit } from '@angular/core';
import {Router, ActivatedRoute} from '@angular/router';
import { Observable } from 'rxjs';
import 'rxjs/add/observable/of';
import { Vendor } from '../vendor';
import { Vendorservice } from '../vendorservice.service';
@Component({
  selector: 'app-vendorpending',
  templateUrl: './vendorpending.component.html',
  styleUrls: ['./vendorpending.component.css']
})
export class VendorpendingComponent implements OnInit {
  vendorName : string;
  venId : number;
  vendor : Vendor;
  orders : Observable<Vendor[]>;
  selValue : number;
  values : string;
  filter : string[];
  feature = 'Feature 1'
  arr : [];
  rs : string;
  status : string;
  selectedFeatures: any = [];
  Acceptorreject(orderId, vendId, status) {
    // alert(orderId);
    // alert(vendId);
    // alert(status);
    localStorage.setItem("AcceptorrejectId",orderId);
    localStorage.setItem("AcceptorrejectVenId", vendId);
    localStorage.setItem("AcceptorrejectStatus", status);
    this._router.navigate(['../vendoracceptorreject'], {relativeTo: this._route});
  }
  constructor(private vendorservice : Vendorservice, private _route : ActivatedRoute, private _router : Router) {
     
    this.vendor = localStorage.getItem('vendor')? JSON.parse(localStorage.getItem('vendor')):[];
    // alert("Customer Id is " +this.customer.cusId);
    this.orders=this.vendorservice.orderTestById(this.vendor.vendorId);

   }
   checkIfAllSelected(x) {
    this.selectedFeatures.push(x);
    // alert("added");
  }
   acceptMe() {
    this.values = this.selectedFeatures.toString();
    //alert(this.values);
    this.filter = this.values.split(",");
    this.status="ACCEPTED";
    for(var v=0;v<this.filter.length;v++) {
      alert(this.filter[v]);
      this.vendorservice.acceptOrRejectOrder(parseInt(this.filter[v]),this.vendor.vendorId,this.status).subscribe(x => {
        this.rs=x;
      })
    }
    alert("Selected Orders Accepted Successfully...");
    this._router.navigate(['/vendordashboard']);
     alert(this.vendor.vendorId);

  }
  ngOnInit() {
  }

}
