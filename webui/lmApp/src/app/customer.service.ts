import { Injectable } from '@angular/core';
import { Http, Response } from '@angular/http';
import { Observable } from 'rxjs';
import 'rxjs/add/operator/map';
import { Customer } from './customer';
import { Wallet } from './wallet';
import { Orders } from './orders';

@Injectable({
  providedIn: 'root'
})
export class CustomerService {
 
  constructor(private http : Http) { }

  validateCustomerId(user : string, pwd : string) : Observable<string> {
    return this.
    http.get("http://localhost:8080/MLP174/api/customer/validateCustomer/"+user+"/"+pwd).
    map((res : Response) => res.text());
  }
  listCustomerName(user : string) : Observable<Customer> {
    return this.
    http.get("http://localhost:8080/MLP174/api/customer/customerName/"+user).
    map((res : Response) => res.json());
  }
  orderTest1ById(customerId : number) : Observable<Customer[]> {
    return this.
    http.get("http://localhost:8080/MLP174/api/orders/customerhistory/"+customerId).
    map((res : Response) => res.json());
  }
  orderListById(customerId : number) : Observable<Customer[]> {
    return this.
    http.get("http://localhost:8080/MLP174/api/orders/pendingcustomer/"+customerId).
    map((res : Response) => res.json());
  }
  validateWallet(customerId : number) : Observable<Wallet[]>{
    return this.
    http.get("http://localhost:8080/MLP174/api/wallet/"+customerId).
    map((res : Response)=> res.json());
}
placeOrder(order : Orders) : Observable<string> {
  return this.
  http.post("http://localhost:8080/MLP174/api/orders/placeOrder/",order).
  map((res : Response)=> res.text());

}
cancelOrder(orderId : number, customerId : number, status : string) : Observable<string> {
  return this.http.post("http://localhost:8080/MLP174/api/orders/CancelOrder/"+ orderId+ "/" + customerId + "/" +status, null).
  map((res : Response) => res.text());
  }

}
