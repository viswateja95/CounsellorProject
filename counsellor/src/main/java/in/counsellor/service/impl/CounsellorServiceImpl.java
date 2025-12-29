package in.counsellor.service.impl;

import in.counsellor.dto.CounsellorDTO;
import in.counsellor.dto.DashBoardDTO;
import in.counsellor.dto.LoginDTO;
import in.counsellor.entitty.Counsellor;
import in.counsellor.repository.CounsellorRepo;
import in.counsellor.repository.EnquiryRepo;
import in.counsellor.service.CounsellorService;
import org.springframework.stereotype.Service;

@Service
public class CounsellorServiceImpl implements CounsellorService {

    private final CounsellorRepo counsellorRepo;
    private final EnquiryRepo enquiryRepo;

    public CounsellorServiceImpl(CounsellorRepo counsellorRepo, EnquiryRepo enquiryRepo){
        this.counsellorRepo = counsellorRepo;
        this.enquiryRepo = enquiryRepo;
    }

    @Override
    public CounsellorDTO register(CounsellorDTO dto) {
        if (counsellorRepo.findByEmail(dto.getEmail()).isPresent()) {
            throw new RuntimeException("Email Already Exists");
        }

        Counsellor counsellor = Counsellor.builder()
                .name(dto.getName())
                .email(dto.getEmail())
                .phno(dto.getPhno())
                .pwd(dto.getPwd())
                .build();

        Counsellor registered = counsellorRepo.save(counsellor);

        dto.setCounsellorId(registered.getCounsellorId());

        System.out.println("test register dto: " + dto);
        return dto;
    }

    @Override
    public LoginDTO login(LoginDTO logindto) {
        Counsellor counsellor = counsellorRepo
                .findByEmailAndPwd(logindto.getEmail(), logindto.getPwd())
                .orElseThrow(() -> new RuntimeException("Invalid credentials"));

        // Build a LoginDTO from the entity (don't read from dto itself)
        LoginDTO response = new LoginDTO();
        response.setEmail(counsellor.getEmail());
        // add any other fields LoginDTO contains (e.g. name, roles)
        return response;
    }

    @Override
    public DashBoardDTO getDashboard(Long counsellorId) {
        Long totalEnquiries = enquiryRepo.countByCounsellor_CounsellorId(counsellorId);
        Long enrolledEnquiries = enquiryRepo.countByCounsellor_CounsellorIdAndEnqStatus(counsellorId, "Enrolled");
        Long lostEnquiries = enquiryRepo.countByCounsellor_CounsellorIdAndEnqStatus(counsellorId, "Lost");
        Long openEnquiries = enquiryRepo.countByCounsellor_CounsellorIdAndEnqStatus(counsellorId, "Open");

        return DashBoardDTO.builder()
                .totalEnquiries(totalEnquiries)
                .enrolledEnquiries(enrolledEnquiries)
                .lostEnquiries(lostEnquiries)
                .openEnquiries(openEnquiries)
                .build();
    }
}
