package id.vn.ptsport.PTSport.Repository;


import id.vn.ptsport.PTSport.Entity.Authority;
import id.vn.ptsport.PTSport.Entity.AuthorityId;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

public interface AuthorityRepository extends JpaRepository<Authority, AuthorityId> {

    @Modifying
    @Query("update Authority u set u.role.id =?1 where u.username.username=?2")
    void changeRole(Integer roleId, String username);
}