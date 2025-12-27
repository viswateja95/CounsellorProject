package in.counsellor.repository;

import in.counsellor.entitty.Enquiry;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
public interface EnquiryRepo extends JpaRepository<Enquiry, Long> {
    List<Enquiry> findByCounsellor_CounsellorId(Long counsellorId);
    Long countByCounsellor_CounsellorId(Long counsellorId);
    Long countByCounsellor_CounsellorIdAndEnqStatus(Long counsellorId, String status);
}
