import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { CustomerpendingComponent } from './customerpending.component';

describe('CustomerpendingComponent', () => {
  let component: CustomerpendingComponent;
  let fixture: ComponentFixture<CustomerpendingComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ CustomerpendingComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(CustomerpendingComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });
     
  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
