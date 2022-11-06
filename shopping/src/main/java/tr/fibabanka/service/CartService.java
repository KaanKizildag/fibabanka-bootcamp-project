package tr.fibabanka.service;

import org.springframework.stereotype.Service;
import tr.fibabanka.component.CartMapper;
import tr.fibabanka.dto.CartDto;
import tr.fibabanka.entity.Cart;
import tr.fibabanka.repository.CartRepository;

import java.math.BigDecimal;

import static tr.fibabanka.dto.enums.CartStatus.BITMIS;
import static tr.fibabanka.dto.enums.CartStatus.YENI;

@Service
public class CartService {
    private final CartRepository cartRepository;
    private final CartMapper cartMapper;

    public CartService(CartRepository cartRepository, CartMapper cartMapper) {
        this.cartRepository = cartRepository;
        this.cartMapper = cartMapper;
    }

    public Long createCart(String customerName) {
        Cart cart = generateCart(customerName);
        return cart.getId();
    }

    public CartDto findById(Long id) {
        Cart cart = cartRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Sepet Bulunamadı."));
        return cartMapper.fromEntity(cart);
    }

    public void checkout(Long cartId) {
        Cart cart = cartRepository.findById(cartId)
                //todo hata yönetimi yapılacak
                .orElseThrow(() -> new RuntimeException("Sepet bulunamadı"));
        cart.setCartStatus(BITMIS.value);
        cartRepository.save(cart);
    }

    private Cart generateCart(String customerName) {
        Cart cart = new Cart();
        cart.setCartStatus(YENI.value);
        cart.setCustomerName(customerName);
        cart.setTotalAmount(BigDecimal.ZERO);
        cart = cartRepository.save(cart);
        return cart;
    }
}
