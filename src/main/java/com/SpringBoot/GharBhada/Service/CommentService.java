package com.SpringBoot.GharBhada.Service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.SpringBoot.GharBhada.DTO.CommentDto;

@Service
public interface CommentService {

	// Add Comment
	CommentDto addComment(CommentDto commentDto, String userId, String homeId);

	// get all comments homeId
	List<CommentDto> getCommentsByHomeId(String homeId);

	// Delete Comment By Comment commentId
	void deleteCommentByCommentId(String commentId);

}
