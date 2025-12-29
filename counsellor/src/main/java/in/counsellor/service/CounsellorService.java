package in.counsellor.service;

import in.counsellor.dto.CounsellorDTO;
import in.counsellor.dto.DashBoardDTO;
import in.counsellor.dto.LoginDTO;

public interface CounsellorService  {

    public CounsellorDTO register(CounsellorDTO dto);
    public  LoginDTO login(LoginDTO loginDto);
    public DashBoardDTO getDashboard(Long counsellorId);
}
