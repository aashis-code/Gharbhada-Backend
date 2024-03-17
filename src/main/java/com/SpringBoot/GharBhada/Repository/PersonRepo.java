package com.SpringBoot.GharBhada.Repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.SpringBoot.GharBhada.Entity.Person;

@Repository
public interface PersonRepo extends JpaRepository<Person, String>{
	
	Optional<Person>  findByEmail(String email);
	
	@Query(value =" select * from person where email = :email  and password = :password ", nativeQuery = true)
	Optional<Person>  findByEmailAndPassword(String email, String password);
	
	@Query(value = "select count(person_id) from person", nativeQuery = true)
	int countUsers();

}
