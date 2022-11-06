package tr.fibabanka.controller;

import org.springframework.web.bind.annotation.*;
import tr.fibabanka.dto.CartProductDto;
import tr.fibabanka.service.CartProductService;

@RestController
@RequestMapping("/shopping")
public class CartProductController {

    private final CartProductService cartProductService;

    public CartProductController(CartProductService cartProductService) {
        this.cartProductService = cartProductService;
    }

    @PostMapping("/cart/add")
    public CartProductDto addToCart(@RequestBody CartProductDto cartProductDto) {
        cartProductDto = cartProductService.addProductToCart(cartProductDto);
        return cartProductDto;
    }

    @DeleteMapping("/cart/{cartId}/remove/{productId}")
    public void deleteProductFromCart(@PathVariable("cartId") Long cartId,
                                      @PathVariable("productId") Long productId) {
        cartProductService.deleteProductFromCart(cartId, productId);
    }

}
