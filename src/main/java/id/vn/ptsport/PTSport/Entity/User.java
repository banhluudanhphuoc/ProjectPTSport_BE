package id.vn.ptsport.PTSport.Entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.List;

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
    @Column(name = "is_active" , length = 2)
    private int isActive;

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(
            name = "user_role",
            joinColumns = @JoinColumn(name = "user_username"), // Sử dụng user_username thay vì user_id
            inverseJoinColumns = @JoinColumn(name = "role_id")
    )
    private List<Role> roles;

    @OneToMany(mappedBy = "user")
    private List<Cart> carts;

    @OneToMany(mappedBy = "user")
    private List<DeliveryAddress> deliveryAddresses;

    @OneToMany(mappedBy = "user")
    private List<Order> orders;

    @OneToMany(mappedBy = "user")
    private List<SocialLogin> socialLogins;

    // Getter và setter
}

