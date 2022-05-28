import { ComponentFixture, TestBed } from '@angular/core/testing';

import { BillTrackerComponent } from './bill-tracker.component';

describe('BillTrackerComponent', () => {
  let component: BillTrackerComponent;
  let fixture: ComponentFixture<BillTrackerComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ BillTrackerComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(BillTrackerComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
