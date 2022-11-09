package tr.fibabanka.service;

import org.springframework.stereotype.Service;
import tr.fibabanka.component.ProductMapper;
import tr.fibabanka.dto.ProductDto;
import tr.fibabanka.entity.Product;
import tr.fibabanka.exceptions.ProductNotFoundException;
import tr.fibabanka.repository.ProductRepository;

import java.util.List;

@Service
public class ProductServiceImpl implements ProductService {
    private final ProductRepository productRepository;
    private final ProductMapper productMapper;

    public ProductServiceImpl(ProductRepository productRepository, ProductMapper productMapper) {
        this.productRepository = productRepository;
        this.productMapper = productMapper;
    }

    public List<ProductDto> findProductsByCategoryId(Long categoryId) {
        List<Product> productList = productRepository.findByCategoryId(categoryId);
        return productMapper.fromEntityList(productList);
    }

    public ProductDto findById(Long id) {
        Product product = productRepository.findById(id)
                .orElseThrow(() -> new ProductNotFoundException("%d id ile ürün bulunamadı", id));
        return productMapper.fromEntity(product);
    }

}
