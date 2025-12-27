package in.counsellor.service.impl;

import in.counsellor.dto.EnquiryDTO;
import in.counsellor.entitty.Counsellor;
import in.counsellor.entitty.Course;
import in.counsellor.entitty.Enquiry;
import in.counsellor.repository.CounsellorRepo;
import in.counsellor.repository.CourseRepo;
import in.counsellor.repository.EnquiryRepo;
import in.counsellor.service.EnquiryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class EnquiryServiceImpl implements EnquiryService {

    @Autowired
    private CounsellorRepo counsellorRepo;

    @Autowired
    private EnquiryRepo enquiryRepo;

    @Autowired
    private CourseRepo courseRepo;

    @Override
    public EnquiryDTO addEnquiry(EnquiryDTO enquiryDTO) {
        // ⭐ Validate input to prevent NullPointerException
        if (enquiryDTO.getCounsellorId() == null) {
            throw new RuntimeException("Counsellor ID is required");
        }
        if (enquiryDTO.getCourseId() == null) {
            throw new RuntimeException("Course ID is required");
        }

        // Get Counsellor
        Counsellor counsellor = counsellorRepo.findById(Math.toIntExact(enquiryDTO.getCounsellorId()))
                .orElseThrow(() -> new RuntimeException("Counsellor not found"));

        // Get Course - ⭐ Now using courseId from DTO
        Course course = courseRepo.findById(enquiryDTO.getCourseId())
                .orElseThrow(() -> new RuntimeException("Course not found"));

        // Create and save Enquiry
        Enquiry enquiry = Enquiry.builder()
                .studentName(enquiryDTO.getStudentName())
                .studentPhno(enquiryDTO.getStudentPhno())
                .classMode(enquiryDTO.getClassMode())
                .enqStatus(enquiryDTO.getEnqStatus())
                .counsellor(counsellor)
                .course(course)                          // ⭐ Set course object
                .createdDate(LocalDateTime.now())
                .build();

        Enquiry saved = enquiryRepo.save(enquiry);
        enquiryDTO.setEnqId(saved.getEnqId());
        enquiryDTO.setCourseName(course.getCourseName());
        return enquiryDTO;
    }

    @Override
    public List<EnquiryDTO> getEnquiriesByCounsellor(Long counsellorId) {
        List<Enquiry> enquiries = enquiryRepo.findByCounsellor_CounsellorId(counsellorId);
        return enquiries.stream()
                .map(this::convertToDto)
                .collect(Collectors.toList());
    }

//    @Override
//    public List<EnquiryDTO> filterEnquiries(Long counsellorId, EnquiryFilterDto filterDto) {
//        // ⭐ Now passes courseId to the filter query
//        List<Enquiry> enquiries = enquiryRepo.filterEnquiries(
//                counsellorId,
//                filterDto.getCourseId(),      // ⭐ courseId instead of courseName
//                filterDto.getClassMode(),
//                filterDto.getEnqStatus(),
//                filterDto.getStudentName()
//        );
//        return enquiries.stream()
//                .map(this::convertToDto)
//                .collect(Collectors.toList());
//    }
//
//    @Override
//    public EnquiryDTO updateEnquiry(Long enqId, EnquiryDTO dto) {
//        Enquiry enquiry = enquiryRepo.findById(enqId)
//                .orElseThrow(() -> new RuntimeException("Enquiry not found"));
//
//        if (dto.getCourseId() != null) {
//            Course course = courseRepo.findById(dto.getCourseId())
//                    .orElseThrow(() -> new RuntimeException("Course not found"));
//            enquiry.setCourse(course);
//        }
//
//        enquiry.setStudentName(dto.getStudentName());
//        enquiry.setStudentPhno(dto.getStudentPhno());
//        enquiry.setClassMode(dto.getClassMode());
//        enquiry.setEnqStatus(dto.getEnqStatus());
//        enquiry.setUpdatedDate(LocalDateTime.now());
//
//        Enquiry updated = enquiryRepo.save(enquiry);
//        return convertToDto(updated);
//    }
//
//    @Override
//    public void deleteEnquiry(Long enqId) {
//        enquiryRepo.deleteById(enqId);
//    }
//
    private EnquiryDTO convertToDto(Enquiry enquiry) {
        EnquiryDTO dto = new EnquiryDTO();
        dto.setEnqId(enquiry.getEnqId());
        dto.setStudentName(enquiry.getStudentName());
        dto.setStudentPhno(enquiry.getStudentPhno());
        dto.setClassMode(enquiry.getClassMode());
        dto.setEnqStatus(enquiry.getEnqStatus());
        dto.setCounsellorId(enquiry.getCounsellor().getCounsellorId());
        dto.setCourseId(enquiry.getCourse().getCourseId());
        dto.setCourseName(enquiry.getCourse().getCourseName());
        return dto;
    }
}
