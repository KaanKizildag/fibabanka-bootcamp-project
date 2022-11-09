package tr.fibabanka.service;

import tr.fibabanka.dto.CartProductDto;

import java.util.List;

public interface CartProductService {

     void deleteProductFromCart(Long cartId, Long productId);

     List<CartProductDto> findCartProductByCartId(Long cartId);

     CartProductDto addProductToCart(CartProductDto cartProductDto);
}
