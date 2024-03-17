package com.SpringBoot.GharBhada.DTO;

import java.util.Date;
import java.util.List;

import com.SpringBoot.GharBhada.Entity.Image;
import com.SpringBoot.GharBhada.Utils.EntityValidation;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class HomeDto implements EntityValidation{
	
	private String home_id; 
	
	@NotBlank(message = TITLE_BLANK_MESSAGE)
	@Size(min = 10 , message = TITLE_LENGTH)
	private String title;
	
	@Min(value = 1, message =  LOCATION_BLANK)
	private double latitude;
	
	private double longitude;
	
	 private Date createdAt;
     
	 private Date updatedAt;
	 
	 @Min(value = 1, message = HOME_PRICE)
	 private double price;
	 
	 @Min(value = 1, message = HOME_AREA)
	 private double area;
	 
	 @Min(value = 1, message= HOME_BEDROOM)
	 private long bedroom;
	 
	 @Min(value = 1, message = HOME_BATHROOM)
	 private long baths;
	 
	 private boolean rented;
	 
	 private double averageRating;
	
	 @NotBlank(message = DESCRIPTION_BLANK_MESSAGE)
	 @Size(min = 50, max = 300, message = HOME_DESCRIPTION)
	private String description;
	
	 @NotBlank(message = DISTRICT_BLANK_MESSAGE)
    private String district;
	
	@NotBlank(message = CITY_BLANK_MESSAGE)
	private String city;
	
	@NotBlank(message = HOME_AMENITIES)
	private String amenities;
	
	private PersonDto person;
	
	private CategoryDto category;
	
}
