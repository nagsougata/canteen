import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { VendorhistoryComponent } from './vendorhistory.component';

describe('VendorhistoryComponent', () => {
  let component: VendorhistoryComponent;
  let fixture: ComponentFixture<VendorhistoryComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ VendorhistoryComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(VendorhistoryComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
