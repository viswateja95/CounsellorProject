package in.counsellor.service;

import in.counsellor.dto.CounsellorDTO;
import in.counsellor.dto.LoginDTO;
import in.counsellor.entitty.Counsellor;
import in.counsellor.repository.CounsellorRepo;

public interface CounsellorService  {

    public CounsellorDTO register(CounsellorDTO dto);
    public  LoginDTO login(LoginDTO loginDto);
}
