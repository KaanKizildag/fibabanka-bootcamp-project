package tr.fibabanka.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.SneakyThrows;
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
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import tr.fibabanka.dto.CartProductDto;
import tr.fibabanka.service.CartProductServiceImpl;

import java.math.BigDecimal;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(MockitoExtension.class)
class CartProductControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Mock
    private CartProductServiceImpl cartProductServiceImpl;

    @InjectMocks
    private CartProductController cartProductController;

    @BeforeEach
    void setUp() {
        mockMvc = MockMvcBuilders.standaloneSetup(cartProductController)
                .build();
    }

    @SneakyThrows
    @Test
    @DisplayName("Sepete ürün eklenebilmelidir.")
    void addToCart() {
        CartProductDto cartProductDto = getMockCartProductDto();

        Mockito.when(cartProductServiceImpl.addProductToCart(Mockito.any()))
                .thenReturn(cartProductDto);

        mockMvc.perform(post("/shopping/cart/add")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(new ObjectMapper().writeValueAsString(cartProductDto))
                )
                .andExpect(status().isOk())
                .andDo(print());
    }

    private CartProductDto getMockCartProductDto() {
        return new CartProductDto(1L, 1L, 1L, 5, BigDecimal.TEN, BigDecimal.ZERO);
    }

    @SneakyThrows
    @Test
    @DisplayName("Sepetten ürün silinebilmelidir.")
    void deleteProductFromCart() {
        long cartId = 1L;
        long productId = 1L;

        mockMvc.perform(delete("/shopping//cart/" + cartId + "/remove/" + productId)
                        .contentType(MediaType.APPLICATION_JSON)
                )
                .andExpect(status().isOk())
                .andDo(print());
    }
}