package in.counsellor.service.impl;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import in.counsellor.dto.EnquiryDTO;
import in.counsellor.dto.EnquiryFilterDTO;
import in.counsellor.entitty.Counsellor;
import in.counsellor.entitty.Course;
import in.counsellor.entitty.Enquiry;
import in.counsellor.repository.CounsellorRepo;
import in.counsellor.repository.CourseRepo;
import in.counsellor.repository.EnquiryRepo;
import in.counsellor.service.EnquiryService;

@Service
public class EnquiryServiceImpl implements EnquiryService {

    private final CounsellorRepo counsellorRepo;
    private final EnquiryRepo enquiryRepo;
    private final CourseRepo courseRepo;

    public EnquiryServiceImpl(CounsellorRepo counsellorRepo, EnquiryRepo enquiryRepo, CourseRepo courseRepo) {
        this.counsellorRepo = counsellorRepo;
        this.enquiryRepo = enquiryRepo;
        this.courseRepo = courseRepo;
    }

    @Override
    @Transactional  
    public EnquiryDTO addEnquiry(EnquiryDTO enquiryDTO) {
        if (enquiryDTO.getCounsellorId() == null) {
            throw new RuntimeException("Counsellor ID is required");
        }

        if (enquiryDTO.getCourseId() == null) {
            throw new RuntimeException("Course ID is required");
        }

        Counsellor counsellor = counsellorRepo.findById(Math.toIntExact(enquiryDTO.getCounsellorId()))
                .orElseThrow(() -> new RuntimeException("Counsellor not found"));

        Course course = courseRepo.findById(enquiryDTO.getCourseId())
                .orElseThrow(() -> new RuntimeException("Course not found"));

        Enquiry enquiry = Enquiry.builder()
                .studentName(enquiryDTO.getStudentName())
                .studentPhno(enquiryDTO.getStudentPhno())
                .classMode(enquiryDTO.getClassMode())
                .enqStatus(enquiryDTO.getEnqStatus())
                .counsellor(counsellor)
                .course(course)
                .createdDate(LocalDateTime.now())
                .build();

        Enquiry saved = enquiryRepo.save(enquiry);
        enquiryDTO.setEnqId(saved.getEnqId());
        enquiryDTO.setCourseName(course.getCourseName());
        return enquiryDTO;
    }

    @Override
    @Transactional(readOnly = true)  
    public List<EnquiryDTO> getEnquiriesByCounsellor(Long counsellorId) {
        List<Enquiry> enquiries = enquiryRepo.findByCounsellor_CounsellorId(counsellorId);
        return enquiries.stream()
                .map(this::convertToDto)
                .collect(Collectors.toList());
    }

    @Override
    @Transactional  
    public EnquiryDTO updateEnquiry(Long enqId, EnquiryDTO dto) {
        Enquiry enquiry = enquiryRepo.findById(enqId)
                .orElseThrow(() -> new RuntimeException("Enquiry is not found"));

        if(dto.getCourseId() != null){
            Course course = courseRepo.findById(dto.getCourseId())
                    .orElseThrow(() -> new RuntimeException("course not found"));
            enquiry.setCourse(course);
        }

        enquiry.setStudentName(dto.getStudentName());
        enquiry.setStudentPhno(dto.getStudentPhno());
        enquiry.setClassMode(dto.getClassMode());
        enquiry.setEnqStatus(dto.getEnqStatus());
        enquiry.setUpdatedDate(LocalDateTime.now());

        Enquiry updated = enquiryRepo.save(enquiry);
        return convertToDto(updated);
    }

    @Override
    @Transactional  
    public void deleteEnquiry(Long enqId) {
        enquiryRepo.deleteById(enqId);
    }

    @Override
    @Transactional(readOnly = true)  
    public List<EnquiryDTO> filterEnquiries(Long counsellorId, EnquiryFilterDTO filterDto) {
        List<Enquiry> enquiries = enquiryRepo.filterEnquiries(
                counsellorId,
                filterDto.getCourseId(),
                filterDto.getClassMode(),
                filterDto.getEnqStatus(),
                filterDto.getStudentName()
        );
        return enquiries.stream()
                .map(this::convertToDto)
                .collect(Collectors.toList());
    }

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
