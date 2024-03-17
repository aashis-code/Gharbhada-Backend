package com.SpringBoot.GharBhada.Controller;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.SpringBoot.GharBhada.DTO.CommentDto;
import com.SpringBoot.GharBhada.Service.CommentService;

import jakarta.validation.Valid;

@RestController
@RequestMapping(value = "/api/comment")
public class CommentController {

	@Autowired
	private CommentService commentService;

	// Add Comment
	@PostMapping(value = "/user/{userId}/home/{homeId}", consumes = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<CommentDto> addComment(@PathVariable String userId, @PathVariable String homeId,
			@RequestBody @Valid CommentDto commentDto) {
		CommentDto addComment = commentService.addComment(commentDto, userId, homeId);
		return new ResponseEntity<CommentDto>(addComment, HttpStatus.CREATED);
	}

	// Deleting Comment
	@DeleteMapping("/{commentId}")
	public ResponseEntity<Map<String, String>> deleteComment(@PathVariable String commentId) {
		commentService.deleteCommentByCommentId(commentId);
		return new ResponseEntity<>(Map.of("message", "Deleted successfully !"), HttpStatus.OK);
	}

	// Get comments by home Id
	@GetMapping("/home/{homeId}")
	public ResponseEntity<List<CommentDto>> getCommentsByHomeId(@PathVariable String homeId) {
		List<CommentDto> commentsByHomeId = commentService.getCommentsByHomeId(homeId);
		return new ResponseEntity<>(commentsByHomeId, HttpStatus.OK);
	}

}
