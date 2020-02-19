import { Component, OnInit } from '@angular/core';
import { Observable } from 'rxjs';
import { Wallet } from '../wallet';
import { WalletService } from '../wallet.service';
import { CustomerLoginComponent } from '../customerlogin/customerlogin.component';

@Component({
  selector: 'app-wallet-info',
  templateUrl: './wallet-info.component.html',
  styleUrls: ['./wallet-info.component.css']
})
export class WalletInfoComponent implements OnInit {
  wallet : Observable<Wallet[]>
  //wallets : Observable<Wallet[]>;
  customerId : number;
  customer : CustomerLoginComponent;
  sum : number;
  walletId : number;
  show() {
    this.wallet=this.walletService.validateWallet(this.customerId);
  }

  constructor(private walletService : WalletService) { 
    this.customer = localStorage.getItem('customer')? JSON.parse(localStorage.getItem('customer')):[];
    this.customerId = this.customer.customerId;
    this.wallet=walletService.listWallet();
    this.wallet=walletService.validateWallet(this.customerId); 
    // this.wallet1=walletService.validateWallet(this.customerId);
  }

  ngOnInit() {
  }

}
