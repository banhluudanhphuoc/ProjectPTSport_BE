package id.vn.ptsport.PTSport.Repository;

import id.vn.ptsport.PTSport.Entity.User;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

public interface UserRepository extends CrudRepository<User, String> {
    User findByUsername(String username);
    User findByEmail(String email);

    @Modifying(flushAutomatically = true)
    @Query(value = "Update user a set a.refresh_token =:newRefreshToken where a.username=:username",nativeQuery = true)
    void updateRefreshToken(String newRefreshToken, String username);
    @Modifying(flushAutomatically = true)
    @Query(value = "update user a set a.is_active = false where a.username=:username",nativeQuery = true)
    void delete(String username);
}

