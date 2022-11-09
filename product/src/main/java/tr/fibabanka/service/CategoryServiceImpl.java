package tr.fibabanka.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import tr.fibabanka.component.CategoryMapper;
import tr.fibabanka.dto.CategoryDto;
import tr.fibabanka.entity.Category;
import tr.fibabanka.repository.CategoryRepository;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CategoryServiceImpl implements CategoryService {
    private final CategoryRepository categoryRepository;
    private final CategoryMapper categoryMapper;

    public List<CategoryDto> findAllCategories() {
        List<Category> categories = categoryRepository.findAll();
        return categoryMapper.fromEntityList(categories);
    }

}
