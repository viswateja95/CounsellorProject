import { Counsellor, DashboardStats, LoginRequest } from './../model/counsellor.model';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { ApiResponse } from '../model/api-response.model';
import { ApiService } from './api.service';

@Injectable({
  providedIn: 'root'
})
export class CounsellorService {

  constructor(private apiService: ApiService) { }

    register(counsellor: Counsellor): Observable<ApiResponse<Counsellor>> {
        return this.apiService.post<Counsellor>('/counsellor/register', counsellor);
    }

    login(loginRequest: LoginRequest): Observable<ApiResponse<Counsellor>> {
        return this.apiService.post<Counsellor>('/counsellor/login', loginRequest);
    }

    getDashboard(counsellorId: number): Observable<ApiResponse<DashboardStats>> {
        return this.apiService.get<DashboardStats>(`/counsellor/dashboard/${counsellorId}`);
    }

    setCurrentCounsellor(counsellor: Counsellor): void {
        localStorage.setItem('currentCounsellor', JSON.stringify(counsellor));
    }

    getCurrentCounsellor(): Counsellor | null {
        const data = localStorage.getItem('currentCounsellor');
        return data ? JSON.parse(data) : null;
    }

    logout(): void {
        localStorage.removeItem('currentCounsellor');
    }

    isLoggedIn(): boolean {
        return this.getCurrentCounsellor() !== null;
    }

    getCurrentCounsellorId(): number | null {
        const counsellor = this.getCurrentCounsellor();
        return counsellor ? counsellor.counsellorId || null : null;
    }
}
