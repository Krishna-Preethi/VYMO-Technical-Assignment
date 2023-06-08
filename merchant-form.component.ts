import { Component } from '@angular/core';
import { HttpClient } from '@angular/common/http';

interface Merchant {
  restaurantName: string;
  contactName: string;
  pincode: string;
  location: string;
  website: string;
  phoneNumber: string;
  averageDailyTransactions: number;
}

@Component({
  selector: 'app-merchant-form',
  templateUrl: './merchant-form.component.html',
  styleUrls: ['./merchant-form.component.css']
})
export class MerchantFormComponent {
  merchant: Merchant = {
    restaurantName: '',
    contactName: '',
    pincode: '',
    location: '',
    website: '',
    phoneNumber: '',
    averageDailyTransactions: 0
  };
  status: string = '';

  constructor(private http: HttpClient) {}

  submitForm(): void {
    this.http.post('/api/merchants', this.merchant)
      .subscribe(
        () => {
          this.status = 'success';
        },
        () => {
          this.status = 'failure';
        }
      );
  }
}
