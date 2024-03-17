package com.SpringBoot.GharBhada.ServiceImpl;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.SpringBoot.GharBhada.DTO.HomeDto;
import com.SpringBoot.GharBhada.DTO.PersonDto;
import com.SpringBoot.GharBhada.DTO.RatingDto;
import com.SpringBoot.GharBhada.Entity.Rating;
import com.SpringBoot.GharBhada.ModelMapper.HomeModelMapper;
import com.SpringBoot.GharBhada.ModelMapper.PersonModelMapper;
import com.SpringBoot.GharBhada.ModelMapper.RatingModelMapper;
import com.SpringBoot.GharBhada.Repository.RatingRepo;
import com.SpringBoot.GharBhada.Service.HomeService;
import com.SpringBoot.GharBhada.Service.PersonService;
import com.SpringBoot.GharBhada.Service.RatingService;

@Component
public class RatingImpl implements RatingService {

	@Autowired
	private RatingRepo ratingRepo;

	@Autowired
	private RatingModelMapper ratingModelMapper;

	@Autowired
	private HomeModelMapper homeModelMapper;

	@Autowired
	private PersonModelMapper personModelMapper;

	@Autowired
	private HomeService homeService;

	@Autowired
	private PersonService personService;

	// Create Rating By PersonId and HomeId
	@Override
	public RatingDto createRating(RatingDto ratingDto, String personId, String homeId) {
		String randomId = UUID.randomUUID().toString();
		ratingDto.setRating_id(randomId);
		PersonDto user = personService.getSingleUser(personId);
		HomeDto home = homeService.getSingleHome(homeId);

		Rating ratingDtoToRating = ratingModelMapper.RatingDtoToRating(ratingDto);
		ratingDtoToRating.setPerson(personModelMapper.PersonDtoToPerson(user));
		ratingDtoToRating.setHome(homeModelMapper.HomeDtoToHome(home));
		Rating save = ratingRepo.save(ratingDtoToRating);
		RatingDto ratingToRatingDto = ratingModelMapper.RatingToRatingDto(save);
		return ratingToRatingDto;
	}
	
	@Override
	public double getSingleRating(String personId, String homeId) {
		Double findSpecificRating = ratingRepo.findSpecificRating(personId, homeId);
		if(findSpecificRating != null) {
			return findSpecificRating;
		}
		return -1.0;
	}

	// Get Rating of Home by HomeId
	@Override
	public List<RatingDto> getRatingOfHomeByHomeId(String homeId) {
		List<Rating> findByHomeId = ratingRepo.findByHomeId(homeId);
		List<RatingDto> ratingDtos = findByHomeId.stream().map(home -> ratingModelMapper.RatingToRatingDto(home))
				.collect(Collectors.toList());
		return ratingDtos;
	}

	@Override
	public RatingDto updateRating(RatingDto ratingDto, String personId, String homeId) {
		// TODO Auto-generated method stub
		return null;
	}

	
	
	@Override
	public void deleteRating(String ratingId) {
		// TODO Auto-generated method stub

	}

	// Get Average rating of home
	@Override
	public Double getAverageHomeRating(String homeId) {
		Double averageRating = ratingRepo.getAverageRating(homeId);
		return averageRating;
	}

}
