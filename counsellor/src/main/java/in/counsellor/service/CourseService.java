package in.counsellor.service;

import in.counsellor.dto.CourseDTO;

import java.util.List;

public interface CourseService {
    public List<CourseDTO> getCourses();
    public CourseDTO addCourse(CourseDTO courseDTO);
    public CourseDTO getCourseById(Long courseId);
}
