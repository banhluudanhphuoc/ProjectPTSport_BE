package id.vn.ptsport.PTSport.Controller;


import id.vn.ptsport.PTSport.DTO.Request.LoginRequest;
import id.vn.ptsport.PTSport.DTO.Response.LoginResponse;
import id.vn.ptsport.PTSport.Entity.User;
import id.vn.ptsport.PTSport.Repository.UserRepository;
import id.vn.ptsport.PTSport.Security.JWT.JwtTokenProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@RequestMapping("/api")
public class AdminLoginController {
    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private JwtTokenProvider tokenProvider;

    @PostMapping("/admin/login")
    public LoginResponse adminLogin(@Valid @RequestBody LoginRequest loginRequest) {
        User user = userRepository.findByUsername(loginRequest.getUsername());
        if (user == null) {
            return new LoginResponse(null, "Không tìm thấy người dùng.", 401);
        }
        if (!passwordEncoder.matches(loginRequest.getPassword(), user.getPassword())) {
            return new LoginResponse(null, "Sai mật khẩu.", 401);
        }

        // Đăng nhập thành công, tạo JWT Token và trả về
        String jwt = tokenProvider.generateAdminToken(user);
        return new LoginResponse(jwt, "Đăng nhập thành công", 200);
    }
}
