package com.SpringBoot.GharBhada.DTO;

import java.util.List;

import com.SpringBoot.GharBhada.Utils.EntityValidation;
import com.fasterxml.jackson.annotation.JsonBackReference;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class CategoryDto implements EntityValidation{


	private int category_id;

	@NotBlank(message = CATEGORY_NAME_BLANK_MESSAGE)
	@Size(min = 3, message = CATEGORY_NAME_MIN_LENGTH)
	private String categoryName;
	
	@NotBlank(message = CATEGORY_DESCRIPTION_BLANK_MESSAGE)
	@Size(min = 15, message = CATEGORY_DESCRIPTION_MIN_LENGTH)
	private String categoryDescription;

	@JsonBackReference
	private List<HomeDto> homes;

}
