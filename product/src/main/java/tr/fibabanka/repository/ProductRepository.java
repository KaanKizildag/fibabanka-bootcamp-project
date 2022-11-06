package tr.fibabanka.repository;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import tr.fibabanka.entity.Product;

import java.util.List;

@Repository
public interface ProductRepository extends CrudRepository<Product, Long> {

    @Query("From Product p Where p.category.id = :categoryId")
    List<Product> findByCategoryId(@Param("categoryId") Long categoriId);

}
