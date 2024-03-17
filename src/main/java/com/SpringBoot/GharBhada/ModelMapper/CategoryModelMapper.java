package com.SpringBoot.GharBhada.ModelMapper;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;

import com.SpringBoot.GharBhada.DTO.CategoryDto;
import com.SpringBoot.GharBhada.Entity.Category;

public class CategoryModelMapper {
	
	@Autowired
	private ModelMapper modelMapper;
	
   public Category CategoryDtoToCategory(CategoryDto categoryDto) {
	   return modelMapper.map(categoryDto, Category.class);
   }
	
	public CategoryDto categoryToCategoryDto(Category category) {
		return modelMapper.map(category, CategoryDto.class);
	}

}
