package id.vn.ptsport.PTSport.Entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.List;
@Getter
@Setter
@Entity
public class Orders {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "username")
    private User user;

    private BigDecimal totalAmount;
    private String orderStatus;

    @OneToMany(mappedBy = "order")
    private List<OrderDetail> orderDetails;

    // Getter và setter
}

