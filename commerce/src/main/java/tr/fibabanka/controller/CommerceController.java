package tr.fibabanka.controller;

import org.springframework.web.bind.annotation.*;
import tr.fibabanka.dtos.CartDto;
import tr.fibabanka.dtos.CartProductDto;
import tr.fibabanka.dtos.CategoryDto;
import tr.fibabanka.dtos.ProductDto;
import tr.fibabanka.service.CommerceService;
import tr.fibabanka.service.CommerceServiceImpl;

import java.util.List;

@RestController
@RequestMapping("/commerce")
public class CommerceController {

    private final CommerceService commerceServiceImpl;

    public CommerceController(CommerceServiceImpl commerceServiceImpl) {
        this.commerceServiceImpl = commerceServiceImpl;
    }

    @GetMapping("/inventory/products/{categoryId}")
    public List<ProductDto> findProductsByCategoryId(@PathVariable("categoryId") Long categoryId) {
        return commerceServiceImpl.findProductsByCategoryId(categoryId);
    }


    @GetMapping("/inventory/categories")
    public List<CategoryDto> findAllCategories() {
        return commerceServiceImpl.findAllCategories();
    }

    @GetMapping("/inventory/product/{productId}")
    public ProductDto findProductById(@PathVariable("productId") long id) {
        return commerceServiceImpl.findProductById(id);
    }

    @GetMapping("/shopping/cart/create/{customerName}")
    public Long createCart(@PathVariable("customerName") String customerName) {
        return commerceServiceImpl.createCart(customerName);
    }

    @PostMapping("/shopping/cart/add")
    public CartProductDto addToCart(@RequestBody CartProductDto cartProductDto) {
        cartProductDto = commerceServiceImpl.addProductToCart(cartProductDto);
        return cartProductDto;
    }

    @DeleteMapping("/shopping/cart/{cartId}/remove/{productId}")
    public void deleteProductFromCart(@PathVariable("cartId") Long cartId,
                                      @PathVariable("productId") Long productId) {
        commerceServiceImpl.deleteProductFromCart(cartId, productId);
    }

    @GetMapping("/shopping/checkout/{cartId}")
    public void checkout(@PathVariable("cartId") Long cartId) {
        commerceServiceImpl.checkout(cartId);
    }

    @GetMapping("/shopping/cart/find/{cartId}")
    public CartDto findCartById(@PathVariable("cartId") Long cartId) {
        return commerceServiceImpl.findById(cartId);
    }

}
