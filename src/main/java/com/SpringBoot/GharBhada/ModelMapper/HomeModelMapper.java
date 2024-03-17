package com.SpringBoot.GharBhada.ModelMapper;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;

import com.SpringBoot.GharBhada.DTO.HomeDto;
import com.SpringBoot.GharBhada.Entity.Home;


public class HomeModelMapper {

	@Autowired
	private ModelMapper modelMapper;
	
	public Home HomeDtoToHome(HomeDto homeDto) {
		return modelMapper.map(homeDto, Home.class);
	}
	
	public HomeDto HomeToHomeDto(Home home) {
		return modelMapper.map(home, HomeDto.class);
	}
	
}

