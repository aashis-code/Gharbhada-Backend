package com.SpringBoot.GharBhada.Service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.SpringBoot.GharBhada.DTO.HomeDto;

@Service
public interface HomeService {

	// Create Home
	HomeDto createHome(HomeDto homeDto, String personId, Integer categoryId);
	
	//update Home
	HomeDto updateHome(HomeDto homeDto, String personId, String homeId);
	
	//Delete Home By HomeId
	int deleteHomeByHomeId(String personId, String homeId);
	
	//Get total Home Count
	int getAllHomeCount();

	// Get single Home
	HomeDto getSingleHome(String homeId);

	// Get Homes by PersonId
	List<HomeDto> getHomesByPersonId(String personId);
	
	//Get near Homes of selected Home
	List<HomeDto> getNearHomesOfSelectedHome(double lattitude, double longitude, String districtName);

	//Get Homes Based on your Location
	List<HomeDto> getHomesByLocation(double latitude, double longitude);
	
	// Get All Home Lists
	List<HomeDto> allHomes();
	
	//Get Homes by category
	List<HomeDto> getHomeByCategory(Integer categoryId);
	
	//Get Top Rated Homes
	List<HomeDto> sortHomesByRating();

	// Implementation of pagination
	List<HomeDto> getFilteredHomes(String sortBy, String sortDir, Integer pageNumber, Integer pageSize);

	// Keyword Based Search Home
	List<HomeDto> getHomesByKeyword(String keyword);

}
