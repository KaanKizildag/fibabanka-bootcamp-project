package tr.fibabanka.component;

import org.springframework.stereotype.Component;
import tr.fibabanka.dto.CartDto;
import tr.fibabanka.entity.Cart;

import java.util.ArrayList;

@Component
public class CartMapper {
    public CartDto fromEntity(Cart cart) {
        CartDto cartDto = new CartDto();

        cartDto.setId(cart.getId());
        cartDto.setCartProducts(new ArrayList<>());
        cartDto.setCustomerName(cart.getCustomerName());

        return cartDto;
    }
}
