package id.vn.ptsport.PTSport.Service;

import id.vn.ptsport.PTSport.DTO.Request.LoginRequest;
import org.springframework.http.ResponseEntity;

public interface LoginService {
    ResponseEntity<?> login(LoginRequest request);
}
