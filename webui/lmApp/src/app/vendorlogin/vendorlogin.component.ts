import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { Observable } from 'rxjs';
import 'rxjs/add/observable/of';
import { Vendor } from '../vendor';
import { Vendorservice } from '../vendorservice.service';

@Component({
  selector: 'app-vendorlogin',
  templateUrl: './vendorlogin.component.html',
  styleUrls: ['./vendorlogin.component.css']
})
export class VendorloginComponent implements OnInit {
  result : any = {res:''};
test : string;
vendorId : number;
userName : string;
passWord : string;
vendor : Observable<Vendor>;
count : number;
// custService : CustomerService;

validate() {
// alert(this.userName);
// alert(this.passWord);
this.vendorService.validateVendorId(this.userName,this.passWord).subscribe( x => {
if(x=="1") {
//alert("Hi");
localStorage.setItem("vendorUser",this.userName);
this.router.navigate(["/vendordashboard"]);
} else {
this.count++;
}
console.log("result " +x);
// this.result.res=x;
// this.test = x;
},
err => {
console.log(err);
})
}
constructor(private vendorService : Vendorservice, private router : Router) {
this.count=0;
}
  ngOnInit()  {
    this.validate();
  }

}