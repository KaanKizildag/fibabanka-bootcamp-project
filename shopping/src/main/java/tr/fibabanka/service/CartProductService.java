package tr.fibabanka.service;

import org.springframework.stereotype.Service;
import tr.fibabanka.component.CartProductMapper;
import tr.fibabanka.dto.CartProductDto;
import tr.fibabanka.entity.CartProduct;
import tr.fibabanka.repository.CartProductRepository;

import java.util.List;

@Service
public class CartProductService {

    private final CartProductRepository cartProductRepository;
    private final CartProductMapper cartProductMapper;

    public CartProductService(CartProductRepository cartProductRepository, CartProductMapper cartProductMapper) {
        this.cartProductRepository = cartProductRepository;
        this.cartProductMapper = cartProductMapper;
    }


    public void deleteProductFromCart(Long cartId, Long productId) {
        cartProductRepository.deleteByCartIdAndProductId(cartId, productId);
    }

    public List<CartProductDto> findCartProductByCartId(Long cartId) {
        List<CartProduct> cartProducts = cartProductRepository.findByCartId(cartId);
        return cartProductMapper.fromEntityList(cartProducts);
    }

    public CartProductDto addProductToCart(CartProductDto cartProductDto) {
        CartProduct cartProduct = cartProductMapper.fromDto(cartProductDto);
        cartProduct = cartProductRepository.save(cartProduct);
        return cartProductMapper.fromDto(cartProduct);
    }
}
