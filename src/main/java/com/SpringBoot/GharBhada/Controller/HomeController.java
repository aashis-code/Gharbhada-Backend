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
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.SpringBoot.GharBhada.DTO.HomeDto;
import com.SpringBoot.GharBhada.Service.HomeService;
import com.SpringBoot.GharBhada.Utils.PaginationConstant;

import jakarta.validation.Valid;

@RestController
@RequestMapping(value = "/api/home")
public class HomeController implements PaginationConstant {

	@Autowired
	private HomeService homeService;

	// Creation of Home
	@PostMapping("/person/{personId}/category/{categoryId}")
	public ResponseEntity<HomeDto> createHome(@PathVariable String personId, @PathVariable Integer categoryId,
			@RequestBody @Valid HomeDto homeDto) {
		HomeDto createHome = homeService.createHome(homeDto, personId, categoryId);
		return new ResponseEntity<HomeDto>(createHome, HttpStatus.CREATED);
	}

	// Update Home
	@PutMapping("person/{personId}/home/{homeId}")
	public ResponseEntity<HomeDto> updateHome(@RequestBody @Valid HomeDto homeDto,@PathVariable String personId,@PathVariable String homeId) {
		HomeDto updateHome = homeService.updateHome(homeDto, personId, homeId);
		return new ResponseEntity<HomeDto>(updateHome, HttpStatus.OK);
	}

	// Get Single Home
	@GetMapping("/{homeId}")
	public ResponseEntity<HomeDto> getSingleHome(@PathVariable String homeId) {
		HomeDto singleHome = homeService.getSingleHome(homeId);
		return new ResponseEntity<HomeDto>(singleHome, HttpStatus.OK);
	}
	
	//Delete Home By HomeId
	@DeleteMapping("/{homeId}/person/{personId}")
	public ResponseEntity<Map<String, String>> deleteHome(@PathVariable String homeId, @PathVariable String personId){
		homeService.deleteHomeByHomeId(personId, homeId);
		return new ResponseEntity<>(Map.of("message","Home successfully deleted"), HttpStatus.OK);
	}

	// Get Homes By PersonId
	@GetMapping("/person/{personId}")
	public ResponseEntity<List<HomeDto>> getHomesByPersonId(@PathVariable String personId) {
		List<HomeDto> homesByPersonId = homeService.getHomesByPersonId(personId);
		return new ResponseEntity<>(homesByPersonId, HttpStatus.OK);
	}

	// Get All available Homes
	@GetMapping(value = "/", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<HomeDto>> getAllHomes() {
		List<HomeDto> allHomes = homeService.allHomes();
		return new ResponseEntity<List<HomeDto>>(allHomes, HttpStatus.OK);
	}
	
	//Get all Homes count
	@GetMapping(value = "/count")
	public ResponseEntity<Map<String, Integer>> getAllHomeCount(){
		int allHomeCount = homeService.getAllHomeCount();
		return new ResponseEntity<Map<String,Integer>>(Map.of("count",allHomeCount), HttpStatus.OK) ;
	}
	
	//Get All Homes by category
	@GetMapping(value = "/category/{categoryId}", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<HomeDto>> getAllHomes(@PathVariable Integer categoryId) {
		List<HomeDto> categorizedHomes = homeService.getHomeByCategory(categoryId);
		return new ResponseEntity<List<HomeDto>>(categorizedHomes, HttpStatus.OK);
	}
	
	//Get Sorted homes based on rating
	@GetMapping(value = "/sortby/rating")
	public ResponseEntity<List<HomeDto>> getSortHomesByRating(){
		List<HomeDto> sortHomesByRating = homeService.sortHomesByRating();
		return new ResponseEntity<List<HomeDto>>(sortHomesByRating, HttpStatus.OK);
	}

	// Get list of home based on selected attribute
	@GetMapping("/pagination")
	public ResponseEntity<List<HomeDto>> getPaginatedHome(
			@RequestParam(value = "sortBy", defaultValue = SORT_BY, required = false) String sortBy,
			@RequestParam(value = "sortDir", defaultValue = SORT_DIR, required = false) String sortDir,
			@RequestParam(value = "pageNum", defaultValue = PAGE_NUM, required = false) Integer pageNum,
			@RequestParam(value = "pageSize", defaultValue = PAGE_SIZE, required = false) Integer pagSize) {
		List<HomeDto> filteredHomes = homeService.getFilteredHomes(sortBy, sortDir, pageNum, pagSize);
		return new ResponseEntity<List<HomeDto>>(filteredHomes, HttpStatus.OK);
	}

	// Search Home Based on Keyword
	@GetMapping("/search/{keyword}")
	public ResponseEntity<List<HomeDto>> searchByKeyword(@PathVariable String keyword) {
		List<HomeDto> homesByKeyword = homeService.getHomesByKeyword(keyword);
		return new ResponseEntity<List<HomeDto>>(homesByKeyword, HttpStatus.OK);
	}

	// Get Near Homes By District Location
	@GetMapping("/filter")
	public ResponseEntity<List<HomeDto>> getNearHomesByDistrict(@RequestParam(value = "latitude") double latitude,
			@RequestParam(value = "longitude") double longitude, @RequestParam(value = "district") String district) {
		List<HomeDto> nearHomesOfSelectedHome = homeService.getNearHomesOfSelectedHome(latitude, longitude, district);
		return new ResponseEntity<List<HomeDto>>(nearHomesOfSelectedHome, HttpStatus.OK);
	}

	// Get Homes By Your Current Location
	@GetMapping("/filter/location")
	public ResponseEntity<List<HomeDto>> getNearHomesByLocation(@RequestParam(value = "latitude") double latitude,
			@RequestParam(value = "longitude") double longitude) {
		List<HomeDto> homesByLocation = homeService.getHomesByLocation(latitude, longitude);
		return new ResponseEntity<List<HomeDto>>(homesByLocation, HttpStatus.OK);
	}

}
