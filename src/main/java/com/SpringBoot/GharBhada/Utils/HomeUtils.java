package com.SpringBoot.GharBhada.Utils;

public interface HomeUtils {

	
	String CREATE_HOME_QUERY = "insert into home(home_id, title, latitude, longitude, "
			+ "created_at,updated_at, description,person_id, "
			+ "category_id, city, district,price,area,bedroom,baths,amenities,rented) "
            + "values (?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
	
	String UPDATE_HOME_QUERY  = "UPDATE home " +
	        "SET title = ?, updated_at = ?, description = ?, price = ?, rented = ?, amenities = ?" +
	        "WHERE home_id = ?";

	
	String GET_HOME_BY_ID = "select * from home h\r\n"
			+ "left join person p on h.person_id = p.person_id\r\n"
			+ "left join category c on h.category_id = c.category_id\r\n"
			+ "left join image i on h.home_id = i.home_id\r\n"
			+ "where h.home_id = ? ";
	
	String DELETE_HOME_BY_HOMEID = "delete from home where home_id = ?";
	
	String GET_BY_PERSON_ID = "select *\r\n"
			+ "from home h\r\n"
			+ "left join person p on h.person_id = p.person_id\r\n"
			+ "left join category c on h.category_id = c.category_id\r\n"
			+ "left join image i on h.home_id = i.home_id\r\n"
			+ "where p.person_id = ?";
	
 String GET_ALL_HOMES = "select *\r\n"
			+ "from home h\r\n"
			+ "left join person p on h.person_id = p.person_id\r\n"
			+ "left join category c on h.category_id = c.category_id\r\n"
			+ "left join image i on h.home_id = i.home_id\r\n";
	
	String GET_HOMES_BY_CATEGORY =  "select *\r\n"
			+ "from home h\r\n"
			+ "left join person p on h.person_id = p.person_id\r\n"
			+ "left join category c on h.category_id = c.category_id\r\n"
			+ "left join image i on h.home_id = i.home_id\r\n"
			+ "where c.category_id = ?";
	
	String SEARCH_HOME ="select *\r\n"
			+ "from home h\r\n"
			+ "left join person p on h.person_id = p.person_id\r\n"
			+ "left join category c on h.category_id = c.category_id\r\n"
			+ "order by ? ?\r\n"
			+ "limit ? offset ?";
	
	String GET_HOMES_BY_DISTRICT = "select *\r\n"
			+ "from home h\r\n"
			+ "left join person p on h.person_id = p.person_id\r\n"
			+ "left join category c on h.category_id = c.category_id\r\n"
			+ "left join image i on h.home_id = i.home_id\r\n"
			+ "where district = ?";
	
	String GET_HOMES_EXCEPT_RENTED = "select *\r\n"
			+ "from home h\r\n"
			+ "left join person p on h.person_id = p.person_id\r\n"
			+ "left join category c on h.category_id = c.category_id\r\n"
	        + "left join image i on h.home_id = i.home_id\r\n";
	
	String GET_HOMES_COUNT = "select count(*) from home";
	
	String SEARCH_KEYWORD = " select * from home h\r\n"
			+ "left join person p on h.person_id = p.person_id\r\n"
			+ "left join category c on h.category_id = c.category_id\r\n"
			+ "where title like concat('%',?,'%')\r\n"
			+ "union \r\n"
			+ "select *\r\n"
			+ "from home h\r\n"
			+ "left join person p on h.person_id = p.person_id\r\n"
			+ "left join category c on h.category_id = c.category_id\r\n"
			+ "where district like concat('%',?,'%')\r\n"
			+ "union\r\n"
			+ "select *\r\n"
			+ "from home h\r\n"
			+ "left join person p on h.person_id = p.person_id\r\n"
			+ "left join category c on h.category_id = c.category_id\r\n"
			+ "where city like concat('%',?,'%')\r\n"
			+ "union\r\n"
			+ "select *\r\n"
			+ "from home h\r\n"
			+ "left join person p on h.person_id = p.person_id\r\n"
			+ "left join category c on h.category_id = c.category_id\r\n"
			+ "where description like concat('%',?,'%')";
}
