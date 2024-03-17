package com.SpringBoot.GharBhada.Service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.SpringBoot.GharBhada.DTO.CategoryDto;

@Service
public interface CategoryService {
	
	//Create Category
	CategoryDto createCategory(CategoryDto categoryDto);
	
	//Get All Categories
	List<CategoryDto> getAllCategories();
	
	//Category Update
	CategoryDto updateCategory(Integer categoryId,CategoryDto categoryDto);
	
	//Delete Category
	boolean deleteCategory(Integer categoryId);

}
