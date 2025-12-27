package in.counsellor.repository;

import in.counsellor.entitty.Enquiry;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
public interface EnquiryRepo extends JpaRepository<Enquiry, Long> {
    List<Enquiry> findByCounsellor_CounsellorId(Long counsellorId);

    @Query("SELECT e FROM Enquiry e WHERE e.counsellor.counsellorId = :counsellorId " +
            "AND (:courseId IS NULL OR e.course.courseId = :courseId) " +
            "AND (:classMode IS NULL OR e.classMode = :classMode) " +
            "AND (:enqStatus IS NULL OR e.enqStatus = :enqStatus) " +
            "AND (:studentName IS NULL OR LOWER(e.studentName) LIKE LOWER(CONCAT('%', :studentName, '%')))")
    List<Enquiry> filterEnquiries(
        @Param("counsellorId") Long counsellorId,
        @Param("courseId") Long courseId,
        @Param("classMode") String classMode,
        @Param("enqStatus") String enqStatus,
        @Param("studentName") String studentName
    );
    Long countByCounsellor_CounsellorId(Long counsellorId);
    Long countByCounsellor_CounsellorIdAndEnqStatus(Long counsellorId, String status);
}
