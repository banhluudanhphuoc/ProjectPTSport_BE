package id.vn.ptsport.PTSport.Service.Impl;

import id.vn.ptsport.PTSport.Entity.User;
import id.vn.ptsport.PTSport.Repository.UserRepository;
import id.vn.ptsport.PTSport.Service.ChangePassService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ChangePassServiceImpl implements ChangePassService {

    @Autowired
    private UserRepository userRepository;

    @Override
    public User findByUsername(String username) {
        return userRepository.findByUsername(username);
    }

    @Override
    public void updateUser(User user) {
        userRepository.save(user);
    }
}

