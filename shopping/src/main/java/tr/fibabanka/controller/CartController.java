package tr.fibabanka.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import tr.fibabanka.dto.CartDto;
import tr.fibabanka.service.CartService;

@RestController
@RequestMapping("/shopping")
public class CartController {

    private final CartService cartServiceImpl;

    public CartController(CartService cartServiceImpl) {
        this.cartServiceImpl = cartServiceImpl;
    }

    @GetMapping("/cart/create/{customerName}")
    public Long createCart(@PathVariable("customerName") String customerName) {
        return cartServiceImpl.createCart(customerName);
    }

    @GetMapping("/checkout/{cartId}")
    public void checkout(@PathVariable("cartId") Long cartId) {
        cartServiceImpl.checkout(cartId);
    }

    @GetMapping("/cart/find/{cartId}")
    public CartDto findCartById(@PathVariable("cartId") Long cartId) {
        return cartServiceImpl.findById(cartId);
    }
}
