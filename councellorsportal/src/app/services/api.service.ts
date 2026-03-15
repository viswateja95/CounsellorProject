import { HttpHeaders, HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable, catchError, throwError } from 'rxjs';
import { ApiResponse } from '../model/api-response.model';

@Injectable({
  providedIn: 'root'
})
export class ApiService {

 private readonly baseUrl = 'http://localhost:8080/api';

    private httpOptions = {
        headers: new HttpHeaders({
            'Content-Type': 'application/json'
        })
    };

    constructor(private http: HttpClient) { }

    get<T>(endpoint: string): Observable<ApiResponse<T>> {
        return this.http.get<ApiResponse<T>>(
            `${this.baseUrl}${endpoint}`,
            this.httpOptions
        ).pipe(
            catchError(error => this.handleError(error))
        );
    }

    post<T>(endpoint: string, body: any): Observable<ApiResponse<T>> {
        return this.http.post<ApiResponse<T>>(
            `${this.baseUrl}${endpoint}`,
            body,
            this.httpOptions
        ).pipe(
            catchError(error => this.handleError(error))
        );
    }

    put<T>(endpoint: string, body: any): Observable<ApiResponse<T>> {
        return this.http.put<ApiResponse<T>>(
            `${this.baseUrl}${endpoint}`,
            body,
            this.httpOptions
        ).pipe(
            catchError(error => this.handleError(error))
        );
    }

    delete<T>(endpoint: string): Observable<ApiResponse<T>> {
        return this.http.delete<ApiResponse<T>>(
            `${this.baseUrl}${endpoint}`,
            this.httpOptions
        ).pipe(
            catchError(error => this.handleError(error))
        );
    }

    private handleError(error: any) {
        let errorMessage = 'An error occurred';

        if (error.error instanceof ErrorEvent) {
            errorMessage = `Error: ${error.error.message}`;
        } else if (error.status) {
            errorMessage = `Error Code: ${error.status}\nMessage: ${error.error?.message || error.message}`;
        }

        console.error('API Error:', errorMessage);
        return throwError(() => new Error(errorMessage));
    }
}
