import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';
import {FormsModule} from '@angular/forms';
import {HttpModule} from '@angular/http';
import {RouterModule, Routes} from '@angular/router';
import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { CustomerLoginComponent } from './customerlogin/customerlogin.component';
import { DashBoardComponent } from './dashboard/dashboard.component';
import { CustomerOrderHistoryComponent } from './customer-order-history/customer-order-history.component';
import { CustomerInfoComponent } from './customer-info/customer-info.component';
import { WalletInfoComponent } from './wallet-info/wallet-info.component';
import { VendordashboardComponent } from './vendordashboard/vendordashboard.component';
import { VendorloginComponent } from './vendorlogin/vendorlogin.component';
import { CustomerpendingComponent } from './customerpending/customerpending.component';
import { VendorhistoryComponent } from './vendorhistory/vendorhistory.component';
import { VendorpendingComponent } from './vendorpending/vendorpending.component';
import { VendorinfoComponent } from './vendorinfo/vendorinfo.component';
import { MenuComponent } from './menu/menu.component';
import { PlaceOrderComponent } from './place-order/place-order.component';
import { CustomerCancelOrderComponent } from './customer-cancel-order/customer-cancel-order.component';
import { VendoracceptorrejectComponent } from './vendoracceptorreject/vendoracceptorreject.component';
import { IndexComponent } from './index/index.component';
import { LogoutComponent } from './logout/logout.component';
import { CustomFilterPipe } from './custom-filter.pipe';
import { VendorFilterPipe } from './vendor-filter.pipe';



const appRoutes : Routes = [
  {path : 'dashBoard', component : DashBoardComponent, 
  children :
[
  {path:'placeOrder', component:PlaceOrderComponent, outlet:'data'},
    {path:'customerOrderHistory', component:CustomerOrderHistoryComponent, outlet:'data'},
    {path:'wallet', component:WalletInfoComponent, outlet:'data'},
    {path:'customerCancelOrder', component:CustomerCancelOrderComponent, outlet:'data'},
    {path:'customerPending', component:CustomerpendingComponent, outlet:'data'},
  ]
 },
 {path : 'vendordashboard', component : VendordashboardComponent,
 children :
 [
   {path:'vendor', component:VendorinfoComponent, outlet:'data'},
   {path:'vendorpendingorder', component:VendorpendingComponent, outlet:'data'},
   {path:'vendoracceptorreject', component:VendoracceptorrejectComponent, outlet:'data'},
   {path:'vendororderhistory', component:VendorhistoryComponent, outlet:'data'},
 ]
},
{path : 'index', component : IndexComponent, 
children :
[
  {path:'vendorlogin', component: VendorloginComponent, outlet:'data'},
  {path:'customer-login', component: CustomerLoginComponent, outlet:'data'},
  {path:'Logout',component:LogoutComponent,outlet:'data'},
]
},
{path : '', component : IndexComponent },
]
@NgModule({
  declarations: [
    AppComponent,
    CustomerLoginComponent,
    DashBoardComponent,
    CustomerOrderHistoryComponent,
    CustomerInfoComponent,
    WalletInfoComponent,
    VendordashboardComponent,
    VendorloginComponent,
    CustomerpendingComponent,
    VendorhistoryComponent,
    VendorpendingComponent,
    VendorinfoComponent,
    MenuComponent,
    PlaceOrderComponent,
    CustomerCancelOrderComponent,
    VendoracceptorrejectComponent,
    IndexComponent,
    LogoutComponent,
    CustomFilterPipe,
    VendorFilterPipe
  
    
  ],
  imports: [
    BrowserModule,
    FormsModule,
    HttpModule,
    AppRoutingModule,
    RouterModule.forRoot(appRoutes)
  ],
  providers: [],
  bootstrap: [AppComponent]
})
export class AppModule { }