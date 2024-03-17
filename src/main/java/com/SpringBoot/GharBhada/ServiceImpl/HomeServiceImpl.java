package com.SpringBoot.GharBhada.ServiceImpl;

import java.util.Comparator;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.SpringBoot.GharBhada.DAO.HomeDao;
import com.SpringBoot.GharBhada.DTO.HomeDto;
import com.SpringBoot.GharBhada.Entity.Category;
import com.SpringBoot.GharBhada.Entity.Home;
import com.SpringBoot.GharBhada.Entity.Person;
import com.SpringBoot.GharBhada.Exception.NoSpecificHomeOwnership;
import com.SpringBoot.GharBhada.Exception.ResourceNotFoundException;
import com.SpringBoot.GharBhada.ModelMapper.HomeModelMapper;
import com.SpringBoot.GharBhada.Repository.CategoryRepo;
import com.SpringBoot.GharBhada.Repository.PersonRepo;
import com.SpringBoot.GharBhada.Repository.RatingRepo;
import com.SpringBoot.GharBhada.Service.HomeService;
import com.SpringBoot.GharBhada.Utils.FilterHome;

@Component
public class HomeServiceImpl implements HomeService {

	@Autowired
	private HomeDao homeDao;

	@Autowired
	private HomeModelMapper homeModelMapper;

	@Autowired
	private PersonRepo personRepo;

	@Autowired
	private CategoryRepo categoryRepo;

	@Autowired
	private FilterHome filterHome;

	@Autowired
	private RatingRepo ratingRepo;

	// Creation of new Home
	@Override
	public HomeDto createHome(HomeDto homeDto, String personId, Integer categoryId) {

		Person person = personRepo.findById(personId)
				.orElseThrow(() -> new ResourceNotFoundException("Person", personId));
		Category category = categoryRepo.findById(categoryId)
				.orElseThrow(() -> new ResourceNotFoundException("Category", categoryId.toString()));

		Date date = new Date();
		String string = UUID.randomUUID().toString();
		homeDto.setHome_id(string);
		homeDto.setCreatedAt(date);
		homeDto.setUpdatedAt(date);
		Home homeDtoToHome = homeModelMapper.HomeDtoToHome(homeDto);
		Home home = homeDao.createHome(homeDtoToHome, personId, categoryId);
		HomeDto homeToHomeDto = homeModelMapper.HomeToHomeDto(home);
//		 homeToHomeDto.setPerson(personModelMapper.PersonToPersonDto(person));
//		homeToHomeDto.setCategory(categoryModelMapper.CategoryToCategoryDto(category));
		return homeToHomeDto;
	}

	// Update Home
	@Override
	public HomeDto updateHome(HomeDto homeDto, String personId, String homeId) {
		personRepo.findById(personId).orElseThrow(() -> new ResourceNotFoundException("Person", personId));
		homeDto.setUpdatedAt(new Date());
		Home requestHome = homeModelMapper.HomeDtoToHome(homeDto);
		List<Home> homesByPersonId = homeDao.getHomesByPersonId(personId);
		for (Home home : homesByPersonId) {
			if (home.getHome_id().equals(homeId)) {
				Home updateHome = homeDao.updateHome(requestHome, homeId);
				HomeDto homeToHomeDto = homeModelMapper.HomeToHomeDto(updateHome);
				return homeToHomeDto;
			}
		}
		throw new NoSpecificHomeOwnership(personId, homeId);
	}

	// Get single home
	@Override
	public HomeDto getSingleHome(String homeId) {
		Home homeByHomeId = homeDao.getHomeByHomeId(homeId);
		HomeDto homeToHomeDto = homeModelMapper.HomeToHomeDto(homeByHomeId);
		Object averageRating = ratingRepo.getAverageRating(homeToHomeDto.getHome_id());
		if (averageRating != null) {
			homeToHomeDto.setAverageRating((Double) averageRating);
		} else {
			homeToHomeDto.setAverageRating(0.0);
		}
		return homeToHomeDto;
	}
	
	//Delete Home By HomeId
	@Override
	public int deleteHomeByHomeId(String personId, String homeId) {
		personRepo.findById(personId)
		.orElseThrow(() -> new ResourceNotFoundException("Person", personId));
		int delete = homeDao.deleteHome(homeId);
		return delete;
	}


	@Override
	public List<HomeDto> getHomesByPersonId(String personId) {
		List<Home> homesByPersonId = homeDao.getHomesByPersonId(personId);
		List<HomeDto> listOfHomeDto = homesByPersonId.stream().map(home -> homeModelMapper.HomeToHomeDto(home))
				.collect(Collectors.toList());
		List<HomeDto> ListOfHomeDto = addHomeAverageRating(listOfHomeDto);
		return ListOfHomeDto;
	}

