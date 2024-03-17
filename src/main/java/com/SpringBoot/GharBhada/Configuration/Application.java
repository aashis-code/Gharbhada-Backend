package com.SpringBoot.GharBhada.Configuration;

import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.SpringBoot.GharBhada.Algorithm.HaverSine;
import com.SpringBoot.GharBhada.ModelMapper.CategoryModelMapper;
import com.SpringBoot.GharBhada.ModelMapper.CommentModelMapper;
import com.SpringBoot.GharBhada.ModelMapper.HomeModelMapper;
import com.SpringBoot.GharBhada.ModelMapper.ImageModelMapper;
import com.SpringBoot.GharBhada.ModelMapper.PersonModelMapper;
import com.SpringBoot.GharBhada.ModelMapper.RatingModelMapper;
import com.SpringBoot.GharBhada.Utils.FilterHome;

@Configuration
public class Application {

//	@Bean
//	public CustomUserDetailService customUserDetailService() {
//		return new CustomUserDetailService();
//	}

	@Bean
	public ModelMapper modelMapper() {
		return new ModelMapper();
	}

	@Bean
	public PersonModelMapper personModelMapper() {
		return new PersonModelMapper();
	}

	@Bean
	public HomeModelMapper homeModelMapper() {
		return new HomeModelMapper();
	}

	@Bean
	public CategoryModelMapper categoryModelMapper() {
		return new CategoryModelMapper();
	}

	@Bean
	public CommentModelMapper commentModelMapper() {
		return new CommentModelMapper();
	}

	@Bean
	public RatingModelMapper ratingModelMapper() {
		return new RatingModelMapper();
	}

	@Bean
	public ImageModelMapper imageModelMapper() {
		return new ImageModelMapper();
	}

	@Bean
	public HaverSine haverSine() {
		return new HaverSine();
	}

	@Bean
	public FilterHome filterHome() {
		return new FilterHome();
	}
}
