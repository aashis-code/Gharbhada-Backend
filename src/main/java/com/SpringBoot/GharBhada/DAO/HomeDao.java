package com.SpringBoot.GharBhada.DAO;

import java.util.List;

import com.SpringBoot.GharBhada.Entity.Home;

public interface HomeDao {
	
	//Create Home Information
	Home createHome(Home home, String personId, Integer categoryId);
	
	//Get Single Home BY HomeId
	Home getHomeByHomeId(String homeId);
	
	//Delete Home By HomeId and PersonId
	int deleteHome(String homeId);
	
	//Get Homes by Category
	List<Home> getHomesByCategory(Integer categoryId);
	
	//update home info
	Home updateHome(Home home, String homeId);
	
	//Get near Homes based on City
	List<Home> getHomesByDistrict(String districtName);
	
	//Get near Homes based on your location
	List<Home> getHomesByLocation();
	
	//Get Homes Count
	int getHomesCount();
	
	
	//Get All Homes By PersonId
	List<Home> getHomesByPersonId(String personId);
	
	//Get All Home List
	List<Home> allHomes();
	
	//Get using Pagination
	List<Home> getHomePagination(String sortBy, String sortDir, Integer offsetInteger, Integer pageSize);
	
	//Get Home based on Keyword search
	List<Home> keywoardSearch(String keyword);

}