	// Get near Homes based on City
	@Override
	public List<HomeDto> getNearHomesOfSelectedHome(double lattitude, double longitude, String districtName) {
		List<Home> homesByDistrict = homeDao.getHomesByDistrict(districtName);
		List<Home> filterHomes = filterHome.getFilterHomes(lattitude, longitude, homesByDistrict);
		List<HomeDto> listOfFilteredHome = filterHomes.stream().map(home -> homeModelMapper.HomeToHomeDto(home))
				.collect(Collectors.toList());
		List<HomeDto> ListOfFilteredHome = addHomeAverageRating(listOfFilteredHome);
		return ListOfFilteredHome;
	}

	// Get near Homes Based on Your Location
	@Override
	public List<HomeDto> getHomesByLocation(double latitude, double longitude) {
		List<Home> homesByLocation = homeDao.getHomesByLocation();
		List<Home> filterHomes = filterHome.getFilterHomes(latitude, longitude, homesByLocation);
		List<HomeDto> listOfHomes = filterHomes.stream().map(home -> homeModelMapper.HomeToHomeDto(home))
				.collect(Collectors.toList());
		List<HomeDto> ListOfHomes = addHomeAverageRating(listOfHomes);
		return ListOfHomes;
	}

	// Get All Homes
	@Override
	public List<HomeDto> allHomes() {
		List<Home> allHomes = homeDao.allHomes();
		List<HomeDto> collectionOfHomes = allHomes.stream().map(home -> homeModelMapper.HomeToHomeDto(home))
				.collect(Collectors.toList());
		List<HomeDto> addHomeAverageRating = addHomeAverageRating(collectionOfHomes);

		return addHomeAverageRating;
	}

	// Get homes by category
	@Override
	public List<HomeDto> getHomeByCategory(Integer categoryId) {
		List<Home> homes = homeDao.getHomesByCategory(categoryId);
		List<HomeDto> collect = homes.stream().map(home -> homeModelMapper.HomeToHomeDto(home))
				.collect(Collectors.toList());
		List<HomeDto> categorizedHomes = addHomeAverageRating(collect);
		return categorizedHomes;
	}

	// Get Top Rated Homes
	@Override
	public List<HomeDto> sortHomesByRating() {
		Map<HomeDto, Double> map = new HashMap<HomeDto, Double>();
		List<Home> allHomes = homeDao.allHomes();
		List<HomeDto> collect = allHomes.stream().map(home -> homeModelMapper.HomeToHomeDto(home))
				.collect(Collectors.toList());
		List<HomeDto> homeDto = addHomeAverageRating(collect);
		for (HomeDto home : homeDto) {
			map.put(home, home.getAverageRating());
		}
		List<HomeDto> topRatedHomeDtos = map.entrySet().stream().sorted(Map.Entry.comparingByValue(Comparator.reverseOrder()))
				.map(home -> home.getKey()).collect(Collectors.toList());
		return topRatedHomeDtos;
	}

	// Filtered based on selected attributes
	@Override
	public List<HomeDto> getFilteredHomes(String sortBy, String sortDir, Integer pageNumber, Integer pageSize) {
		int offsetInteger = (pageNumber - 1) * pageSize;
		List<Home> homePagination = homeDao.getHomePagination(sortBy, sortDir, offsetInteger, pageSize);
		List<HomeDto> listOfFilteredHome = homePagination.stream().map(home -> homeModelMapper.HomeToHomeDto(home))
				.collect(Collectors.toList());
		List<HomeDto> ListOfFilteredHome = addHomeAverageRating(listOfFilteredHome);
		return ListOfFilteredHome;
	}

	// Search home based on query
	@Override
	public List<HomeDto> getHomesByKeyword(String keyword) {
		List<Home> keywoardSearch = homeDao.keywoardSearch(keyword);
		List<HomeDto> listOfKeywordSearchHomeDto = keywoardSearch.stream()
				.map(home -> homeModelMapper.HomeToHomeDto(home)).collect(Collectors.toList());
		List<HomeDto> ListOfKeywordSearchHomeDto = addHomeAverageRating(listOfKeywordSearchHomeDto);
		return ListOfKeywordSearchHomeDto;
	}
	
	//Get all Homes count
	@Override
	public int getAllHomeCount() {
		int homesCount = homeDao.getHomesCount();
		return homesCount;
	}


	private List<HomeDto> addHomeAverageRating(List<HomeDto> homeDto) {
		for (HomeDto homedto : homeDto) {
			Object averageRating = ratingRepo.getAverageRating(homedto.getHome_id());
			if (averageRating != null) {
				homedto.setAverageRating((Double) averageRating);
			} else {
				homedto.setAverageRating(0.0);
			}
		}
		return homeDto;
	}

	

}
