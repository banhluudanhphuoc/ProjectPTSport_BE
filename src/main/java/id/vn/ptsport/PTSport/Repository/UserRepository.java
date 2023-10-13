package id.vn.ptsport.PTSport.Repository;

import id.vn.ptsport.PTSport.Entity.User;
import org.springframework.data.repository.CrudRepository;

public interface UserRepository extends CrudRepository<User, String> {
    User findByUsername(String username);
    User findByEmail(String email);
}

