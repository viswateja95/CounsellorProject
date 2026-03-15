
import { Injectable } from '@angular/core';
import { AsyncSubject, Observable } from 'rxjs';
import { Enquiry, EnquiryFilter } from '../model/enquiry.model';
import { ApiResponse } from '../model/api-response.model';
import { ApiService } from './api.service';

@Injectable({
  providedIn: 'root'
})
export class EnquiryService {

 constructor(private apiService: ApiService) { }

    addEnquiry(enquiry: Enquiry): Observable<ApiResponse<Enquiry>> {
        return this.apiService.post<Enquiry>('/enquiry/addEnquiry', enquiry);
    }

    getEnquiriesByCounsellor(counsellorId: number): Observable<ApiResponse<Enquiry[]>> {
        return this.apiService.get<Enquiry[]>(`/enquiry/counsellor/${counsellorId}`);
    }

    filterEnquiries(counsellorId: number, filter: EnquiryFilter): Observable<ApiResponse<Enquiry[]>> {
        return this.apiService.post<Enquiry[]>(`/enquiry/filter/${counsellorId}`, filter);
    }

    updateEnquiry(enqId: number, enquiry: Enquiry): Observable<ApiResponse<Enquiry>> {
        return this.apiService.put<Enquiry>(`/enquiry/updateEnquiry/${enqId}`, enquiry);
    }

    deleteEnquiry(enqId: number): Observable<ApiResponse<void>> {
        return this.apiService.delete<void>(`/enquiry/${enqId}`);
    }
}
