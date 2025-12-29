package in.counsellor.service;

import java.util.List;

import in.counsellor.dto.EnquiryDTO;
import in.counsellor.dto.EnquiryFilterDTO;

public interface EnquiryService {
    public EnquiryDTO addEnquiry(EnquiryDTO enquiryDTO);

    public List<EnquiryDTO> getEnquiriesByCounsellor(Long counsellorId);

    public EnquiryDTO updateEnquiry(Long enqId, EnquiryDTO dto);

    public void deleteEnquiry(Long enqId);
    
    public List<EnquiryDTO> filterEnquiries(Long counsellorId, EnquiryFilterDTO filterDto);
}
