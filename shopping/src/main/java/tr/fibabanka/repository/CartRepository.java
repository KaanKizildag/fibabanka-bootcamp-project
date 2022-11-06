package tr.fibabanka.repository;


import org.springframework.data.repository.CrudRepository;
import tr.fibabanka.entity.Cart;

public interface CartRepository extends CrudRepository<Cart, Long> {
}
