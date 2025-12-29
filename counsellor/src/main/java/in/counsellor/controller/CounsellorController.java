package in.counsellor.controller;

import in.counsellor.dto.ApiResponse;
import in.counsellor.dto.CounsellorDTO;
import in.counsellor.dto.DashBoardDTO;
import in.counsellor.dto.LoginDTO;
import in.counsellor.service.CounsellorService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/counsellor")
@Slf4j
public class CounsellorController {

    private final CounsellorService counsellorService;

    public CounsellorController(CounsellorService counsellorService){
        this.counsellorService = counsellorService;
    }

    @PostMapping("/register")
    public ResponseEntity<ApiResponse<CounsellorDTO>> register(@RequestBody CounsellorDTO dto){
        try{
            log.info("Registering new counsellor with email: {}", dto.getEmail());
            CounsellorDTO registered = counsellorService.register(dto);
            ApiResponse<CounsellorDTO> response = ApiResponse.success(
                    registered, "Counsellor registered successfully");
            return ResponseEntity.status(HttpStatus.CREATED).body(response);
        } catch (Exception e) {
            log.error("Error registering counsellor", e);
            throw new RuntimeException(e);
        }
    }

    @PostMapping("/login")
    public ResponseEntity<ApiResponse<LoginDTO>> login(@RequestBody LoginDTO logindto){
        try {
            log.info("Counsellor login attempt with email: {}", logindto.getEmail());
            LoginDTO login = counsellorService.login(logindto);
            ApiResponse<LoginDTO> response = ApiResponse.success(
                    login, "Login successful");
            return ResponseEntity.ok(response);
        }catch (Exception e){
            log.error("Error during login", e);
            throw new RuntimeException(e);
        }
    }

    @GetMapping("/dashboard/{counsellorId}")
    public ResponseEntity<ApiResponse<DashBoardDTO>> getDashBoard(@PathVariable Long counsellorId){
        log.info("Fetching dashboard for counsellor: {}", counsellorId);
        DashBoardDTO dashBoard = counsellorService.getDashboard(counsellorId);
        ApiResponse<DashBoardDTO> response = ApiResponse.success(
                dashBoard, "Dashboard retrieved successfully");
        return ResponseEntity.ok(response);
    }

}
