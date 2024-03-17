package com.SpringBoot.GharBhada.Service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.SpringBoot.GharBhada.DTO.PersonDto;

@Service
public interface PersonService {

	//User Creation
	 PersonDto userCreate(PersonDto personDto);
	 
	 //Count total users
	 int countUsers();
	 
	 //Get All Users
	List<PersonDto> getAllUsers();
	
	//Get By email
	PersonDto getByEmailAddress(String email);
	 
	 //Get single user
	 PersonDto getSingleUser(String userId);
	 
	 //Delete User
	 boolean deleteUser(String userId);
	 
	 
}
