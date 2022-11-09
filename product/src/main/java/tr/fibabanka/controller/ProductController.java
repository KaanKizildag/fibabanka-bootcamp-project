package tr.fibabanka.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import tr.fibabanka.dto.ProductDto;
import tr.fibabanka.service.ProductService;

import java.util.List;

@RestController
@RequestMapping("/inventory")
@RequiredArgsConstructor
public class ProductController {

    private final ProductService productServiceImpl;

    @GetMapping("/products/{categoryId}")
    public List<ProductDto> findProductsByCategoryId(@PathVariable("categoryId") Long categoryId) {
        return productServiceImpl.findProductsByCategoryId(categoryId);
    }

    @GetMapping("/product/{id}")
    public ProductDto findProductById(@PathVariable("id") Long id) {
        return productServiceImpl.findById(id);
    }
}
