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
public class CommentDto implements EntityValidation {

	private String comment_id;

	@NotBlank(message = COMMENT_BLANK_MESSAGE)
	private String content;

//	@JsonBackReference
//	private PersonDto personId;

//	@JsonBackReference
//	private HomeResponse homeId;
}
