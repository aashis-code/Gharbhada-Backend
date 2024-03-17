package com.SpringBoot.GharBhada.ServiceImplTest;

import static org.junit.Assert.assertTrue;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import com.SpringBoot.GharBhada.DTO.PersonDto;
import com.SpringBoot.GharBhada.Entity.Person;
import com.SpringBoot.GharBhada.Exception.ResourceNotFoundException;
import com.SpringBoot.GharBhada.Exception.UserAlreadyExist;
import com.SpringBoot.GharBhada.ModelMapper.PersonModelMapper;
import com.SpringBoot.GharBhada.Repository.PersonRepo;
import com.SpringBoot.GharBhada.Service.PersonService;

@SpringBootTest
@ExtendWith(MockitoExtension.class)
public class PersonServiceTest {

	@Autowired
	private PersonService personService;

	@Autowired
	private PersonModelMapper personModelMapper;

	@MockBean
	private PersonRepo personRepo;
	
	//User creation SUCCESS
	@Test 
	public void createUser() {
		Person person = Person.builder().name("John Labron").password("John123").phone("9812453").email("john@gmail.com")
				.address("Butwal").build();
		
		when(personRepo.findByEmail("john@gmail.com")).thenReturn(Optional.empty());
		when(personRepo.save(Mockito.any(Person.class))).thenReturn(person);
		
		PersonDto personDroCreate = personService.userCreate(personModelMapper.personToPersonDto(person));
		assertEquals("John Labron", personDroCreate.getName());
		assertEquals("john@gmail.com", personDroCreate.getEmail());
	
	}
	
	// User creation FAILURE
	@Test
	public void userAlreadyExists() {
		Person person = Person.builder().name("John Labron").password("John123").phone("9812453").email("john@gmail.com")
				.address("Butwal").build();
		
		when(personRepo.findByEmail("john@gmail.com")).thenReturn(Optional.of(person));
		try {
			personService.userCreate(personModelMapper.personToPersonDto(person));
		}catch (UserAlreadyExist e) {
			assertEquals("john@gmail.com already exist.", e.getMessage());
		}	
	}

	//Get user by Id
	@Test
	public void getSingleUserTest() {
		Person person = Person.builder().person_id("1").name("John").phone("9812453").email("john@gmail.com")
				.address("Butwal").build();

		when(personRepo.findById("1")).thenReturn(Optional.ofNullable(person));

		PersonDto byEmailAddress = personService.getSingleUser("1");

		Assertions.assertThat(byEmailAddress).isNotNull();
	}

	//Get all user List
	@Test
	public void getAllUsersTest() {
		List<Person> persons = new ArrayList<>();
		Person person1 = Person.builder().person_id("1").name("John").phone("9812453").email("john@gmail.com")
				.address("Butwal").build();
		Person person2 = Person.builder().person_id("2").name("Alex").phone("9812445").email("alex@gmail.com")
				.address("Pokhara").build();
		persons.add(person1);
		persons.add(person2);
		when(personRepo.findAll()).thenReturn(persons);

		List<PersonDto> allUsers = personService.getAllUsers();

		assertEquals(2, allUsers.size());
		assertEquals("1", (personModelMapper.personToPersonDto(persons.get(0))).getPerson_id());
		assertEquals("2", (personModelMapper.personToPersonDto(persons.get(1))).getPerson_id());

	}

	//Get User By Email Address
	@Test
	public void getUserByEmailAddressTest() {
		Person person = Person.builder().person_id("1").name("John").phone("9812453").email("john@gmail.com")
				.address("Butwal").build();

		when(personRepo.findByEmail("john@gmail.com")).thenReturn(Optional.ofNullable(person));

		PersonDto byEmailAddress = personService.getByEmailAddress("john@gmail.com");

		Assertions.assertThat(byEmailAddress).isNotNull();
	}

	@Test
	public void getUserByEmailAddressNotFoundTest() {

		String email = "nonexistent@email.com"; // Use an email that doesn't exist

		when(personRepo.findByEmail(email)).thenReturn(Optional.empty());

		ResourceNotFoundException thrown = assertThrows(ResourceNotFoundException.class,
				() -> personService.getByEmailAddress(email));

		assertEquals("nonexistent@email.com", thrown.getFieldValue());
	}

	@Test
	public void deleteUserSuccess() {
		String userId = "1";
		Person person = Person.builder().person_id("1").name("John").phone("9812453").email("john@gmail.com")
				.address("Butwal").build();

		when(personRepo.findById(userId)).thenReturn(Optional.of(person));

		boolean isDeleted = personService.deleteUser(userId);

		assertTrue(isDeleted);
		verify(personRepo).delete(person);
	}

	@Test
	public void deleteUserFailure() {
		String userId = "nonexistent";

		when(personRepo.findById(userId)).thenReturn(Optional.empty());

		ResourceNotFoundException thrown = assertThrows(ResourceNotFoundException.class,
				() -> personService.deleteUser(userId));

		assertTrue(thrown.getMessage().contains("User"));
		assertTrue(thrown.getMessage().contains(userId));
	}

}
