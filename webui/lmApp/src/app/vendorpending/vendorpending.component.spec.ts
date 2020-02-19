import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { VendorpendingComponent } from './vendorpending.component';

describe('VendorpendingComponent', () => {
  let component: VendorpendingComponent;
  let fixture: ComponentFixture<VendorpendingComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ VendorpendingComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(VendorpendingComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
