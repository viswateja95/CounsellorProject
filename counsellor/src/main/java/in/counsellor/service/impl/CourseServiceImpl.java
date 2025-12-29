package in.counsellor.service.impl;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import in.counsellor.dto.CourseDTO;
import in.counsellor.entitty.Course;
import in.counsellor.repository.CourseRepo;
import in.counsellor.service.CourseService;

@Service
public class CourseServiceImpl implements CourseService {

    private final CourseRepo courseRepo;

    public CourseServiceImpl(CourseRepo courseRepo){
        this.courseRepo =  courseRepo;
    }

    @Override
    @Transactional(readOnly = true)
    public List<CourseDTO> getCourses() {
        List<Course> courses = courseRepo.findAll();
        return courses.stream()
                .map(this::convertToDto)
                .collect(Collectors.toList());
    }

    @Override
    @Transactional
    public CourseDTO addCourse(CourseDTO courseDTO) {

        Course course = Course.builder()
                .courseName(courseDTO.getCourseName())
                .build();
        Course saved = courseRepo.save(course);
        courseDTO.setCourseId(saved.getCourseId());
        return courseDTO;
    }

    @Override
    @Transactional(readOnly = true)
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
