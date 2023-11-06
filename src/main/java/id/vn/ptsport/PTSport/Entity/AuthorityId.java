package id.vn.ptsport.PTSport.Entity;

import lombok.Getter;
import lombok.Setter;
import org.hibernate.Hibernate;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import java.io.Serializable;
import java.util.Objects;

@Getter
@Setter
@Embeddable
public class AuthorityId implements Serializable {
    private static final long serialVersionUID = -5621395243604252822L;
    @Column(name = "username", nullable = false, length = 100)
    private String username;

    @Column(name = "role_id", nullable = false)
    private Integer roleId;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
        AuthorityId entity = (AuthorityId) o;
        return Objects.equals(this.roleId, entity.roleId) &&
                Objects.equals(this.username, entity.username);
    }

    @Override
    public int hashCode() {
        return Objects.hash(roleId, username);
    }

}
