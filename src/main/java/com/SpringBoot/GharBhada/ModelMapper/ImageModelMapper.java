package com.SpringBoot.GharBhada.ModelMapper;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;

import com.SpringBoot.GharBhada.DTO.ImageDto;
import com.SpringBoot.GharBhada.Entity.Image;

public class ImageModelMapper {

	@Autowired
	private ModelMapper modelMapper;
	
	public ImageDto ImageToImageDto(Image image) {
		return modelMapper.map(image, ImageDto.class);
	}
	
	public Image ImageDtoToImage(ImageDto imageDto) {
		return modelMapper.map(imageDto, Image.class);
	}
}
