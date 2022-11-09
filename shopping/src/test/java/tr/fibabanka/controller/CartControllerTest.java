package tr.fibabanka.controller;

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
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import tr.fibabanka.dto.CartDto;
import tr.fibabanka.service.CartServiceImpl;

import java.util.ArrayList;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(MockitoExtension.class)
class CartControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Mock
    private CartServiceImpl cartServiceImpl;

    @InjectMocks
    private CartController cartController;

    @BeforeEach
    void setUp() {
        mockMvc = MockMvcBuilders.standaloneSetup(cartController)
                .build();
    }

    @Test
    @DisplayName("Sepet oluşturulabilmelidir")
    void createCart() throws Exception {
        mockMvc.perform(get("/shopping/cart/create/huseyin")
                        .contentType(MediaType.APPLICATION_JSON)
                )
                .andExpect(status().isOk())
                .andDo(print());
    }

    @Test
    @DisplayName("Sepet durumu güncellenebilmelidir.")
    void checkout() throws Exception {
        long cartId = 1L;

        mockMvc.perform(get("/shopping/checkout/" + cartId)
                        .contentType(MediaType.APPLICATION_JSON)
                )
                .andExpect(status().isOk())
                .andDo(print());
    }

    @Test
    @DisplayName("Sepet Id ile sorgulanabilmelidir.")
    void findCartById() throws Exception {
        Long cartId = 1L;

        String customerName = "Hüseyin";
        Mockito.when(cartServiceImpl.findById(cartId))
                .thenReturn(new CartDto(1L, customerName, new ArrayList<>()));

        mockMvc.perform(get("/shopping/cart/find/" + cartId)
                        .contentType(MediaType.APPLICATION_JSON)
                )
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.customerName", Matchers.is(customerName)))
                .andExpect(jsonPath("$.id", Matchers.is(cartId.intValue())))
                .andDo(print());
    }


}