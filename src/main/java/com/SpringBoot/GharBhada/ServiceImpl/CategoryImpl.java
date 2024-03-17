package com.SpringBoot.GharBhada.ServiceImpl;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.SpringBoot.GharBhada.DTO.CategoryDto;
import com.SpringBoot.GharBhada.Entity.Category;
import com.SpringBoot.GharBhada.Exception.ResourceNotFoundException;
import com.SpringBoot.GharBhada.ModelMapper.CategoryModelMapper;
import com.SpringBoot.GharBhada.Repository.CategoryRepo;
import com.SpringBoot.GharBhada.Service.CategoryService;

@Component
public class CategoryImpl implements CategoryService {

	@Autowired
	private CategoryRepo categoryRepo;

	@Autowired
	private CategoryModelMapper categoryModelMapper;

	// Save Category
	@Override
	public CategoryDto createCategory(CategoryDto categoryDto) {
		Category categoryRequestToCategory = categoryModelMapper.CategoryDtoToCategory(categoryDto);
		Category save = categoryRepo.save(categoryRequestToCategory);
		CategoryDto categoryToCategoryResponse = categoryModelMapper.categoryToCategoryDto(save);
		return categoryToCategoryResponse;
	}

	// Get all Categories
	@Override
	public List<CategoryDto> getAllCategories() {
		List<Category> findAll = categoryRepo.findAll();
		List<CategoryDto> listOfCategory = findAll.stream()
				.map(category -> categoryModelMapper.categoryToCategoryDto(category)).collect(Collectors.toList());
		return listOfCategory;
	}

	// Delete Category
	@Override
	public boolean deleteCategory(Integer categoryId) {
		Optional<Category> findById = categoryRepo.findById(categoryId);
		if (findById.isEmpty()) {
			throw new ResourceNotFoundException("Category", categoryId.toString());
		}
		categoryRepo.delete(findById.get());
		return true;
	}

	@Override
	public CategoryDto updateCategory(Integer categoryId, CategoryDto categoryDto) {
		Optional<Category> category = categoryRepo.findById(categoryId);
		if (category.isEmpty()) {
			throw new ResourceNotFoundException("Category", categoryId.toString());
		}
		category.get().setCategoryDescription(categoryDto.getCategoryDescription());
		category.get().setCategoryName(categoryDto.getCategoryName());
		Category save = categoryRepo.save(category.get());
		CategoryDto categoryDTO = categoryModelMapper.categoryToCategoryDto(save);
		return categoryDTO;
	}

}
