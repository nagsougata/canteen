import { Injectable } from '@angular/core';
import { Http, Response } from '@angular/http';
import { Observable } from 'rxjs';
import 'rxjs/add/operator/map';
import { Wallet } from './wallet';
@Injectable({
  providedIn: 'root'
})
export class WalletService {
  
  constructor(private http : Http) { }

  listWallet() : Observable<Wallet[]> {
    return this.
    http.get("http://localhost:8080/MLP174/api/wallet").
    map((res : Response) => res.json());
  }
  validateWallet(customerId : number) : Observable<Wallet[]>{
    return this.
    http.get("http://localhost:8080/MLP174/api/wallet/"+customerId).
    map((res : Response)=> res.json());
}
}
