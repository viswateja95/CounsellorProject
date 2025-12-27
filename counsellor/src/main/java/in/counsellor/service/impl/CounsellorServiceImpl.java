package in.counsellor.service.impl;

import in.counsellor.dto.CounsellorDTO;
import in.counsellor.dto.LoginDTO;
import in.counsellor.entitty.Counsellor;
import in.counsellor.repository.CounsellorRepo;
import in.counsellor.service.CounsellorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class CounsellorServiceImpl implements CounsellorService {

    private final CounsellorRepo counsellorRepo;

    public CounsellorServiceImpl(CounsellorRepo counsellorRepo){
        this.counsellorRepo = counsellorRepo;
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


//    @Override
//    public boolean register(Counsellor counsellor) {
//        Counsellor savedCounsellor = counsellorRepo.save(counsellor);
//        return savedCounsellor.getCounsellorId() != null;
//    }
}
