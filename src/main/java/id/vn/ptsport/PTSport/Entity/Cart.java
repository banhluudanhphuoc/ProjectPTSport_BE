package id.vn.ptsport.PTSport.Entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.List;
@Getter
@Setter
@Entity
public class Cart {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "user_username") // Sử dụng user_username thay vì user_id
    private User user;

    private BigDecimal totalValue;

    @OneToMany(mappedBy = "cart")
    private List<CartProduct> cartProducts;

    // Getter và setter
}
