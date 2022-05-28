import { Component, OnInit } from '@angular/core';
import { BillTrackerService } from 'src/app/services/bill-tracker.service';

@Component({
  selector: 'app-bill-tracker',
  templateUrl: './bill-tracker.component.html',
  styleUrls: ['./bill-tracker.component.css']
})
export class BillTrackerComponent implements OnInit {

  constructor(private billService: BillTrackerService) { }

  bills:any = [];

  ngOnInit(): void {
    this.bills = this.billService.getAllBills();
  }

}
