package com.SpringBoot.GharBhada.Controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.SpringBoot.GharBhada.DTO.ImageDto;
import com.SpringBoot.GharBhada.Service.ImageService;
import com.SpringBoot.GharBhada.Utils.FileResponse;

@RestController
@RequestMapping(value = "/api/image")
public class ImageController {

	private Logger logger = LoggerFactory.getLogger(ImageController.class);

	@Autowired
	private ImageService imageService;
	
	
	//Upload Image Path
	@PostMapping("/home/{homeId}/user/{userId}/upload")
	public ResponseEntity<ImageDto> uploadImagePath(@RequestBody ImageDto imageDto, @PathVariable String homeId, @PathVariable String userId){
		ImageDto uploadImagePath = imageService.uploadImagePath(imageDto, homeId, userId);
		return new ResponseEntity<ImageDto>(uploadImagePath, HttpStatus.CREATED);
	}

	// Post request for Image Uploading
	@PostMapping("/upload")
	public ResponseEntity<FileResponse> uploadImage(@RequestParam("homeId") String homeId,
			@RequestParam("image") List<MultipartFile> files) {
		logger.info("{} is the length.", files.size());
		List<String> storeImages = imageService.storeImages(files, homeId);
		return new ResponseEntity<FileResponse>(new FileResponse(storeImages, "Images has been uploaded."),
				HttpStatus.CREATED);
	}

	// Get List of Image URLs By HomeId
	@GetMapping("/home/{homeId}/images")
	public ResponseEntity<List<ImageDto>> getImagesByHomeId(@PathVariable String homeId) {
		List<ImageDto> allHomesPicturePath = imageService.getAllHomesPicturePath(homeId);
		return new ResponseEntity<>(allHomesPicturePath, HttpStatus.OK);
	}

	// Get Images by homeId
	@GetMapping("/{imageId}")
	public ResponseEntity<byte[]> getImages(@PathVariable String imageId) {

		byte[] singleImage = imageService.getSingleImage(imageId);

		if (singleImage != null) {
			// Set response headers
			HttpHeaders headers = new HttpHeaders();
			headers.setContentType(MediaType.IMAGE_JPEG);
			return new ResponseEntity<>(singleImage, headers, HttpStatus.OK);
		}

		return new ResponseEntity<byte[]>(HttpStatus.NOT_FOUND);
	}

}

//@GetMapping("/{homeId}")
//public ResponseEntity<List<ResponseEntity<byte[]>>> getImages(@PathVariable String homeId){
//	
//    // Construct the file path
//    String[] ImagePaths = { "images/3fefdf29-d57a-4bc7-9d24-5a7a108de9af.png", "images/708f7503-c805-48da-a861-afa803e898d8.png"};
//
//    List<ResponseEntity<byte[]>> imageResponses = new ArrayList<>();
//    
//    for(String imagePath : ImagePaths)
//    {
//    // Read the image file
//    File imageFile = new File(imagePath);
//    
//   try {
//        FileInputStream inputStream = new FileInputStream(imageFile);
//        byte[] imageData = FileCopyUtils.copyToByteArray(inputStream);
//
//     // Set response headers
//        HttpHeaders headers = new HttpHeaders();
//        headers.setContentType(MediaType.IMAGE_PNG); 
//
//        ResponseEntity<byte[]> responseEntity = new ResponseEntity<>(imageData, headers, HttpStatus.OK);
//        imageResponses.add(responseEntity);
//      
//    } catch (IOException e) {
//        e.printStackTrace();
//    }
//    }
//    return  new ResponseEntity<>(imageResponses, HttpStatus.OK);
//}	
