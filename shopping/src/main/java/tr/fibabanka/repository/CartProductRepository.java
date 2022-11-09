package tr.fibabanka.repository;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;
import tr.fibabanka.entity.CartProduct;

import java.util.List;

public interface CartProductRepository extends CrudRepository<CartProduct, Long> {

    @Transactional
    @Modifying
    @Query("delete from CartProduct c Where c.cartId = :cartId And c.productId = :productId")
    void deleteByCartIdAndProductId(@Param("cartId") Long cartId, @Param("productId") Long productId);

    @Query("From CartProduct Where cartId = :cartId")
    List<CartProduct> findByCartId(@Param(("cartId")) Long cartId);
}
