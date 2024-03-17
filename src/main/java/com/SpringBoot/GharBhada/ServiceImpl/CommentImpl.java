package com.SpringBoot.GharBhada.ServiceImpl;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.SpringBoot.GharBhada.DTO.CommentDto;
import com.SpringBoot.GharBhada.DTO.HomeDto;
import com.SpringBoot.GharBhada.Entity.Comment;
import com.SpringBoot.GharBhada.Entity.Home;
import com.SpringBoot.GharBhada.Entity.Person;
import com.SpringBoot.GharBhada.Exception.ResourceNotFoundException;
import com.SpringBoot.GharBhada.ModelMapper.CommentModelMapper;
import com.SpringBoot.GharBhada.ModelMapper.HomeModelMapper;
import com.SpringBoot.GharBhada.Repository.CommentRepo;
import com.SpringBoot.GharBhada.Service.CommentService;
import com.SpringBoot.GharBhada.Service.HomeService;
import com.SpringBoot.GharBhada.Service.PersonService;

@Component
public class CommentImpl implements CommentService {

	@Autowired
	private CommentRepo commentRepo;

	@Autowired
	private CommentModelMapper commentModelMapper;

	@Autowired
	private HomeModelMapper homeModelMapper;

	@Autowired
	private PersonService personService;

	@Autowired
	private HomeService homeService;

	// Add Comment
	@Override
	public CommentDto addComment(CommentDto commentDto, String userId, String homeId) {
		personService.getSingleUser(userId);
		homeService.getSingleHome(homeId);

		Person person = new Person();
		person.setPerson_id(userId);

		Home home = new Home();
		home.setHome_id(homeId);

		String commentId = UUID.randomUUID().toString();
		commentDto.setComment_id(commentId);
		Comment commentRequestToComment = commentModelMapper.CommentDtoToComment(commentDto);
		commentRequestToComment.setPerson(person);
		commentRequestToComment.setHome(home);
		Comment save = commentRepo.save(commentRequestToComment);
		CommentDto commentToCommentResponse = commentModelMapper.CommentToCommentDto(save);
		return commentToCommentResponse;
	}

	@Override
	public void deleteCommentByCommentId(String commentId) {
		Comment comment = commentRepo.findById(commentId)
				.orElseThrow(() -> new ResourceNotFoundException("Comment ", commentId));
		commentRepo.delete(comment);
	}

	@Override
	public List<CommentDto> getCommentsByHomeId(String homeId) {
		HomeDto singleHome = homeService.getSingleHome(homeId);
		Home homeDtoToHome = homeModelMapper.HomeDtoToHome(singleHome);
		List<Comment> comments = commentRepo.findByHome(homeDtoToHome);
		List<CommentDto> commentList = comments.stream().map(comment -> commentModelMapper.CommentToCommentDto(comment))
				.collect(Collectors.toList());
		return commentList;
	}

}
