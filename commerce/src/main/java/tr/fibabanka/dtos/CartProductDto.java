package tr.fibabanka.dtos;

import lombok.*;

import java.math.BigDecimal;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class CartProductDto {

    private Long cartProductId;

    private Long cartId;

    private Long productId;

    private int salesQuantity;

    private BigDecimal salesPrice;

    @Setter(AccessLevel.NONE)
    private BigDecimal lineAmount;

    public BigDecimal getLineAmount() {
        return salesPrice.multiply(BigDecimal.valueOf(salesQuantity));
    }
}
