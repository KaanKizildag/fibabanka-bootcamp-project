package tr.fibabanka.service;

import tr.fibabanka.dto.ProductDto;

import java.util.List;

public interface ProductService {

    List<ProductDto> findProductsByCategoryId(Long categoryId);

    ProductDto findById(Long id);
}
