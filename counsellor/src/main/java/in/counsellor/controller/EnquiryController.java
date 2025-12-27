package in.counsellor.controller;

import in.counsellor.dto.EnquiryDTO;
import in.counsellor.service.EnquiryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController()
@RequestMapping("api/enquiry")
@CrossOrigin(origins = "http://localhost:4200")
public class EnquiryController {

    @Autowired
    private EnquiryService enquiryService;

    @PostMapping("/addEnquiry")
    public ResponseEntity<EnquiryDTO> addEnquiry(@RequestBody EnquiryDTO enquiryDTO) {
        EnquiryDTO saved = enquiryService.addEnquiry(enquiryDTO);
        return ResponseEntity.ok(saved);
    }

    @GetMapping("/counsellor/{counsellorId}")
    public ResponseEntity<List<EnquiryDTO>> getEnquiries(@PathVariable Long counsellorId) {
        List<EnquiryDTO> enquiries = enquiryService.getEnquiriesByCounsellor(counsellorId);
        return ResponseEntity.ok(enquiries);
    }
}
