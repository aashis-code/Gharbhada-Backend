package com.SpringBoot.GharBhada.DAO;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.jdbc.core.RowMapper;

import com.SpringBoot.GharBhada.Entity.Category;
import com.SpringBoot.GharBhada.Entity.Home;
import com.SpringBoot.GharBhada.Entity.Image;
import com.SpringBoot.GharBhada.Entity.Person;

public class RowMapperImpl implements RowMapper<Home> {

	@Override
	public Home mapRow(ResultSet rs, int rowNum) throws SQLException {
		Home home = new Home();

		home.setHome_id(rs.getString("home_id"));
		home.setTitle(rs.getString("title"));
		home.setLatitude(rs.getDouble("latitude"));
		home.setLongitude(rs.getDouble("longitude"));
		home.setDescription(rs.getString("description"));
		home.setCreatedAt(rs.getDate("created_at"));
		home.setUpdatedAt(rs.getDate("updated_at"));
		home.setCity(rs.getString("city"));
		home.setDistrict(rs.getString("district"));
		home.setPrice(rs.getDouble("price"));
		home.setRented(rs.getBoolean("rented"));
		home.setArea(rs.getDouble("area"));
		home.setBaths(rs.getLong("baths"));
		home.setBedroom(rs.getLong("bedroom"));
		home.setAmenities(rs.getString("amenities"));

		Person person = new Person();
		person.setPerson_id(rs.getString("person_id"));
		person.setEmail(rs.getString("email"));
		person.setName(rs.getString("user_name"));
		person.setPhone(rs.getString("phone_num"));
		person.setAddress(rs.getString("address"));
		home.setPerson(person);

//		Category category = home.getCategory(); --> Throw Null Pointer Exception
		Category category = new Category();
		category.setCategory_id(rs.getInt("category_id"));
		category.setCategoryName(rs.getString("category_name") );
		home.setCategory(category);

		// Map image
//		List<Image> images = new ArrayList<>();
//		do {
//			Image image = new Image();
//			image.setImage_id(rs.getString("image_id"));
//			image.setImage_path(rs.getString("image_path"));
//			images.add(image);
//		} while (rs.next() && rs.getString("home_id").equals(home.getHome_id()));
//	
//		
//		home.setImages(images);

		return home;
	}

}
