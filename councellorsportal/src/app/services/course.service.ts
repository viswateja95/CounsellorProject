import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { ApiResponse } from '../model/api-response.model';
import { Course } from '../model/course.model';
import { ApiService } from './api.service';

@Injectable({
  providedIn: 'root'
})
export class CourseService {

  constructor(private apiService: ApiService) { }

    addCourse(course: Course): Observable<ApiResponse<Course>> {
        return this.apiService.post<Course>('/course', course);
    }

    getAllCourses(): Observable<ApiResponse<Course[]>> {
        return this.apiService.get<Course[]>('/course');
    }

    getCourseById(courseId: number): Observable<ApiResponse<Course>> {
        return this.apiService.get<Course>(`/course/${courseId}`);
    }

    updateCourse(courseId: number, course: Course): Observable<ApiResponse<Course>> {
        return this.apiService.put<Course>(`/course/${courseId}`, course);
    }

    deleteCourse(courseId: number): Observable<ApiResponse<void>> {
        return this.apiService.delete<void>(`/course/${courseId}`);
    }
}
