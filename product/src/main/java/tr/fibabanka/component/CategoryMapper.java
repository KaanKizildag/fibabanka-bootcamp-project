package tr.fibabanka.component;

import org.springframework.stereotype.Component;
import tr.fibabanka.dto.CategoryDto;
import tr.fibabanka.entity.Category;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class CategoryMapper {

    public CategoryDto fromEntity(Category category) {
        CategoryDto categoryDto = new CategoryDto();

        categoryDto.setCategoryName(category.getCategoryName());
        categoryDto.setId(category.getId());

        return categoryDto;
    }

    public List<CategoryDto> fromEntityList(List<Category> categoryList) {
        return categoryList
                .stream()
                .map(this::fromEntity)
                .collect(Collectors.toList());
    }
}
