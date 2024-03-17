package com.SpringBoot.GharBhada.Controller;

import static org.junit.Assert.assertTrue;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import com.SpringBoot.GharBhada.DTO.PersonDto;
import com.SpringBoot.GharBhada.Entity.Person;
import com.SpringBoot.GharBhada.Service.PersonService;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

@WebMvcTest(controllers = PersonController.class)
@AutoConfigureMockMvc(addFilters = false) // Bypass security filters
@ExtendWith(MockitoExtension.class)
class PersonControllerTest {

	@Autowired
	private MockMvc mockMvc;

	@MockBean
	private PersonService personService;

	private PersonDto personDto;

	@BeforeEach
	void setUp() {
		personDto = new PersonDto("123", "Manoj Jha", "manoj@gmail.com", "abcd123", "9821234234", "india");
	}

	@Test
	public void person_Create_Succes() throws Exception {
		Mockito.when(personService.userCreate(personDto)).thenReturn(personDto);
		
		  mockMvc.perform(MockMvcRequestBuilders.post("/api/person/create")
	                .contentType(MediaType.APPLICATION_JSON)
	                .content(new ObjectMapper().writeValueAsString(personDto)))
	                .andExpect(status().isCreated()); 

	}
	
	@Test
	public void person_Create_Failure() throws Exception {
		PersonDto personDto1 = PersonDto.builder().name("Alex John").address("Tilottama").email(" ").password("john").phone("9812342987").build();
		Mockito.when(personService.userCreate(personDto1)).thenReturn(personDto1);
		
		  MvcResult result = mockMvc.perform(MockMvcRequestBuilders.post("/api/person/create")
	                .contentType(MediaType.APPLICATION_JSON)
	                .content(new ObjectMapper().writeValueAsString(personDto1)))
	                .andExpect(status().isBadRequest()).andReturn(); 
		  
		  String response = result.getResponse().getContentAsString();
		  ObjectMapper objectMapper = new ObjectMapper();
	        Map<String, Object> responseMap = objectMapper.readValue(response, new TypeReference<Map<String, Object>>() {});
	        List<String> emailErrors = (List<String>) responseMap.get("email");
	        assertTrue(emailErrors.contains("Enter valid email."));

	}

}
