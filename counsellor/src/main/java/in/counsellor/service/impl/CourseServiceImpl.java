package in.counsellor.service.impl;

import in.counsellor.dto.CourseDTO;
import in.counsellor.entitty.Course;
import in.counsellor.repository.CourseRepo;
import in.counsellor.service.CourseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class CourseServiceImpl implements CourseService {
    @Autowired
    private CourseRepo courseRepo;

    @Override
    public List<CourseDTO> getCourses() {
        List<Course> courses = courseRepo.findAll();
        return courses.stream()
                .map(this::convertToDto)
                .collect(Collectors.toList());
    }

    @Override
    public CourseDTO addCourse(CourseDTO courseDTO) {

        Course course = Course.builder()
                .courseName(courseDTO.getCourseName())
                .build();
        Course saved = courseRepo.save(course);
        courseDTO.setCourseId(saved.getCourseId());
        return courseDTO;
    }

    @Override
    public CourseDTO getCourseById(Long courseId) {
        Course course = courseRepo.findById(courseId)
                .orElseThrow(() -> new RuntimeException("Course Not Found"));
        return convertToDto(course);
    }

    private CourseDTO convertToDto(Course course) {
        return CourseDTO.builder()
                .courseId(course.getCourseId())
                .courseName(course.getCourseName())
                .build();
    }
}
