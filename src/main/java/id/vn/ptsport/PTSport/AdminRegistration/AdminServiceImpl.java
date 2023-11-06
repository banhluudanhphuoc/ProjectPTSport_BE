package id.vn.ptsport.PTSport.AdminRegistration;


import id.vn.ptsport.PTSport.Entity.Authority;
import id.vn.ptsport.PTSport.Entity.AuthorityId;
import id.vn.ptsport.PTSport.Entity.Role;
import id.vn.ptsport.PTSport.Entity.User;
import id.vn.ptsport.PTSport.Repository.AuthorityRepository;
import id.vn.ptsport.PTSport.Repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

//@Service
//@Transactional(rollbackFor = {Exception.class})
//@RequiredArgsConstructor
//public class AdminServiceImpl implements AdminService {
//    private final PasswordEncoder passwordEncoder;
//    private final UserRepository userRepository;
//    private final AuthorityRepository authorityRepository;
//
//    @Override
//    public AdminRegistrationResponse registerAdmin (AdminRegistrationRequest adminRegistrationRequest){
//        User user = new User();
//        user.setUsername(adminRegistrationRequest.getUsername());
//        user.setPassword(passwordEncoder.encode(adminRegistrationRequest.getPassword()));
//
//        AuthorityId authorityId = new AuthorityId();
//        Role role = new Role();
//        role.setId(2);
//        authorityId.setUsername(user.getUsername());
//        authorityId.setRoleId(2);
//        Authority authority = new Authority();
//        authority.setId(authorityId);
//        authority.setRole(role);
//
//
//        userRepository.save(user);
//        authorityRepository.save(authority);
//
//        AdminRegistrationResponse response = new AdminRegistrationResponse();
//        response.setMessage("Đăng ký thành công.");
//        response.setStatus(200);
//        return response;
//
//    }
@Service
@Transactional(rollbackFor = {Exception.class})
@RequiredArgsConstructor
public class AdminServiceImpl implements AdminService {
    private final PasswordEncoder passwordEncoder;
    private final UserRepository userRepository;

    @Override
    public AdminRegistrationResponse registerAdmin(AdminRegistrationRequest adminRegistrationRequest) {
        // Kiểm tra xem tài khoản Admin đã tồn tại chưa.
        User existingUser = userRepository.findByUsername(adminRegistrationRequest.getUsername());

        if (existingUser != null) {
            // Nếu tài khoản đã tồn tại, bạn có thể thay đổi quyền của nó thành Admin.
            Role adminRole = new Role();
            adminRole.setId(2); // Đảm bảo rằng ID cho quyền Admin đã được định nghĩa.

            existingUser.setPassword(passwordEncoder.encode(adminRegistrationRequest.getPassword()));

            List<Role> roles = new ArrayList<>();
            roles.add(adminRole);

            existingUser.setRoles(roles); // Gán quyền cho tài khoản Admin.

            userRepository.save(existingUser); // Lưu tài khoản với quyền mới.

            AdminRegistrationResponse response = new AdminRegistrationResponse();
            response.setMessage("Cập nhật tài khoản thành công.");
            response.setStatus(200);
            return response;
        } else {
            // Nếu tài khoản không tồn tại, bạn có thể tạo một tài khoản mới.
            User user = new User();
            user.setUsername(adminRegistrationRequest.getUsername());
            user.setPassword(passwordEncoder.encode(adminRegistrationRequest.getPassword()));

            Role adminRole = new Role();
            adminRole.setId(2); // Đảm bảo rằng ID cho quyền Admin đã được định nghĩa.

            List<Role> roles = new ArrayList<>();
            roles.add(adminRole);

            user.setRoles(roles); // Gán quyền cho tài khoản Admin.

            userRepository.save(user); // Lưu tài khoản Admin mới.

            AdminRegistrationResponse response = new AdminRegistrationResponse();
            response.setMessage("Đăng ký thành công.");
            response.setStatus(200);
            return response;
        }
    }
}
