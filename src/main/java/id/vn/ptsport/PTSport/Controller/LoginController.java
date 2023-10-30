//package id.vn.ptsport.PTSport.Controller;
//
////import id.vn.ptsport.PTSport.DTO.Request.LoginRequest;
////import id.vn.ptsport.PTSport.Service.LoginService;
////import lombok.RequiredArgsConstructor;
////import org.springframework.http.ResponseEntity;
////import org.springframework.web.bind.annotation.PostMapping;
////import org.springframework.web.bind.annotation.RequestBody;
////import org.springframework.web.bind.annotation.RestController;
////
////import javax.validation.Valid;
////
////@RestController
////@RequiredArgsConstructor
////public class LoginController {
////    private final LoginService loginService;
////    @PostMapping("/api/login")
////    public ResponseEntity<?> login(@RequestBody @Valid LoginRequest request){
////        return loginService.login(request);
////    }
////}
//
//import id.vn.ptsport.PTSport.DTO.Request.LoginRequest;
//import id.vn.ptsport.PTSport.DTO.Response.LoginResponse;
//import id.vn.ptsport.PTSport.DTO.Response.RandomStuff;
//import id.vn.ptsport.PTSport.Security.CustomUserDetails;
//import id.vn.ptsport.PTSport.Security.JWT.JwtTokenProvider;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.security.authentication.AuthenticationManager;
//import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
//import org.springframework.security.core.Authentication;
//import org.springframework.security.core.context.SecurityContextHolder;
//import org.springframework.web.bind.annotation.*;
//
//import javax.validation.Valid;
//
//@RestController
//@RequestMapping("/api")
//public class LoginController {
//
//    @Autowired
//    AuthenticationManager authenticationManager;
//
//    @Autowired
//    private JwtTokenProvider tokenProvider;
//
//    @PostMapping("/login")
//    public LoginResponse authenticateUser(@Valid @RequestBody LoginRequest loginRequest) {
//
//        // Xác thực từ username và password.
//        Authentication authentication = authenticationManager.authenticate(
//                new UsernamePasswordAuthenticationToken(
//                        loginRequest.getUsername(),
//                        loginRequest.getPassword()
//                )
//        );
//
//        // Nếu không xảy ra exception tức là thông tin hợp lệ
//        // Set thông tin authentication vào Security Context
//        SecurityContextHolder.getContext().setAuthentication(authentication);
//
//        // Trả về jwt cho người dùng.
//        String jwt = tokenProvider.generateToken((CustomUserDetails) authentication.getPrincipal());
//        return new LoginResponse(jwt);
//    }
//
//
//
//    // Api /api/random yêu cầu phải xác thực mới có thể request
//    @GetMapping("/random")
//    public RandomStuff randomStuff(){
//        return new RandomStuff("JWT Hợp lệ mới có thể thấy được message này");
//    }
//
//}

package id.vn.ptsport.PTSport.Controller;

//import id.vn.ptsport.PTSport.DTO.Request.LoginRequest;
//import id.vn.ptsport.PTSport.Service.LoginService;
//import lombok.RequiredArgsConstructor;
//import org.springframework.http.ResponseEntity;
//import org.springframework.web.bind.annotation.PostMapping;
//import org.springframework.web.bind.annotation.RequestBody;
//import org.springframework.web.bind.annotation.RestController;
//
//import javax.validation.Valid;
//
//@RestController
//@RequiredArgsConstructor
//public class LoginController {
//    private final LoginService loginService;
//    @PostMapping("/api/login")
//    public ResponseEntity<?> login(@RequestBody @Valid LoginRequest request){
//        return loginService.login(request);
//    }
//}
import id.vn.ptsport.PTSport.Repository.UserRepository;
import id.vn.ptsport.PTSport.DTO.Request.LoginRequest;
import id.vn.ptsport.PTSport.DTO.Response.LoginResponse;
import id.vn.ptsport.PTSport.DTO.Response.RandomStuff;
import id.vn.ptsport.PTSport.Security.CustomUserDetails;
import id.vn.ptsport.PTSport.Security.JWT.JwtTokenProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;
import id.vn.ptsport.PTSport.Entity.User;


import javax.validation.Valid;
//import org.springframework.web.bind.annotation.CrossOrigin;
//@CrossOrigin(origins = "http://localhost:3000")
@RestController
@RequestMapping("/api")
public class LoginController {
    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    AuthenticationManager authenticationManager;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private JwtTokenProvider tokenProvider;



    @PostMapping("/login")
    public LoginResponse authenticateUser(@Valid @RequestBody LoginRequest loginRequest) {
        User user = userRepository.findByUsername(loginRequest.getUsername());
        if (user == null) {
            // Nếu không tìm thấy người dùng trong cơ sở dữ liệu, trả về lỗi và status 401
            return new LoginResponse(null,"Không tìm thấy người dùng.", 401);

        }
        if (!passwordEncoder.matches(loginRequest.getPassword(), user.getPassword())) {
            return new LoginResponse(null, "Sai mật khẩu.", 401);
        }

        // Xác thực từ username và password.
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        loginRequest.getUsername(),
                        loginRequest.getPassword()

                )
        );

        // Nếu không xảy ra exception tức là thông tin hợp lệ
        // Set thông tin authentication vào Security Context
        SecurityContextHolder.getContext().setAuthentication(authentication);
        CustomUserDetails userDetails = (CustomUserDetails) authentication.getPrincipal();


        if (user.getIsActive()) {
            // Nếu tài khoản hoạt động (isActive == 1), trả về JWT token và status 200
            String jwt = tokenProvider.generateToken((CustomUserDetails) authentication.getPrincipal());
            return new LoginResponse(jwt,"Đăng nhập thành công",200);
        } else {
            // Nếu tài khoản không hoạt động (isActive == 0), trả về status 401
            return new LoginResponse(null,"Vui lòng xác thực tài khoản trước khi đăng nhập.", 401);
        }

    }



    // Api /api/random yêu cầu phải xác thực mới có thể request
    @GetMapping("/random")
    public RandomStuff randomStuff(){
        return new RandomStuff("JWT Hợp lệ mới có thể thấy được message này");
    }

}

