package in.counsellor.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class DashBoardDTO {
    private Long totalEnquiries;
    private Long enrolledEnquiries;
    private Long lostEnquiries;
    private Long openEnquiries;
}
