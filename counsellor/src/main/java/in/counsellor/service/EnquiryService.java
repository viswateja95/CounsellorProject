package in.counsellor.service;

import in.counsellor.dto.EnquiryDTO;
import java.util.*;

public interface EnquiryService {
    public EnquiryDTO addEnquiry(EnquiryDTO enquiryDTO);

    public List<EnquiryDTO> getEnquiriesByCounsellor(Long counsellorId);
}
