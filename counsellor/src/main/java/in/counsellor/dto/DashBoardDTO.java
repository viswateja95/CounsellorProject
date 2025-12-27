package in.counsellor.dto;

import lombok.Data;

@Data
public class DashBoardDTO {
    private Long totalEnquiries;
    private Long enrolledEnquiries;
    private Long lostEnquiries;
    private Long openEnquiries;
}
