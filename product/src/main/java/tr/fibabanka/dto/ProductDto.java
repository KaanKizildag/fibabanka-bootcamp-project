package tr.fibabanka.dto;

import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
public class ProductDto {
    private Long id;

    private String productName;

    private BigDecimal salesPrice;

    private Long categoryId;
}
