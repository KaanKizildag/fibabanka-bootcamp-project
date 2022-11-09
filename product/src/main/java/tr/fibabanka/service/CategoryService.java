package tr.fibabanka.service;

import tr.fibabanka.dto.CategoryDto;

import java.util.List;

public interface CategoryService {
    List<CategoryDto> findAllCategories();
}
