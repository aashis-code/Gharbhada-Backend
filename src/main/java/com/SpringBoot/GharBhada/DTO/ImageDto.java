package com.SpringBoot.GharBhada.DTO;

import com.SpringBoot.GharBhada.Utils.EntityValidation;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class ImageDto implements EntityValidation {

	private String image_id;

	@NotBlank(message = IMAGE_PATH_BLANK_MESSAGE)
	private String image_path;

}
