package id.vn.ptsport.PTSport.Controller;

import id.vn.ptsport.PTSport.DTO.Request.LoginRequest;
import id.vn.ptsport.PTSport.Service.LoginService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@RequiredArgsConstructor
public class LoginController {
    private final LoginService loginService;
    @PostMapping("/api/login")
    public ResponseEntity<?> login(@RequestBody @Valid LoginRequest request){
        return loginService.login(request);
    }
}
