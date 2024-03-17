package com.SpringBoot.GharBhada.Repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.SpringBoot.GharBhada.Entity.Rating;

public interface RatingRepo extends JpaRepository<Rating, String>{
	
	//Get AverageRating
	@Query(value = "SELECT AVG(rating_value) from rating where home_id = :homeId", nativeQuery = true)
	public Double getAverageRating(@Param("homeId") String homeId);
	
	@Query(value = "SELECT * from rating where home_id = :homeId", nativeQuery = true)
	public List<Rating> findByHomeId(String homeId);
	
	@Query(value = "select rating_value from rating where  person_id = :personId and home_id = :homeId", nativeQuery = true)
	public Double findSpecificRating(String personId, String homeId); 

}
