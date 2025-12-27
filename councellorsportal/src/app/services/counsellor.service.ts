import { Counsellor } from './../model/counsellor.model';
import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class CounsellorService {

  constructor(private http: HttpClient) { }
  private baseUrl = "http://localhost:8080/api/counsellor";

  register(counsellor: Counsellor):Observable<any> {
    console.log("In service"+counsellor);
    console.log(`${this.baseUrl}/register`, counsellor);
    return this.http.post(`${this.baseUrl}/register`, counsellor);
  }
  onLogin(email: string, pwd: string): Observable<any> {
    console.log("In service"+email+pwd);
    return this.http.post(`${this.baseUrl}/login`, { email, pwd });
  }
  setCurrentCounsellor(counsellor: Counsellor): void {
    localStorage.setItem('currentCounsellor', JSON.stringify(counsellor));
  }
  getCurrentCounsellor(): Counsellor | null {
    const counsellorData = localStorage.getItem('currentCounsellor');
    if (!counsellorData) {
      return null;
    }
    try {
      return JSON.parse(counsellorData) as Counsellor;
    } catch (e) {
      console.error('Failed to parse currentCounsellor from localStorage', e);
      return null;
    }
  }
}
