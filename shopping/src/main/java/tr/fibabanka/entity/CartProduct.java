package tr.fibabanka.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.Formula;

import javax.persistence.*;
import java.math.BigDecimal;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class CartProduct {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long cartProductId;

    private Long cartId;

    private Long productId;

    @Column(name = "sales_quantity")
    private int salesQuantity;

    @Column(name = "sales_price")
    private BigDecimal salesPrice;

    @Formula("sales_price * sales_quantity")
    private BigDecimal lineAmount;

}
