package id.vn.ptsport.PTSport.Controller;

import id.vn.ptsport.PTSport.DTO.Request.EmailVerificationRequest;
import id.vn.ptsport.PTSport.DTO.Response.EmailVerificationResponse;
import id.vn.ptsport.PTSport.Entity.User;
import id.vn.ptsport.PTSport.Repository.EmailVerificationTokenRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

//import org.springframework.web.bind.annotation.CrossOrigin;
//@CrossOrigin(origins = "http://localhost:3000")
@RestController
@RequestMapping("/api/verify-email")
public class EmailVerificationController {
    @Autowired
    private EmailVerificationTokenRepository emailVerificationTokenRepository;

    @PostMapping
    public ResponseEntity<EmailVerificationResponse> verifyEmail(@RequestBody EmailVerificationRequest verificationRequest) {
        String token = verificationRequest.getToken();

        // Kiểm tra xem token có hợp lệ không (kiểm tra trong cơ sở dữ liệu hoặc hệ thống xác thực)
        // Nếu token hợp lệ, đặt trạng thái isActive thành true cho người dùng
        // Lưu ý: Dưới đây chỉ là một ví dụ đơn giản, bạn cần cài đặt cách xác thực token phù hợp với ứng dụng của bạn.
        EmailVerificationResponse response = new EmailVerificationResponse();
        User user = emailVerificationTokenRepository.findByRefreshToken(token);
        if (user != null && token.equals(user.getRefreshToken())) {
            user.setIsActive(true);
            emailVerificationTokenRepository.save(user);
            response.setMessage("Xác thực email thành công!");
            response.setStatus(200);
            return ResponseEntity.ok(response); // Thay đổi ở đây
        } else {
            response.setMessage("Token không hợp lệ !!!");
            response.setStatus(409);
            return ResponseEntity.ok(response); // Thay đổi ở đây
        }
    }
}