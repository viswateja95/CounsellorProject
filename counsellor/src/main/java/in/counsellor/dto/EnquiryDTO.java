package in.counsellor.dto;

import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
public class EnquiryDTO {
    private Long enqId;
    private String studentName;
    private String studentPhno;
    private String classMode;
    private String enqStatus;
    private Long counsellorId;
    private Long courseId;
    private String courseName;
}
