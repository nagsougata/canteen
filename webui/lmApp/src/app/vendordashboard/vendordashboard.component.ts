import { Component, OnInit } from '@angular/core';

@Component({
  selector: 'app-vendordashboard',
  templateUrl: './vendordashboard.component.html',
  styleUrls: ['./vendordashboard.component.css']
})
export class VendordashboardComponent implements OnInit {

  vendorName : string;
  vendorId : string;
  constructor() { 
    this.vendorName = localStorage.getItem("vendorUser");
    this.vendorId=localStorage.getItem("venid");
  }



  ngOnInit() {
  }

}
