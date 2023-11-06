package id.vn.ptsport.PTSport.AdminRegistration;

//import id.vn.ptsport.PTSport.DTO.Request.AdminLoginRequest;
//import id.vn.ptsport.PTSport.Entity.Admin;
//import id.vn.ptsport.PTSport.Repository.AdminRepository;
//import id.vn.ptsport.PTSport.Security.CustomAdminDetails;
//import id.vn.ptsport.PTSport.Security.JWT.JwtTokenProvider;
//import id.vn.ptsport.PTSport.DTO.Response.LoginResponse;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.security.authentication.AuthenticationManager;
//import org.springframework.security.authentication.BadCredentialsException;
//import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
//import org.springframework.security.core.Authentication;
//import org.springframework.security.core.context.SecurityContextHolder;
//import org.springframework.stereotype.Service;
//
//@Service
//public class AdminService {
//    @Autowired
//    private AuthenticationManager authenticationManager;
//
//    @Autowired
//    private JwtTokenProvider tokenProvider;
//
//    @Autowired
//    private AdminRepository adminRepository;
//
//    public LoginResponse authenticateAdmin(AdminLoginRequest adminLoginRequest) {
//        try {
//            Authentication authentication = authenticationManager.authenticate(
//                    new UsernamePasswordAuthenticationToken(
//                            adminLoginRequest.getUsernameAdmin(),
//                            adminLoginRequest.getPassword()
//                    )
//            );
//
//            SecurityContextHolder.getContext().setAuthentication(authentication);
//            CustomAdminDetails adminDetails = (CustomAdminDetails) authentication.getPrincipal();
//
//            String jwt = tokenProvider.generateAdminToken(adminDetails.getAdmin());
//            return new LoginResponse(jwt, "Đăng nhập thành công", 200);
//        } catch (BadCredentialsException ex) {
//            // Xử lý trường hợp sai mật khẩu
//            return new LoginResponse(null, "Sai tài khoản hoặc mật khẩu", 401);
//        } catch (Exception e) {
//            // Xử lý các trường hợp khác
//            return new LoginResponse(null, "Lỗi không xác định", 500);
//        }
//    }
//}


public interface AdminService {
    AdminRegistrationResponse registerAdmin(AdminRegistrationRequest registrationRequest);

}
