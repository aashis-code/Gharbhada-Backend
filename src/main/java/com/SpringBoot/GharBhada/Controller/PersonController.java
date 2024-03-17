package com.SpringBoot.GharBhada.Controller;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.SpringBoot.GharBhada.DTO.PersonDto;
import com.SpringBoot.GharBhada.Service.PersonService;

import jakarta.validation.Valid;

@RestController
@RequestMapping(path = "/api/person")
public class PersonController {
	
	@Autowired
   private PersonService personService;
	
	//To create new User
	@PostMapping(value =  "/create", consumes = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<PersonDto> createPerson(@RequestBody @Valid PersonDto personDto){
		PersonDto userCreate = personService.userCreate(personDto);
		return new ResponseEntity<PersonDto>(userCreate, HttpStatus.CREATED);
	}
	
	//To get all Users List
	@GetMapping(value = "/", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<PersonDto>> getAllUsers(){
		List<PersonDto> allUsers = personService.getAllUsers();
		return new ResponseEntity<List<PersonDto>>(allUsers, HttpStatus.OK);
	}
	
	//Count all users
	   @Secured("ROLE_ADMIN")
		@GetMapping(value = "/count", produces = MediaType.APPLICATION_JSON_VALUE)
		public ResponseEntity<Map<String, Integer>> countAllUsers(){
			int countUsers = personService.countUsers();
			return new ResponseEntity<>(Map.of("users",countUsers), HttpStatus.OK);
		}
	
	//Get User by UserId
	@GetMapping("/{userId}")
	public ResponseEntity<PersonDto> getSingleUser(@PathVariable String userId){
		PersonDto singleUser = personService.getSingleUser(userId);
		return new ResponseEntity<PersonDto>(singleUser, HttpStatus.FOUND);
	}
	
	//Get Person Info by emailaddress
	@GetMapping("/email/{emailId}")
	public ResponseEntity<PersonDto> getPersonByEmailAddress(@PathVariable String emailId){
		PersonDto personDto = personService.getByEmailAddress(emailId);
		return new ResponseEntity<PersonDto>(personDto, HttpStatus.OK);
	}
	
	//Delete User By UserId
	@DeleteMapping("/{userId}")
	public ResponseEntity<Map<String, String>> deleteUser(@PathVariable String userId){
	    personService.deleteUser(userId);
		return new ResponseEntity<Map<String,String>>(Map.of("message","Successfully deleted !"), HttpStatus.OK);
	}
	
}
