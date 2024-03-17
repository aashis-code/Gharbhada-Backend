package com.SpringBoot.GharBhada.DTO;

import com.SpringBoot.GharBhada.Utils.EntityValidation;

import jakarta.validation.constraints.DecimalMax;
import jakarta.validation.constraints.DecimalMin;
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
public class RatingDto implements EntityValidation  {

	private String rating_id;

	@DecimalMax(value = "5.0")
	@DecimalMin(value = "0.0")
	private double ratingValue;
	


}
