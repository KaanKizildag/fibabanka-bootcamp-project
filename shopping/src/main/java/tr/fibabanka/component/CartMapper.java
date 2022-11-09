package tr.fibabanka.component;

import org.springframework.stereotype.Component;
import tr.fibabanka.dto.CartDto;
import tr.fibabanka.entity.Cart;
import tr.fibabanka.service.CartProductService;

@Component
public class CartMapper {

    private final CartProductService cartProductService;

    public CartMapper(CartProductService cartProductService) {
        this.cartProductService = cartProductService;
    }


    public CartDto fromEntity(Cart cart) {
        CartDto cartDto = new CartDto();

        cartDto.setId(cart.getId());
        cartDto.setCartProducts(cartProductService.findCartProductByCartId(cart.getId()));
        cartDto.setCustomerName(cart.getCustomerName());

        return cartDto;
    }
}
