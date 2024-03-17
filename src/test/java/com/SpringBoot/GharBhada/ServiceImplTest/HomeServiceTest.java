//package com.SpringBoot.GharBhada.ServiceImplTest;
//
//import org.junit.jupiter.api.extension.ExtendWith;
//import org.mockito.junit.jupiter.MockitoExtension;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.test.context.SpringBootTest;
//import org.springframework.boot.test.mock.mockito.MockBean;
//
//import com.SpringBoot.GharBhada.DAO.HomeDao;
//import com.SpringBoot.GharBhada.Service.HomeService;
//
//@SpringBootTest
//@ExtendWith(MockitoExtension.class)
//public class HomeServiceTest {
//	
//	@MockBean
//	private HomeService homeService;
//	
//	@Autowired
//	private HomeDao homeDao;
//	
//	
//	@Test
//	public void getHomesByKeyword_Success() {
//	    String keyword = "lake house";
//	    List<Home> expectedHomes = List.of(
//	        new Home("1", "Lakeside Cottage", "Beautiful lakefront home"),
//	        new Home("2", "Lakeview Lodge", "Spacious house with stunning lake views")
//	    );
//
//	    when(homeDao.keywoardSearch(keyword)).thenReturn(expectedHomes);
//
//	    List<HomeDto> foundHomesDto = homeService.getHomesByKeyword(keyword);
//
//	    // Assert the size and content of the returned HomeDtos
//	    assertEquals(2, foundHomesDto.size());
//	    assertTrue(foundHomesDto.stream().allMatch(homeDto -> expectedHomes.stream().anyMatch(home ->
//	        homeDto.getId().equals(home.getId()) &&
//	        homeDto.getName().equals(home.getName())
//	    )));
//
//	    // Assert that the average ratings have been added (assuming addHomeAverageRating works as expected)
//	    // ... (add assertions to verify average ratings)
//	}
//
//	@Test
//	public void getHomesByKeyword_NoResults() {
//	    String keyword = "nonexistent keyword";
//
//	    when(homeDao.keywoardSearch(keyword)).thenReturn(Collections.emptyList());
//
//	    List<HomeDto> foundHomesDto = homeService.getHomesByKeyword(keyword);
//
//	    assertTrue(foundHomesDto.isEmpty());
//	}
//
//	@Test
//	public void getHomesByKeyword_DaoThrowsException() {
//	    String keyword = "trigger-error";
//	    RuntimeException expectedException = new RuntimeException("Something went wrong in the dao");
//
//	    when(homeDao.keywoardSearch(keyword)).thenThrow(expectedException);
//
//	    assertThrows(RuntimeException.class, () -> homeService.getHomesByKeyword(keyword));
//	}
//
//}
