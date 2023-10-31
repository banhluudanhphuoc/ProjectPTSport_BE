package id.vn.ptsport.PTSport.Controller;

import id.vn.ptsport.PTSport.DTO.Request.ChangePasswordRequest;
import id.vn.ptsport.PTSport.DTO.Response.ChangePasswordResponse;
import id.vn.ptsport.PTSport.Entity.User;
import id.vn.ptsport.PTSport.Security.JWT.JwtTokenProvider;
import id.vn.ptsport.PTSport.Service.ChangePassService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

@RequestMapping("/api")
@RestController
public class ChangePasswordController {
    @Autowired
    private ChangePassService changePassService;

    @Autowired
    private JwtTokenProvider tokenProvider;


    @Autowired
    private PasswordEncoder passwordEncoder;

    @PostMapping("/change-password")
    public ResponseEntity<ChangePasswordResponse> changePassword(@RequestHeader("Authorization") String token,
                                                                 @RequestBody ChangePasswordRequest changePasswordRequest) {
        String jwt = token.substring(7); // Loại bỏ "Bearer " từ chuỗi token
        String username = tokenProvider.extractUsername(jwt);

        User user = changePassService.findByUsername(username);

        if (user == null) {
            return new ResponseEntity<>(new ChangePasswordResponse(401,"Người dùng không tồn tại"), HttpStatus.BAD_REQUEST);
        }

        if (tokenProvider.validateToken(jwt)) {
            String oldPassword = changePasswordRequest.getOldPassword();
            String newPassword = changePasswordRequest.getNewPassword();

            // Kiểm tra mật khẩu cũ
            if (passwordEncoder.matches(oldPassword, user.getPassword())) {
                user.setPassword(passwordEncoder.encode(newPassword));
                changePassService.updateUser(user);
                return new ResponseEntity<>(new ChangePasswordResponse(200, "Mật khẩu đã được thay đổi thành công"), HttpStatus.OK);
            } else {
                return new ResponseEntity<>(new ChangePasswordResponse(401, "Sai mật khẩu cũ"), HttpStatus.UNAUTHORIZED);
            }
        } else {
            return new ResponseEntity<>(new ChangePasswordResponse(401, "Token không hợp lệ"), HttpStatus.UNAUTHORIZED);
        }
    }}