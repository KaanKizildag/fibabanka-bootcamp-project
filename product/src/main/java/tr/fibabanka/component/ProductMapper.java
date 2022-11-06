package tr.fibabanka.component;

import org.springframework.stereotype.Component;
import tr.fibabanka.dto.ProductDto;
import tr.fibabanka.entity.Product;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class ProductMapper {

    public ProductDto fromEntity(Product product) {
        ProductDto productDto = new ProductDto();

        productDto.setProductName(product.getProductName());
        productDto.setId(product.getId());
        productDto.setCategoryId(product.getCategory().getId());
        productDto.setSalesPrice(product.getSalesPrice());

        return productDto;
    }

    public List<ProductDto> fromEntityList(List<Product> productList) {
        return productList.stream().map(this::fromEntity)
                .collect(Collectors.toList());
    }
}
