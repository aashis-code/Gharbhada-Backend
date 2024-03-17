package com.SpringBoot.GharBhada.DAO;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import com.SpringBoot.GharBhada.Entity.Home;
import com.SpringBoot.GharBhada.Exception.ResourceNotFoundException;
import com.SpringBoot.GharBhada.Utils.HomeUtils;

@Repository
public class HomeDaoImpl implements HomeDao, HomeUtils {

	@Autowired
	private JdbcTemplate jdbcTemplate;

	@Override
	public Home createHome(Home home, String personId, Integer categoryId) {
		String home_id = home.getHome_id();
		String title = home.getTitle();
		double latitude = home.getLatitude();
		double longitude = home.getLongitude();
		Date createdAt = home.getCreatedAt();
		Date updatedAt = home.getUpdatedAt();
		String description = home.getDescription();
		String city = home.getCity();
		String district = home.getDistrict();
		double price = home.getPrice();
		double area = home.getArea();
		long bedroom = home.getBedroom();
		long baths = home.getBaths();
		String amenities = home.getAmenities();
		boolean rented = false;
		String person_id = personId;
		int category_id = categoryId;

		this.jdbcTemplate.update(CREATE_HOME_QUERY, home_id, title, latitude, longitude, createdAt, updatedAt,
				description, person_id, category_id, city, district, price, area, bedroom, baths, amenities, rented);
		return home;
	}

	// Get Single Home By HomeId
	@Override
	public Home getHomeByHomeId(String homeId) {
		RowMapper<Home> rowMapper = new RowMapperImpl();
		try {
			Home queryForObject = this.jdbcTemplate.queryForObject(GET_HOME_BY_ID, rowMapper, homeId);
			return queryForObject;
		} catch (DataAccessException e) {
			System.out.println(e.getLocalizedMessage());
			throw new ResourceNotFoundException("Home", homeId);
		}

	}

	// Delete Home By HomeId and PersonId
	@Override
	public int deleteHome(String homeId) {
		int delete = jdbcTemplate.update(DELETE_HOME_BY_HOMEID, homeId);
		return delete;
	}

	// Get Homes By PersonId
	@Override
	public List<Home> getHomesByPersonId(String personId) {
		RowMapper<Home> rowMapper = new RowMapperImpl();
		List<Home> ListOfHome = this.jdbcTemplate.query(GET_BY_PERSON_ID, rowMapper, personId);
		return ListOfHome;
	}

	// Get All Homes
	@Override
	public List<Home> allHomes() {
		RowMapper<Home> rowMapper = new RowMapperImpl();
		List<Home> listOfHome = this.jdbcTemplate.query(GET_ALL_HOMES, rowMapper);
		return listOfHome;
	}

	// Get Homes by category
	@Override
	public List<Home> getHomesByCategory(Integer categoryId) {
		RowMapper<Home> rowMapper = new RowMapperImpl();
		List<Home> categorizedHomes = this.jdbcTemplate.query(GET_HOMES_BY_CATEGORY, rowMapper, categoryId);
		return categorizedHomes;
	}

	// Implementation of pagination
	@Override
	public List<Home> getHomePagination(String sortBy, String sortDir, Integer offsetInteger, Integer pageSize) {
		RowMapper<Home> rowMapper = new RowMapperImpl();
		List<Home> list = this.jdbcTemplate.query(SEARCH_HOME, rowMapper,
				new Object[] { sortBy, sortDir, pageSize, offsetInteger });

		return list;
	}

	// Search home based on keyword
	@Override
	public List<Home> keywoardSearch(String keyword) {
		RowMapper<Home> rowMapper = new RowMapperImpl();
		List<Home> listOfHome = this.jdbcTemplate.query(SEARCH_KEYWORD, rowMapper,
				new Object[] { keyword, keyword, keyword, keyword });
		return listOfHome;
	}

	// Get near homes on selected district
	@Override
	public List<Home> getHomesByDistrict(String districtName) {
		RowMapper<Home> rowMapper = new RowMapperImpl();
		List<Home> listOfFilteredHome = this.jdbcTemplate.query(GET_HOMES_BY_DISTRICT, rowMapper, districtName);
		return listOfFilteredHome;
	}

	// Get homes excluding rented home
	@Override
	public List<Home> getHomesByLocation() {
		RowMapper<Home> rowMapper = new RowMapperImpl();
		List<Home> listOfFilteredHome = this.jdbcTemplate.query(GET_HOMES_EXCEPT_RENTED, rowMapper);
		return listOfFilteredHome;
	}

	// Update Home status
	@Override
	public Home updateHome(Home home, String homeId) {
		String home_id = homeId;
		String title = home.getTitle();
		Date updatedAt = home.getUpdatedAt();
		String description = home.getDescription();
		String amenities = home.getAmenities();
		double price = home.getPrice();
		boolean rented = home.isRented();

		this.jdbcTemplate.update(UPDATE_HOME_QUERY, title, updatedAt, description, price, rented, amenities, home_id);
		return home;
	}

	//Get Home Count
	@Override
	public int getHomesCount() {
		Integer value = this.jdbcTemplate.queryForObject(GET_HOMES_COUNT, Integer.class);
		return value;
	}

}
