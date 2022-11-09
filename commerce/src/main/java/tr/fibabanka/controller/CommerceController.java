package tr.fibabanka.controller;

import org.springframework.web.bind.annotation.*;
import tr.fibabanka.CommerceService;
import tr.fibabanka.dtos.CartDto;
import tr.fibabanka.dtos.CartProductDto;
import tr.fibabanka.dtos.CategoryDto;
import tr.fibabanka.dtos.ProductDto;

import java.util.List;

@RestController
@RequestMapping("/commerce")
public class CommerceController {

    private final CommerceService commerceService;

    public CommerceController(CommerceService commerceService) {
        this.commerceService = commerceService;
    }

    @GetMapping("/inventory/products/{categoryId}")
    public List<ProductDto> findProductsByCategoryId(@PathVariable("categoryId") Long categoryId) {
        return commerceService.findProductsByCategoryId(categoryId);
    }


    @GetMapping("/inventory/categories")
    public List<CategoryDto> findAllCategories() {
        return commerceService.findAllCategories();
    }

    @GetMapping("/inventory/product/{productId}")
    public ProductDto findProductById(@PathVariable("productId") long id) {
        return commerceService.findProductById(id);
    }

    @GetMapping("/shopping/cart/create/{customerName}")
    public Long createCart(@PathVariable("customerName") String customerName) {
        return commerceService.createCart(customerName);
    }

    @PostMapping("/shopping/cart/add")
    public CartProductDto addToCart(@RequestBody CartProductDto cartProductDto) {
        cartProductDto = commerceService.addProductToCart(cartProductDto);
        return cartProductDto;
    }

    @DeleteMapping("/shopping/cart/{cartId}/remove/{productId}")
    public void deleteProductFromCart(@PathVariable("cartId") Long cartId,
                                      @PathVariable("productId") Long productId) {
        commerceService.deleteProductFromCart(cartId, productId);
    }

    @GetMapping("/shopping/checkout/{cartId}")
    public void checkout(@PathVariable("cartId") Long cartId) {
        commerceService.checkout(cartId);
    }

    @GetMapping("/shopping/cart/find/{cartId}")
    public CartDto findCartById(@PathVariable("cartId") Long cartId) {
        return commerceService.findById(cartId);
    }

}
