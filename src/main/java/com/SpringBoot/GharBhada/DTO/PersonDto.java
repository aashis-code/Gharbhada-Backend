package com.SpringBoot.GharBhada.DTO;

import com.SpringBoot.GharBhada.Utils.EntityValidation;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
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
public class PersonDto implements EntityValidation {

	private String person_id;

	@NotBlank(message = NAME_MESSAGE)
	@Size(min = 5, message = NAME_SIZE_MESSAGE)
	private String name;

	@Pattern(regexp = EMAIL_PATTERN, message = EMAIL_PATTERN_ERROR)
	private String email;

	
	@NotBlank(message = PASSWORD_MESSAGE)
	@Size(min = 3, message = PASSWORD_SIZE_MESSAGE)
	private String password;

	@Size(min = 10, max = 10, message = PHONE_NUM)
	private String phone;

	@NotBlank(message = ADDRESS_MESSAGGE)
	private String address;

}
