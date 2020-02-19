import { Injectable } from '@angular/core';
import { Http, Response } from '@angular/http';
import { Observable } from 'rxjs';
import 'rxjs/add/operator/map';
import { Vendor } from './vendor';

@Injectable({
  providedIn: 'root'
})
export class Vendorservice {
  constructor(private http : Http) { }

  validateVendorId(user : string, pwd : string) : Observable<string> {
    return this.
    http.get("http://localhost:8080/MLP174/api/vendor/validateVendor/"+user+"/"+pwd).
    map((res : Response) => res.text());
  }
  findByVendorName(user : string) : Observable<Vendor> {
    return this.
    http.get("http://localhost:8080/MLP174/api/vendor/vendorName/"+user).
    map((res : Response) => res.json());
  }
  orderTest2ById(venId : number) : Observable<Vendor[]> {
    return this.
    http.get("http://localhost:8080/MLP174/api/orders/vendorhistory/"+venId).
    map((res : Response) => res.json());
    }
    orderTestById(vendorId : number) : Observable<Vendor[]> {
      return this.
      http.get("http://localhost:8080/MLP174/api/orders/pendingvendororders/"+vendorId).
      map((res : Response) => res.json());
    }
    showVendor() : Observable<Vendor[]>{
      return this.
      http.get("http://localhost:8080/MLP174/api/vendor/").
      map((res : Response)=> res.json());
    }
    acceptOrRejectOrder(orderId : number, vendorId : number, status : String) : Observable<string> {
      return this.http.post("http://localhost:8080/MLP174/api/orders/acceptOrRejectOrder/"+orderId + "/" + vendorId + "/" +status,null).
      map((res : Response) => res.text());
      }
}
