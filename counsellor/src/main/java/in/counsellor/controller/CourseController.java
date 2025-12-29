package in.counsellor.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import in.counsellor.dto.ApiResponse;
import in.counsellor.dto.CourseDTO;
import in.counsellor.service.CourseService;
import lombok.extern.slf4j.Slf4j;

@RestController
@RequestMapping("/api/course")
@Slf4j
public class CourseController {

    private final CourseService courseService;

    public CourseController(CourseService courseService){
        this.courseService = courseService;
    }

    @PostMapping("/addCourse")
    public ResponseEntity<ApiResponse<CourseDTO>> addCourse(@RequestBody CourseDTO courseDTO) {
        log.info("Adding new course: {}", courseDTO.getCourseName());
        CourseDTO saved = courseService.addCourse(courseDTO);
        ApiResponse<CourseDTO> response = ApiResponse.success(saved, "Course added successfully");
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @GetMapping("/courses")
    public ResponseEntity<ApiResponse<List<CourseDTO>>> getAllCourses(){
        log.info("Fetching all courses");
        List<CourseDTO> coursesList = courseService.getCourses();
        ApiResponse<List<CourseDTO>> response = ApiResponse.success(coursesList, "Courses retrieved successfully");
        return ResponseEntity.ok(response);
    }

    @GetMapping("/{courseId}")
    public ResponseEntity<ApiResponse<CourseDTO>> getCourseById(@PathVariable Long courseId){
        log.info("Fetching course: {}", courseId);
        CourseDTO course = courseService.getCourseById(courseId);
        ApiResponse<CourseDTO> response = ApiResponse.success(course, "Course retrieved successfully");
        return ResponseEntity.ok(response);
    }
}
