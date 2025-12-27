package in.counsellor.dto;

import lombok.Builder;
import lombok.Data;

@Data
public class LoginDTO {
    private String email;
    private String pwd;
}
