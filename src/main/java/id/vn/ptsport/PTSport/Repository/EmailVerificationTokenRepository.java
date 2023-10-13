package id.vn.ptsport.PTSport.Repository;


import id.vn.ptsport.PTSport.Entity.User;
import org.springframework.data.repository.CrudRepository;

public interface EmailVerificationTokenRepository extends CrudRepository<User, String> {
    User findByRefreshToken(String refreshToken);
}
