package com.SpringBoot.GharBhada.ModelMapper;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;

import com.SpringBoot.GharBhada.DTO.RatingDto;
import com.SpringBoot.GharBhada.Entity.Rating;

public class RatingModelMapper {
	
	@Autowired
	private ModelMapper modelMapper;
	
	public RatingDto RatingToRatingDto(Rating rating) {
		return modelMapper.map(rating, RatingDto.class);
	}
	
	public Rating RatingDtoToRating(RatingDto ratingDto) {
		return modelMapper.map(ratingDto, Rating.class);
	}

}
