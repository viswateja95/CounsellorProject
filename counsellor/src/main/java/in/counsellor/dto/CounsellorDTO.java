package in.counsellor.dto;

import lombok.Data;

@Data
public class CounsellorDTO {
    private Long counsellorId;
    private String name;
    private String email;
    private String pwd;
    private String phno;
}
