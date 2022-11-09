package tr.fibabanka.controller;

import org.springframework.web.bind.annotation.*;
import tr.fibabanka.dto.CartProductDto;
import tr.fibabanka.service.CartProductService;

@RestController
@RequestMapping("/shopping")
public class CartProductController {

    private final CartProductService cartProductServiceImpl;

    public CartProductController(CartProductService cartProductServiceImpl) {
        this.cartProductServiceImpl = cartProductServiceImpl;
    }

    @PostMapping("/cart/add")
    public CartProductDto addToCart(@RequestBody CartProductDto cartProductDto) {
        cartProductDto = cartProductServiceImpl.addProductToCart(cartProductDto);
        return cartProductDto;
    }

    @DeleteMapping("/cart/{cartId}/remove/{productId}")
    public void deleteProductFromCart(@PathVariable("cartId") Long cartId,
                                      @PathVariable("productId") Long productId) {
        cartProductServiceImpl.deleteProductFromCart(cartId, productId);
    }

}
