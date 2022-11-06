package tr.fibabanka.repository;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import tr.fibabanka.entity.CartProduct;

import java.util.List;

public interface CartProductRepository extends CrudRepository<CartProduct, Long> {
    @Query("From CartProduct Where cartId = :cartId")
    List<CartProduct> findByCartId(@Param(("cartId")) Long cartId);
}
