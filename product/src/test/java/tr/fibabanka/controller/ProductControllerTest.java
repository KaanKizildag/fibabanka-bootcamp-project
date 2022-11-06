package tr.fibabanka.controller;

import lombok.SneakyThrows;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import tr.fibabanka.dto.ProductDto;
import tr.fibabanka.service.ProductService;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(MockitoExtension.class)
class ProductControllerTest {

    @Mock
    private ProductService productService;

    @InjectMocks
    private ProductController productController;

    @Autowired
    private MockMvc mockMvc;

    @BeforeEach
    void setUp() {
        mockMvc = MockMvcBuilders.standaloneSetup(productController)
                .build();
    }

    @SneakyThrows
    @Test
    @DisplayName("Kategori idsine göre ürünler listelenebilmelidir.")
    void findProductsByCategoryId() {
        long categoryId = 1L;
        List<ProductDto> mockProductListByCategoryId = getMockProductListByCategoryId(categoryId);

        Mockito.when(productService.findProductsByCategoryId(categoryId))
                .thenReturn(mockProductListByCategoryId);

        ResultActions resultActions = mockMvc.perform(get("/inventory/products/" + categoryId)
                        .contentType(MediaType.APPLICATION_JSON)
                ).andExpect(status().isOk())
                .andDo(print());

        for (int i = 0; i < mockProductListByCategoryId.size(); i++) {
            int id = mockProductListByCategoryId.get(i).getId().intValue();
            String productName = mockProductListByCategoryId.get(i).getProductName();
            double salesPrice = mockProductListByCategoryId.get(i).getSalesPrice().doubleValue();

            resultActions
                    .andExpect(jsonPath("$.[" + i + "].id", Matchers.is(id)))
                    .andExpect(jsonPath("$.[" + i + "].productName", Matchers.is(productName)))
                    .andExpect(jsonPath("$.[" + i + "].salesPrice", Matchers.is(salesPrice)))
                    .andExpect(jsonPath("$.[" + i + "].categoryId", Matchers.is((int) categoryId)));
        }
        Mockito.verify(productService).findProductsByCategoryId(categoryId);
    }

    @SneakyThrows
    @Test
    @DisplayName("ürün idsi ile ürün bulunabilmelidir.")
    void findProductById() {
        ProductDto productDto = getMockProductDto();
        Long productId = 1L;

        Mockito.when(productService.findById(productId)).thenReturn(productDto);

        mockMvc.perform(get("/inventory/product/" + productId)
                        .contentType(MediaType.APPLICATION_JSON)
                ).andExpect(status().isOk())
                .andExpect(jsonPath("$.id", Matchers.is(productId.intValue())))
                .andExpect(jsonPath("$.productName", Matchers.is(productDto.getProductName())))
                .andExpect(jsonPath("$.salesPrice", Matchers.is(productDto.getSalesPrice().doubleValue())))
                .andExpect(jsonPath("$.categoryId", Matchers.is(productDto.getCategoryId().intValue())))
                .andDo(print());
        Mockito.verify(productService).findById(productId);
    }

    private List<ProductDto> getMockProductListByCategoryId(long categoryId) {
        List<ProductDto> productDtoList = new ArrayList<>();
        productDtoList.add(new ProductDto(1L, "Cep telefonu", BigDecimal.valueOf(1520.5), categoryId));
        productDtoList.add(new ProductDto(2L, "Dizüstü bilgisayar", BigDecimal.valueOf(5783.99), categoryId));
        return productDtoList;
    }

    private ProductDto getMockProductDto() {
        return new ProductDto(1L, "Cep telefonu", BigDecimal.valueOf(1520.5), 1L);
    }
}