package com.SpringBoot.GharBhada.Service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.SpringBoot.GharBhada.DTO.ImageDto;

@Service
public interface ImageService {

	//Save images 
	List<String> storeImages(List<MultipartFile> files, String homeId);
	
	//Upload Image path
	ImageDto uploadImagePath(ImageDto imageDto, String homeId, String personId);
	
	//Get Image By Image Id
	byte[] getSingleImage(String imageId);
	
	//Obtaining Images Path Based on HomeId
	List<ImageDto> getAllHomesPicturePath(String homeId);
	
}
