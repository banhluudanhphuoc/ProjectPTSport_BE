package id.vn.ptsport.PTSport.Service;

import id.vn.ptsport.PTSport.Entity.User;

public interface ChangePassService {
    User findByUsername(String username);

    void updateUser(User user);
}
