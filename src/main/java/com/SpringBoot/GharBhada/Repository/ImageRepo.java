package com.SpringBoot.GharBhada.Repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import com.SpringBoot.GharBhada.Entity.Image;

@Repository
public interface ImageRepo extends JpaRepository<Image, String> {

	//Get Image List By Home Id
	@Query(value = "select * from image where home_id = :homeId", nativeQuery = true)
	List<Image> findByHomeId(@Param("homeId") String homeId);
}
