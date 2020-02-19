import { Component, OnInit } from '@angular/core';
import { Vendorservice } from '../vendorservice.service';

@Component({
  selector: 'app-vendoracceptorreject',
  templateUrl: './vendoracceptorreject.component.html',
  styleUrls: ['./vendoracceptorreject.component.css']
})
export class VendoracceptorrejectComponent implements OnInit {

  vendId : number;
  orderId : number;
  status : string;
  result : string;

  constructor(private _vendorService : Vendorservice) {
    this.orderId=parseInt(localStorage.getItem("AcceptorrejectId"));
    this.vendId=parseInt(localStorage.getItem("AcceptorrejectVenId"));
    this.status=(localStorage.getItem("AcceptorrejectStatus"));
   }
  acceptOrReject() {
    this._vendorService.acceptOrRejectOrder(this.orderId,this.vendId,this.status).subscribe(x => {
      this.result=x;
    })
  }

  ngOnInit() {
  }

}
