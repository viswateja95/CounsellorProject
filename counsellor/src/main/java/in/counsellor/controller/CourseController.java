package in.counsellor.controller;

import in.counsellor.dto.CourseDTO;
import in.counsellor.service.CourseService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/course")
public class CourseController {

    private final CourseService courseService;

    public CourseController(CourseService courseService){
        this.courseService = courseService;
    }

    @PostMapping("/addCourse")
    public ResponseEntity<CourseDTO> addCourse(@RequestBody CourseDTO courseDTO) {
        CourseDTO saved = courseService.addCourse(courseDTO);
        return ResponseEntity.ok(saved);
    }

    @GetMapping("/courses")
    public ResponseEntity<List<CourseDTO>> getAllCourses(){
        List<CourseDTO> coursesList = courseService.getCourses();
        return ResponseEntity.ok(coursesList);
    }

    @GetMapping("/{courseId}")
    public ResponseEntity<CourseDTO> getCourseById(@PathVariable Long courseId){
        CourseDTO course = courseService.getCourseById(courseId);
        return ResponseEntity.ok(course);
    }
}
