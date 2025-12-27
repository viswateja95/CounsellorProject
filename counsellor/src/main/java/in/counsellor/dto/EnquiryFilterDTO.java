package in.counsellor.dto;

import lombok.Data;

@Data
public class EnquiryFilterDTO {
    private Long courseId;
    private String classMode;
    private String enqStatus;
    private String studentName;
}
