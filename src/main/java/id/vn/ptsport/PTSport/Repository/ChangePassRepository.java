package id.vn.ptsport.PTSport.Repository;

import id.vn.ptsport.PTSport.Entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ChangePassRepository extends JpaRepository<User, Long> {
    Optional<User> findByUsername(String username);

}
