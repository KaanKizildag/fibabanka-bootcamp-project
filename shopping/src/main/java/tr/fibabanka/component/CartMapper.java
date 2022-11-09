package tr.fibabanka.component;

import org.springframework.stereotype.Component;
import tr.fibabanka.dto.CartDto;
import tr.fibabanka.entity.Cart;
import tr.fibabanka.service.CartProductServiceImpl;

@Component
public class CartMapper {

    private final CartProductServiceImpl cartProductServiceImpl;

    public CartMapper(CartProductServiceImpl cartProductServiceImpl) {
        this.cartProductServiceImpl = cartProductServiceImpl;
    }


    public CartDto fromEntity(Cart cart) {
        CartDto cartDto = new CartDto();

        cartDto.setId(cart.getId());
        cartDto.setCartProducts(cartProductServiceImpl.findCartProductByCartId(cart.getId()));
        cartDto.setCustomerName(cart.getCustomerName());

        return cartDto;
    }
}
