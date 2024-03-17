package com.SpringBoot.GharBhada.ServiceImpl;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.multipart.MultipartFile;

import com.SpringBoot.GharBhada.DAO.HomeDao;
import com.SpringBoot.GharBhada.DTO.ImageDto;
import com.SpringBoot.GharBhada.Entity.Home;
import com.SpringBoot.GharBhada.Entity.Image;
import com.SpringBoot.GharBhada.Exception.FileSizeLimitExcededException;
import com.SpringBoot.GharBhada.Exception.ResourceNotFoundException;
import com.SpringBoot.GharBhada.ModelMapper.ImageModelMapper;
import com.SpringBoot.GharBhada.Repository.ImageRepo;
import com.SpringBoot.GharBhada.Repository.PersonRepo;
import com.SpringBoot.GharBhada.Service.HomeService;
import com.SpringBoot.GharBhada.Service.ImageService;

@Component
public class ImageServiceImpl implements ImageService {

	@Autowired
	private ImageRepo imageRepo;

	@Autowired
	private ImageModelMapper imageModelMapper;

	@Autowired
	private HomeService homeService;

	@Autowired
	private PersonRepo personRepo;

	@Autowired
	private HomeDao homeDao;

	@Value("${project.image:default }")
	private String uploadPath;
	
	 private String staticLocation = "E:/ReactDevelopment/ReactPractice/GharBhada/public/assets/property/";

	// Upload Image Paths
	@Override
	public ImageDto uploadImagePath(ImageDto imageDto, String homeId, String personId) {
		Home home = homeDao.getHomeByHomeId(homeId);
		personRepo.findById(personId);
		Image imageDtoToImage = imageModelMapper.ImageDtoToImage(imageDto);
		imageDtoToImage.setHome(home);
		Image save = imageRepo.save(imageDtoToImage);
		ImageDto imageToImageDto = imageModelMapper.ImageToImageDto(save);
		return imageToImageDto;
	}

	// Uploading Images
	@Override
	public List<String> storeImages(List<MultipartFile> files, String homeId) {
		
		//Check whether home with hmeId exist
		homeDao.getHomeByHomeId(homeId);

		// Check if the file size exceeds a certain limit (e.g., 10MB)
		long maxSizeBytes = 10 * 1024 * 1024; // 10MB

		List<String> imageNames = new ArrayList<>();

		for (MultipartFile file : files) {

			// Check each file Size
			if (file.getSize() > maxSizeBytes) {
				throw new FileSizeLimitExcededException(file.getSize(), maxSizeBytes);
			}
			// Generate Unique image name
			String originalFilename = file.getOriginalFilename();
			int lastIndexOf = originalFilename.lastIndexOf('.');
			String fileExtension = originalFilename.substring(lastIndexOf + 1);
			String imageName = UUID.randomUUID().toString();
			String uploadImagePath = staticLocation + imageName + "." + fileExtension;
            String fetchImagePath = imageName + "." + fileExtension;
			
			// create folder if not there
//			File f = new File(uploadPath);
//			if (!f.exists()) {
//				f.mkdir();
//			}

			// save Image file to file system
			Path path = Paths.get(uploadImagePath);
			try {
				Files.copy(file.getInputStream(), path, StandardCopyOption.REPLACE_EXISTING);
			} catch (IOException e) {
				// Handle the IO exception
				e.printStackTrace();
			}
			// save image meta data to database
			String imageId = UUID.randomUUID().toString();
			Image image = new Image();
			image.setImage_id(imageId);
			image.setImage_path(fetchImagePath);

			// Setting home Id
			Home home = new Home();
			home.setHome_id(homeId);
			image.setHome(home);
			imageNames.add(imageName);

			imageRepo.save(image);
		}

		return imageNames;
	}

	// Get Image by Image Id
	@Override
	public byte[] getSingleImage(String imageId) {

		Optional<Image> image = imageRepo.findById(imageId);
		if (image.isEmpty()) {
			throw new ResourceNotFoundException("Image ", imageId);
		}

		String imagePath = image.get().getImage_path();
		// Construct the file path
//		String imagePath = "images/3fefdf29-d57a-4bc7-9d24-5a7a108de9af.png";
//		String imagePath = uploadPath + image_path;

		// Read the image file
		File imageFile = new File(imagePath);

		byte[] imageData = null;

		try {
			FileInputStream inputStream = new FileInputStream(imageFile);
			imageData = FileCopyUtils.copyToByteArray(inputStream);
		} catch (IOException e) {
			e.printStackTrace();
		}
		return imageData;
	}

	// Get List of images By HomeId
	@Override
	public List<ImageDto> getAllHomesPicturePath(String homeId) {
		// Check whether Home exists or not
		homeService.getSingleHome(homeId);
		List<Image> images = imageRepo.findByHomeId(homeId);
		List<ImageDto> imageDtos = images.stream().map(image -> imageModelMapper.ImageToImageDto(image))
				.collect(Collectors.toList());
		return imageDtos;
	}

}
