package in.counsellor.controller;

import in.counsellor.dto.CounsellorDTO;
import in.counsellor.dto.LoginDTO;
import in.counsellor.service.CounsellorService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/counsellor")
public class CounsellorController {

    private final CounsellorService counsellorService;

    public CounsellorController(CounsellorService counsellorService){
        this.counsellorService = counsellorService;
    }

    @PostMapping("/register")
    public ResponseEntity<CounsellorDTO> register(@RequestBody CounsellorDTO dto){
        try{
            System.out.println("registering the user");
            CounsellorDTO registered = counsellorService.register(dto);
            return ResponseEntity.ok(registered);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @PostMapping("/login")
    public ResponseEntity<LoginDTO> login(@RequestBody LoginDTO logindto){
        try {
            System.out.println("counsellor login");
            LoginDTO login = counsellorService.login(logindto);
            return ResponseEntity.ok(login);
        }catch (Exception e){
            throw new RuntimeException(e);
        }
    }
}
