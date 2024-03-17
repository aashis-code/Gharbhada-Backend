package com.SpringBoot.GharBhada.ModelMapper;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;

import com.SpringBoot.GharBhada.DTO.CommentDto;
import com.SpringBoot.GharBhada.Entity.Comment;

public class CommentModelMapper {
	
	@Autowired
	private ModelMapper modelMapper;
	
	public Comment CommentDtoToComment(CommentDto commentDto) {
		return modelMapper.map(commentDto, Comment.class);
	}
	
	public CommentDto CommentToCommentDto(Comment comment) {
		return modelMapper.map(comment, CommentDto.class);
	}

}
