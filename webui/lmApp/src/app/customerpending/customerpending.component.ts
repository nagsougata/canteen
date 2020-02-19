import { Component, OnInit } from '@angular/core';
import { Observable } from 'rxjs';
import 'rxjs/add/observable/of';
import { Customer } from '../customer';
import { CustomerService } from '../customer.service';
import { Router, ActivatedRoute } from '@angular/router';
@Component({
  selector: 'app-customerpending',
  templateUrl: './customerpending.component.html',
  styleUrls: ['./customerpending.component.css']
})
export class CustomerpendingComponent implements OnInit {
  
  orders : Observable<Customer[]>;
  customerId : number;
  customer : Customer;
  customerName : string;
  selValue : number;
  values : string;
  filter : string[];
  feature = 'Feature 1'
  arr : [];
  rs : string;
  status : string;
  selectedFeatures: any = [];
  constructor(private customerService : CustomerService, private _router : Router, private _route : ActivatedRoute) { 
  this.customer = localStorage.getItem('customer')? JSON.parse(localStorage.getItem('customer')):[];
  this.orders=this.customerService.orderListById(this.customer.customerId);
  }
  cancelOrder(ordId, custId) {
    // alert(ordId);
    // alert(custId);
    localStorage.setItem("cancelOrderId",ordId);
    localStorage.setItem("cancelCustId", custId);
    this._router.navigate(['../customerCancelOrder'], {relativeTo: this._route});
  }
  checkIfAllSelected(x) {
    this.selectedFeatures.push(x);
    // alert("added");
  }
  
  cancelMe() {
    this.values = this.selectedFeatures.toString();
    // alert(this.values);
    this.filter = this.values.split(",");
    this.status="YES";
    for(var v=0;v<this.filter.length;v++) {
      alert(this.filter[v]);
      this.customerService.cancelOrder(parseInt(this.filter[v]),this.customer.customerId,this.status).subscribe(x => {
        this.rs=x;
      })
    }
    alert("Selected Orders Cancelled Successfully...");
    this._router.navigate(['/dashBoard']);
    // alert(this.customer.customerId);

  }
  ngOnInit() {
  }

}
