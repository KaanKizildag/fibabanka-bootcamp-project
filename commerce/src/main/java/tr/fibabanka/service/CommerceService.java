package tr.fibabanka.service;

import tr.fibabanka.dtos.CartDto;
import tr.fibabanka.dtos.CartProductDto;
import tr.fibabanka.dtos.CategoryDto;
import tr.fibabanka.dtos.ProductDto;

import java.util.List;

public interface CommerceService {
    List<ProductDto> findProductsByCategoryId(long categoryId);

    List<CategoryDto> findAllCategories();

    ProductDto findProductById(Long productId);

    Long createCart(String customerName);

    CartProductDto addProductToCart(CartProductDto cartProductDto);

    void deleteProductFromCart(Long cartId, Long productId);

    void checkout(Long cartId);

    CartDto findById(Long cartId);
}
