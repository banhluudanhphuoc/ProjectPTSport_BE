package id.vn.ptsport.PTSport.Service.Impl;

import id.vn.ptsport.PTSport.DTO.Request.UserRegistrationRequest;
import id.vn.ptsport.PTSport.DTO.Response.UserRegistrationResponse;
import id.vn.ptsport.PTSport.Entity.User;
import id.vn.ptsport.PTSport.Repository.EmailVerificationTokenRepository;
import id.vn.ptsport.PTSport.Repository.UserRepository;
import id.vn.ptsport.PTSport.Service.EmailService;
import id.vn.ptsport.PTSport.Service.UserService;

import org.springframework.stereotype.Service;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.UUID;

@Service
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final EmailService emailService;
    private final EmailVerificationTokenRepository tokenRepository;

    public UserServiceImpl(UserRepository userRepository, PasswordEncoder passwordEncoder, EmailService emailService, EmailVerificationTokenRepository tokenRepository) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
        this.emailService = emailService;
        this.tokenRepository = tokenRepository;
    }

    @Override
    public UserRegistrationResponse registerUser(UserRegistrationRequest registrationRequest) {
        // Kiểm tra xem username đã tồn tại chưa
        if (userRepository.findByUsername(registrationRequest.getUsername()) != null) {
            UserRegistrationResponse response = new UserRegistrationResponse();
            response.setMessage("Tên người dùng đã tồn tại.");
            return response;
        }

        // Kiểm tra xem email đã tồn tại chưa
        if (userRepository.findByEmail(registrationRequest.getEmail()) != null) {
            UserRegistrationResponse response = new UserRegistrationResponse();
            response.setMessage("Email đã được sử dụng.");
            return response;
        }
        // Thực hiện đăng ký người dùng ở đây
        User user = new User();
        user.setUsername(registrationRequest.getUsername());
        user.setPassword(passwordEncoder.encode(registrationRequest.getPassword()));
        user.setEmail(registrationRequest.getEmail());
        user.setName(registrationRequest.getName());
        user.setPhoneNumber(registrationRequest.getPhoneNumber());
        user.setDateOfBirth(registrationRequest.getDateOfBirth());
        user.setIsActive(1);

        // Lưu User và refresh token
        userRepository.save(user);

        // Tạo mã xác thực email
        String token = UUID.randomUUID().toString();

        // Gửi email xác thực
        String verificationLink = "http://yourwebsite.com/verify-email?token=" + token;
        emailService.sendEmail(user.getEmail(), "Xác thực email", "Nhấn vào liên kết sau để xác thực email: " + verificationLink);

        // Lưu refreshToken
        user.setRefreshToken(token);
        userRepository.save(user);

        UserRegistrationResponse response = new UserRegistrationResponse();
        response.setMessage("Đăng ký thành công. Vui lòng kiểm tra email để xác thực.");
        return response;
    }

}
