package com.SpringBoot.GharBhada.ServiceImpl;

import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import com.SpringBoot.GharBhada.DTO.PersonDto;
import com.SpringBoot.GharBhada.Entity.Person;
import com.SpringBoot.GharBhada.Exception.ResourceNotFoundException;
import com.SpringBoot.GharBhada.Exception.UserAlreadyExist;
import com.SpringBoot.GharBhada.ModelMapper.PersonModelMapper;
import com.SpringBoot.GharBhada.Repository.PersonRepo;
import com.SpringBoot.GharBhada.Service.PersonService;

@Component
public class PeronServiceImpl implements PersonService{
	
	@Autowired
	private PersonRepo personRepo;
	
	@Autowired
	private PersonModelMapper personModelMapper;
	
	@Autowired
	private PasswordEncoder passwordEncoder;

	//Creating Person
	@Override
	public PersonDto userCreate(PersonDto personDto ) {
		
		String email = personDto.getEmail();
		Optional<Person> findByEmail = personRepo.findByEmail(email);
		if(findByEmail.isPresent()) {
			throw new UserAlreadyExist(email);
		}
		
		String randomId = UUID.randomUUID().toString();
		personDto.setPerson_id(randomId);
		personDto.setPassword(passwordEncoder.encode(personDto.getPassword()));
		 Person personRequestToPerson = personModelMapper.PersonDtoToPerson(personDto);
		Person savedPerson = personRepo.save(personRequestToPerson);
		PersonDto personToPersonDto = personModelMapper.personToPersonDto(savedPerson);
		return personToPersonDto;
	}
	
	//Count all users
	@Override
	public int countUsers() {
		int countUsers = personRepo.countUsers();
		return countUsers;
	}


	@Override
	public List<PersonDto> getAllUsers() {
		List<Person> persons = personRepo.findAll();
		List<PersonDto> listOfPersonDto = persons.stream().map(person -> personModelMapper.personToPersonDto(person))
		.collect(Collectors.toList());
		return listOfPersonDto;
	}

	@Override
	public PersonDto getSingleUser(String userId) {
		Optional<Person> findById = personRepo.findById(userId);
		if(findById.isEmpty()) {
			throw new ResourceNotFoundException("User", userId);
		}
		PersonDto personToPersonDto = personModelMapper.personToPersonDto(findById.get());
		return personToPersonDto;
	}
	
	//get person by email address
	@Override
	public PersonDto getByEmailAddress(String email) {
	Optional<Person> person = personRepo.findByEmail(email);
	if(person.isEmpty()) {
		throw new ResourceNotFoundException("email" , email);
	}
	PersonDto personDto = personModelMapper.personToPersonDto(person.get());
		return personDto;
	}

	@Override
	public boolean deleteUser(String userId) {
		Optional<Person> findById = personRepo.findById(userId);
		if(findById.isEmpty()) {
			throw new ResourceNotFoundException("User", userId);
		}
		personRepo.delete(findById.get());
		return true;
	}

	
	


}
