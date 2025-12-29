package in.counsellor.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import in.counsellor.dto.ApiResponse;
import in.counsellor.dto.EnquiryDTO;
import in.counsellor.dto.EnquiryFilterDTO;
import in.counsellor.service.EnquiryService;
import lombok.extern.slf4j.Slf4j;

@RestController
@RequestMapping("/api/enquiry")
@Slf4j
public class EnquiryController {

    private final EnquiryService enquiryService;

    public EnquiryController(EnquiryService enquiryService){
        this.enquiryService = enquiryService;
    }

    @PostMapping("/addEnquiry")
    public ResponseEntity<ApiResponse<EnquiryDTO>> addEnquiry(@RequestBody EnquiryDTO enquiryDTO) {
        log.info("Adding new enquiry for student: {}", enquiryDTO.getStudentName());
        EnquiryDTO saved = enquiryService.addEnquiry(enquiryDTO);
        ApiResponse<EnquiryDTO> response = ApiResponse.success(saved, "Enquiry added successfully");
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @GetMapping("/counsellor/{counsellorId}")
    public ResponseEntity<ApiResponse<List<EnquiryDTO>>> getEnquiries(@PathVariable Long counsellorId) {
        log.info("Fetching enquiries for counsellor: {}", counsellorId);
        List<EnquiryDTO> enquiries = enquiryService.getEnquiriesByCounsellor(counsellorId);
        ApiResponse<List<EnquiryDTO>> response = ApiResponse.success(enquiries, "Enquiries retrieved successfully");
        return ResponseEntity.ok(response);
    }

    @PostMapping("/filterEnquiry/{counsellorId}")
    public ResponseEntity<ApiResponse<List<EnquiryDTO>>> filterEnquiries(
            @PathVariable Long counsellorId,
            @RequestBody EnquiryFilterDTO filterDto) {
        log.info("Filtering enquiries for counsellor: {} with filter: {}", counsellorId, filterDto);
        List<EnquiryDTO> enquiries = enquiryService.filterEnquiries(counsellorId, filterDto);
        ApiResponse<List<EnquiryDTO>> response = ApiResponse.success(enquiries, "Enquiries filtered successfully");
        return ResponseEntity.ok(response);
    }

    @PutMapping("/updateEnquiry/{enqId}")
    public ResponseEntity<ApiResponse<EnquiryDTO>> updateEnquiry(@PathVariable Long enqId, @RequestBody EnquiryDTO dto) {
        log.info("Updating enquiry: {}", enqId);
        EnquiryDTO updated = enquiryService.updateEnquiry(enqId, dto);
        ApiResponse<EnquiryDTO> response = ApiResponse.success(updated, "Enquiry updated successfully");
        return ResponseEntity.ok(response);
    }

    @DeleteMapping("/deleteEnquiry/{enqId}")
    public ResponseEntity<ApiResponse<Void>> deleteEnquiry(@PathVariable Long enqId) {
        log.info("Deleting enquiry: {}", enqId);
        enquiryService.deleteEnquiry(enqId);
        ApiResponse<Void> response = ApiResponse.success(null, "Enquiry deleted successfully");
        return ResponseEntity.ok(response);
    }
}
