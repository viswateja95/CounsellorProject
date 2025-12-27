import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { AsyncSubject, Observable } from 'rxjs';
import { Enquiry } from '../model/enquiry.model';

@Injectable({
  providedIn: 'root'
})
export class EnquiryServiceService {

  private baseUrl = 'http://localhost:8080/api/enquiry';

  constructor(private http: HttpClient) { }

  addEnquiry(enquiry: Enquiry): Observable<Enquiry> {
    return this.http.post<Enquiry>(`${this.baseUrl}/addEnquiry`, enquiry);
  }

  getEnquiriesByCounsellor(counsellorId: number): Observable<Enquiry[]> {
    return this.http.get<Enquiry[]>(`${this.baseUrl}/counsellor/${counsellorId}`);
  }

  // Run the AsyncSubject demo inside a method to avoid top-level statements in the class body.
  // public runAsyncSubjectDemo(): void {
  //   // Scenario: Simulating a data fetch operation that completes after some time.
  //   const dataFetcher = new AsyncSubject<string>();

  //   // Subscriber A subscribes early
  //   console.log('Subscriber A: Subscribing to dataFetcher...');
  //   dataFetcher.subscribe({
  //     next: (value) => console.log(`Subscriber A: Received -> ${value}`),
  //     complete: () => console.log('Subscriber A: Completed!'),
  //     error: (err) => console.error(`Subscriber A: Error -> ${err}`)
  //   });

  //   // Data is being emitted, but not yet completed
  //   dataFetcher.next('Initial Data');
  //   dataFetcher.next('Intermediate Data');
  //   console.log('Data emitted but not completed.');

  //   // Subscriber B subscribes later
  //   console.log('Subscriber B: Subscribing to dataFetcher...');
  //   dataFetcher.subscribe({
  //     next: (value) => console.log(`Subscriber B: Received -> ${value}`),
  //     complete: () => console.log('Subscriber B: Completed!'),
  //     error: (err) => console.error(`Subscriber B: Error -> ${err}`)
  //   });

  //   dataFetcher.next('Final Data'); // This is the last value emitted before completion

  //   console.log('Calling complete() on dataFetcher...');
  //   dataFetcher.complete(); // Only upon completion, the last value ('Final Data') is emitted to all subscribers

  //   // Subscriber C subscribes even later, after completion
  //   console.log('Subscriber C: Subscribing to dataFetcher after completion...');
  //   dataFetcher.subscribe({
  //     next: (value) => console.log(`Subscriber C: Received -> ${value}`),
  //     complete: () => console.log('Subscriber C: Completed!'),
  //     error: (err) => console.error(`Subscriber C: Error -> ${err}`)
  //   });

  //   /*
  //   Expected Output:

  //   Subscriber A: Subscribing to dataFetcher...
  //   Data emitted but not completed.
  //   Subscriber B: Subscribing to dataFetcher...
  //   Final Data emitted before completion.
  //   Calling complete() on dataFetcher...
  //   Subscriber A: Received -> Final Data
  //   Subscriber A: Completed!
  //   Subscriber B: Received -> Final Data
  //   Subscriber B: Completed!
  //   Subscriber C: Subscribing to dataFetcher after completion...
  //   Subscriber C: Received -> Final Data
  //   Subscriber C: Completed!
  //   */
  // }
}
