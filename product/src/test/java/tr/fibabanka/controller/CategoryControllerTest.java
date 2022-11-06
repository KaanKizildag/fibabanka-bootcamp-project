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
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import tr.fibabanka.dto.CategoryDto;
import tr.fibabanka.service.CategoryService;

import java.util.ArrayList;
import java.util.List;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(MockitoExtension.class)
class CategoryControllerTest {

    @Mock
    private CategoryService categoryService;


    @Autowired
    private MockMvc mockMvc;

    @InjectMocks
    private CategoryController categoryController;


    @BeforeEach
    void setUp() {
        mockMvc = MockMvcBuilders.standaloneSetup(categoryController)
                .build();
    }

    @SneakyThrows
    @Test
    @DisplayName("Kategoriler listelenebilmelidir.")
    void findAllCategories() {

        List<CategoryDto> mockCategoryDtos = getMockCategoryDtos();
        Mockito.when(categoryService.findAllCategories())
                .thenReturn(mockCategoryDtos);

        mockMvc.perform(get("/inventory/categories")
                        .contentType(MediaType.APPLICATION_JSON)
                )
                .andExpect(jsonPath("$.[0].id", Matchers.is(mockCategoryDtos.get(0).getId().intValue())))
                .andExpect(jsonPath("$.[0].categoryName", Matchers.is(mockCategoryDtos.get(0).getCategoryName())))
                .andExpect(jsonPath("$.[1].id", Matchers.is(mockCategoryDtos.get(1).getId().intValue())))
                .andExpect(jsonPath("$.[1].categoryName", Matchers.is(mockCategoryDtos.get(1).getCategoryName())))
                .andExpect(jsonPath("$.[2].id", Matchers.is(mockCategoryDtos.get(2).getId().intValue())))
                .andExpect(jsonPath("$.[2].categoryName", Matchers.is(mockCategoryDtos.get(2).getCategoryName())))
                .andExpect(status().isOk())
                .andDo(print());
    }

    private List<CategoryDto> getMockCategoryDtos() {
        List<CategoryDto> categoryDtoList = new ArrayList<>();
        categoryDtoList.add(new CategoryDto(1L, "Elektronik"));
        categoryDtoList.add(new CategoryDto(2L, "Yiyecek"));
        categoryDtoList.add(new CategoryDto(3L, "İçecek"));

        return categoryDtoList;
    }
}