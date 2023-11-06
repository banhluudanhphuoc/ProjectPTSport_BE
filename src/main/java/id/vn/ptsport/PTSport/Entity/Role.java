package id.vn.ptsport.PTSport.Entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.LinkedHashSet;
import java.util.Set;

@Getter
@Setter
@Entity
public class Role {
//    @Id
//    @GeneratedValue(strategy = GenerationType.IDENTITY)
//    private Integer id;
//    private String name;
//
////    @ManyToMany(mappedBy = "roles")
////    private List<User> users;
//@ManyToMany(mappedBy = "roles")
//private List<User> users = new ArrayList<>();
@Id
@GeneratedValue(strategy = GenerationType.IDENTITY)
@Column(name = "role_id", nullable = false)
private Integer id;

    @Column(name = "role_name", length = 200)
    private String roleName;

    @ManyToMany(mappedBy = "roles")
    private Set<User> users = new LinkedHashSet<>();


    // Getter và setter
}

