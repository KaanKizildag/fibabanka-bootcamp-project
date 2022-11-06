package tr.fibabanka.component;

import org.springframework.stereotype.Component;
import tr.fibabanka.dto.CartProductDto;
import tr.fibabanka.entity.CartProduct;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class CartProductMapper {

    public CartProductDto fromDto(CartProduct cartProduct) {
        CartProductDto dto = new CartProductDto();

        dto.setCartId(cartProduct.getCartId());
        dto.setCartProductId(cartProduct.getCartProductId());
        dto.setProductId(cartProduct.getProductId());
        dto.setSalesPrice(cartProduct.getSalesPrice());
        dto.setSalesQuantity(cartProduct.getSalesQuantity());

        return dto;
    }


    public CartProduct fromDto(CartProductDto dto) {
        CartProduct cartProduct = new CartProduct();

        cartProduct.setCartId(dto.getCartId());
        cartProduct.setCartProductId(dto.getCartProductId());
        cartProduct.setProductId(dto.getProductId());
        cartProduct.setSalesPrice(dto.getSalesPrice());
        cartProduct.setSalesQuantity(dto.getSalesQuantity());

        return cartProduct;
    }

    public List<CartProductDto> fromEntityList(List<CartProduct> cartProducts) {
        return cartProducts.stream()
                .map(this::fromDto)
                .collect(Collectors.toList());
    }
}
