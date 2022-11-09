package tr.fibabanka.service;

import tr.fibabanka.dto.CartDto;

public interface CartService {

    Long createCart(String customerName);

    CartDto findById(Long id);

    void checkout(Long cartId);

}
