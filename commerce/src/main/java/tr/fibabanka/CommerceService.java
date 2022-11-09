package tr.fibabanka;

import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import tr.fibabanka.config.Configurations;
import tr.fibabanka.dtos.CartDto;
import tr.fibabanka.dtos.CartProductDto;
import tr.fibabanka.dtos.CategoryDto;
import tr.fibabanka.dtos.ProductDto;

import java.util.List;

@Service
public class CommerceService {

    private final RestTemplate restTemplate = new RestTemplate();
    private final Configurations configurations;


    public CommerceService(Configurations configurations) {
        this.configurations = configurations;
    }

    public List<ProductDto> findProductsByCategoryId(long categoryId) {
        String inventoryAppBaseUrl = configurations.getInventoryAppBaseUrl();
        return restTemplate
                .getForEntity(inventoryAppBaseUrl + "/inventory/products/" + categoryId, List.class)
                .getBody();
    }

    public List<CategoryDto> findAllCategories() {
        String inventoryAppBaseUrl = configurations.getInventoryAppBaseUrl();
        return restTemplate
                .getForEntity(inventoryAppBaseUrl + "/inventory/categories/", List.class)
                .getBody();
    }

    public ProductDto findProductById(Long productId) {
        String inventoryAppBaseUrl = configurations.getInventoryAppBaseUrl();
        return restTemplate
                .getForEntity(inventoryAppBaseUrl + "/inventory/product/" + productId, ProductDto.class)
                .getBody();
    }

    public Long createCart(String customerName) {
        String shoppingAppBaseUrl = configurations.getShoppingAppBaseUrl();
        return restTemplate
                .getForEntity(shoppingAppBaseUrl + "/shopping/cart/create/" + customerName, Long.class)
                .getBody();
    }

    public CartProductDto addProductToCart(CartProductDto cartProductDto) {
        String shoppingAppBaseUrl = configurations.getShoppingAppBaseUrl();
        return restTemplate
                .postForEntity(shoppingAppBaseUrl + "/shopping/cart/add", cartProductDto, CartProductDto.class)
                .getBody();
    }

    public void deleteProductFromCart(Long cartId, Long productId) {
        String shoppingAppBaseUrl = configurations.getShoppingAppBaseUrl();
        restTemplate
                .delete(shoppingAppBaseUrl + "/shopping/cart/" + cartId + "/remove/" + productId);
    }

    public void checkout(Long cartId) {
        String shoppingAppBaseUrl = configurations.getShoppingAppBaseUrl();
        restTemplate
                .getForEntity(shoppingAppBaseUrl + "/shopping/checkout/" + cartId, Void.class);
    }

    public CartDto findById(Long cartId) {
        String shoppingAppBaseUrl = configurations.getShoppingAppBaseUrl();
        return restTemplate
                .getForEntity(shoppingAppBaseUrl + "/shopping/cart/find/" + cartId, CartDto.class)
                .getBody();
    }
}
