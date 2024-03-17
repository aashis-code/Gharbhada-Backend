package com.SpringBoot.GharBhada.Controller;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.SpringBoot.GharBhada.DTO.RatingDto;
import com.SpringBoot.GharBhada.Service.RatingService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/rating")
public class RatingController {

	@Autowired
	private RatingService ratingService;

	// create new rating
	@PostMapping("/user/{userId}/home/{homeId}")
	public ResponseEntity<RatingDto> cretaRating(@PathVariable String userId, @PathVariable String homeId,
			@RequestBody @Valid RatingDto ratingDto) {
		RatingDto createRating = ratingService.createRating(ratingDto, userId, homeId);
		return new ResponseEntity<RatingDto>(createRating, HttpStatus.CREATED);
	}
	
	//Get Ratings By Person Id
	@GetMapping("/user/{userId}/home/{homeId}")
	public ResponseEntity<Map<String, Double>> getRatingsByPersonId(@PathVariable String homeId, @PathVariable String userId){
		double createSingleRating = ratingService.getSingleRating(userId, homeId);
		return new ResponseEntity<>(Map.of("userRating", createSingleRating), HttpStatus.OK);
	}
	
	@GetMapping("/home/{homeId}/allrating")
	public ResponseEntity<List<RatingDto>> getRatingsOfHomeByHomeId(@PathVariable String homeId) {
		List<RatingDto> ratingOfHomeByHomeId = ratingService.getRatingOfHomeByHomeId(homeId);
		return new ResponseEntity<>(ratingOfHomeByHomeId, HttpStatus.OK);
	}

	// Get Average Home Rating By HomeId
	@GetMapping("/home/{homeId}")
	public ResponseEntity<Map<String, Double>> getAverageHomeRating(@PathVariable String homeId) {
		Double averageHomeRating = ratingService.getAverageHomeRating(homeId);
		return new ResponseEntity<>(Map.of("AvrRate", averageHomeRating), HttpStatus.OK);
	}

}
