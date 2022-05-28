import { TestBed } from '@angular/core/testing';

import { BillTrackerService } from './bill-tracker.service';

describe('BillTrackerService', () => {
  let service: BillTrackerService;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(BillTrackerService);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });
});
