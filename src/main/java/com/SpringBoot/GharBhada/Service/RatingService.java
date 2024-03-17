package com.SpringBoot.GharBhada.Service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.SpringBoot.GharBhada.DTO.RatingDto;

@Service
public interface RatingService {

	// Create rating
	RatingDto createRating(RatingDto ratingDto, String personId, String homeId);
	
	// Get Individual rating
	double getSingleRating(String personId, String homeId);
	
	//Get ratings by Home Id
	List<RatingDto> getRatingOfHomeByHomeId(String homeId);

	// get average home rating by homeId
	Double getAverageHomeRating(String homeId);

	// Update Rating
	RatingDto updateRating(RatingDto ratingDto, String personId, String homeId);

	// delete Rating
	void deleteRating(String ratingId);

}
