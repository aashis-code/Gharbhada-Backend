package com.SpringBoot.GharBhada.Utils;

public interface EntityValidation {

	//Person Entity Class
	 String EMAIL_PATTERN =
	        "^[_A-Za-z0-9-+]+(.[_A-Za-z0-9-]+)*@"
	        + "[A-Za-z0-9-]+(.[A-Za-z0-9]+)*(.[A-Za-z]{2,})$";
	 String NAME_MESSAGE = "Name can not be blank.";
	 String NAME_SIZE_MESSAGE =  "Name must be at least 5 characters long.";
	 String PASSWORD_MESSAGE = "Password cann't be blank.";
	 String EMAIL_PATTERN_ERROR = "Enter valid email.";
	 String PASSWORD_SIZE_MESSAGE = "Password must be at least 3 characters long.";
	 String PHONE_NUM = "Enter valid Phone Number.";
	 String ADDRESS_MESSAGGE = "Address can't be blank";
	 
	 //Home Entity Class
	 String TITLE_LENGTH = "Title must be at least 10 characters long.";
	 String HOME_DESCRIPTION = "Description must be between 50 to 300 characters.";
	 String TITLE_BLANK_MESSAGE ="Title can't be blank.";
	 String LONGITUDE_BLANK_MESSAGE ="Longitude can't be blank.";
	 String LATTITUDE_BLANK_MESSAGE ="Lattitude can't be blank.";
	 String DESCRIPTION_BLANK_MESSAGE ="Description can't be blank.";
	 String DISTRICT_BLANK_MESSAGE ="District can't be blank.";
	 String CITY_BLANK_MESSAGE ="City can't be blank.";
	 String HOME_PRICE = "Rent price should be mentioned.";
	 String HOME_AREA="Area can not be blank.";
	 String HOME_BEDROOM="Bedroom must be at least 1.";
	 String HOME_BATHROOM="Bathroom must be at least 1.";
	 String HOME_AMENITIES="Amenities can not be blank.";
	 String LOCATION_BLANK="Location can not be blank.";
	 
	 //Comment Entity class
	 String COMMENT_BLANK_MESSAGE = "Comment can't be blank.";
	 
	 //Rating entity class
	 String RATING_VALUE_BLANK = "Rating cant be blank";
	 String RATING_RANGE_MESSAGE = "Rating must be in between 0 to 5.";
	 
	 //Category entity class
	 String CATEGORY_NAME_BLANK_MESSAGE = "Category name can't be blank.";
	 String CATEGORY_NAME_MIN_LENGTH = "Minimum length of category name is of 3 characters.";
	 String CATEGORY_DESCRIPTION_BLANK_MESSAGE = "Category description can't be blank.";
	 String CATEGORY_DESCRIPTION_MIN_LENGTH = "Category description must be at least 15 character long";
	 
	 //Image entity class
	 String IMAGE_PATH_BLANK_MESSAGE = "Image path can't be blank";
	 
}
