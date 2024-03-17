package com.SpringBoot.GharBhada.Controller;

import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import com.SpringBoot.GharBhada.DTO.HomeDto;
import com.SpringBoot.GharBhada.Service.HomeService;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

@WebMvcTest(controllers = HomeController.class)
@AutoConfigureMockMvc(addFilters = false) // Bypass security filters
@ExtendWith(MockitoExtension.class)
public class HomeControllerTest {

	@MockBean
	private HomeService homeService;

	@Autowired
	private MockMvc mockMvc;

	// Home Creation success
	@Test
	void home_Create_Success() throws Exception {
		HomeDto homeDto = HomeDto.builder().title("Test Home from unit testing.")
				.description("Test Home from unit testing.Test Home from unit testing.Test Home from unit testing.")
				.amenities("Test Home from unit testing.").price(1000).latitude(80.0).longitude(80.0).area(234.5).baths(2).bedroom(2)
				.district("RUpandehi").city("Butwal").build();

		when(homeService.createHome(homeDto, "1", 1)).thenReturn(homeDto);

		// Perform the request
		mockMvc.perform(MockMvcRequestBuilders.post("/api/home/person/1/category/1")
				.contentType(MediaType.APPLICATION_JSON).content(new ObjectMapper().writeValueAsString(homeDto)))
				.andExpect(status().isCreated());
	}

	// Home Creation failure
	@Test
	void home_Create_Failure() throws Exception {
		HomeDto homeDto = HomeDto.builder().title("Test Home from unit testing.")
				.description("Test Home from unit testing.Test Home from unit testing.Test Home from unit testing.")
				.amenities("Test Home from unit testing.").latitude(80).longitude(80).area(234).baths(2).bedroom(2)
				.district(" ").city("Butwal").build();

		when(homeService.createHome(homeDto, "1", 1)).thenReturn(homeDto);

		// Perform the request
		MvcResult result = mockMvc.perform(MockMvcRequestBuilders.post("/api/home/person/1/category/1")
				.contentType(MediaType.APPLICATION_JSON).content(new ObjectMapper().writeValueAsString(homeDto)))
				.andExpect(status().isBadRequest()).andReturn();

		String errorMessage = result.getResponse().getContentAsString();
		ObjectMapper objectMapper = new ObjectMapper();
		Map<String, Object> responseMap = objectMapper.readValue(errorMessage,
				new TypeReference<Map<String, Object>>() {
				});
		List<String> districtErrors = (List<String>) responseMap.get("district");
		assertTrue(districtErrors.contains("District can't be blank."));
	}

	// Search by Keyword Succes
	@Test
	public void search_By_Keyword_Succes() throws Exception {

		HomeDto homeDto1 = HomeDto.builder().title("Test Home from unit testing.")
				.description("Test Home from unit testing.Test Home from unit testing.Test Home from unit testing.")
				.amenities("Test Home from unit testing.").latitude(80).longitude(80).area(234).baths(2).bedroom(2)
				.district("Rupandehi").city("Butwal").build();

		HomeDto homeDto2 = HomeDto.builder().title("Test Home from unit testing.")
				.description("Test Home from unit testing.Test Home from unit testing.Test Home from unit testing.")
				.amenities("Test Home from unit testing.").latitude(80).longitude(80).area(234).baths(2).bedroom(2)
				.district("Rupandehi").city("Butwal").build();

		List<HomeDto> homeDtos = new ArrayList<>();
		homeDtos.addAll(Arrays.asList(homeDto1, homeDto2));

		when(homeService.getHomesByKeyword("Test")).thenReturn(homeDtos);

		mockMvc.perform(MockMvcRequestBuilders.get("/api/home/search/Test")).andExpect(status().isOk());
	}

	// Search by Keyword Failure
	@Test
	public void search_By_Keyword_Failure() throws Exception {

		HomeDto homeDto1 = HomeDto.builder().title("Test Home from unit testing.")
				.description("Test Home from unit testing.Test Home from unit testing.Test Home from unit testing.")
				.amenities("Test Home from unit testing.").latitude(80).longitude(80).area(234).baths(2).bedroom(2)
				.district("Rupandehi").city("Butwal").build();

		HomeDto homeDto2 = HomeDto.builder().title("Test Home from unit testing.")
				.description("Test Home from unit testing.Test Home from unit testing.Test Home from unit testing.")
				.amenities("Test Home from unit testing.").latitude(80).longitude(80).area(234).baths(2).bedroom(2)
				.district("Rupandehi").city("Butwal").build();

		List<HomeDto> homeDtos = new ArrayList<>();
		homeDtos.addAll(Arrays.asList(homeDto1, homeDto2));

		when(homeService.getHomesByKeyword("Test")).thenReturn(homeDtos);

		mockMvc.perform(MockMvcRequestBuilders.get("/api/home/search/notPresentKeyword"))
				.andExpect(jsonPath("$").isArray()).andExpect(jsonPath("$").isEmpty());
	}

}
