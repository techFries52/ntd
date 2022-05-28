import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http'

@Injectable({
  providedIn: 'root'
})
export class BillTrackerService {

  constructor(private http: HttpClient) { }
  bills:any = [];
  uri = "http://localhost:8080/bills"

  getAllBillsQuery() {
    this.http.get(this.uri).subscribe(
      response => {
        this.bills = response;
      }
    )
  }

  getAllBills() {
    return this.bills;
  }
}
