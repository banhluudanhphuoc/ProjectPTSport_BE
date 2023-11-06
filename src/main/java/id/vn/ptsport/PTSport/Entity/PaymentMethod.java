package id.vn.ptsport.PTSport.Entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.LinkedHashSet;
import java.util.Set;

@Getter
@Setter
@Entity
@Table(name = "payment_method")
public class PaymentMethod {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "payment_method_id", nullable = false)
    private Integer id;

    @Column(name = "method_name", length = 100)
    private String methodName;

    @OneToMany(mappedBy = "paymentMethod")
    private Set<Order> orders = new LinkedHashSet<>();

}