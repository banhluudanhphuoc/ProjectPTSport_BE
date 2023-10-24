package id.vn.ptsport.PTSport.Entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;

@Getter
@Setter
@Entity
public class User {
    @Id
    private String username;

    private String name;
    private String email;
    private String password;
    private String address;
    private String phoneNumber;
    private String dateOfBirth;
    @Column(name = "refresh_token")
    private String refreshToken;
    @Column(name = "is_active")
    private Boolean isActive;

//    @ManyToMany(fetch = FetchType.EAGER)
////    @JoinTable(
////            name = "user_role",
////            joinColumns = @JoinColumn(name = "user_username"), // Sử dụng user_username thay vì user_id
////            inverseJoinColumns = @JoinColumn(name = "role_id")
////    )
////    private List<Role> roles;

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(
            name = "authority",
            joinColumns = @JoinColumn(name = "username"),
            inverseJoinColumns = @JoinColumn(name = "role_id")
    )
    private List<Role> roles = new ArrayList<>();




    @OneToMany(mappedBy = "user")
    private List<Cart> carts;

    @OneToMany(mappedBy = "user")
    private List<DeliveryAddress> deliveryAddresses;

    @OneToMany(mappedBy = "user")
    private Set<Order> orders = new LinkedHashSet<>();

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "social_login_id")
    private SocialLogin socialLogin;

    @OneToMany(mappedBy = "username")
    private Set<Notification> notifications = new LinkedHashSet<>();


    // Getter và setter
}

