package id.vn.ptsport.PTSport.Service;

//import id.vn.ptsport.PTSport.DTO.Request.LoginRequest;
//import org.springframework.http.ResponseEntity;
//
//public interface LoginService {
//    ResponseEntity<?> login(LoginRequest request);
//}

import id.vn.ptsport.PTSport.Entity.User;
import id.vn.ptsport.PTSport.Repository.UserRepository;
import id.vn.ptsport.PTSport.Security.CustomUserDetails;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class LoginService implements UserDetailsService {
    @Autowired
    private UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String username) {
        // Kiểm tra xem user có tồn tại trong database không?
        User user = userRepository.findByUsername(username);
        if (user == null) {
            throw new UsernameNotFoundException(username);
        }
        return new CustomUserDetails(user);
    }
}
