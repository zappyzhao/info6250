package com.zappy.myapp.validator;

import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

import com.zappy.myapp.pojo.User;

public class ProfileValidator implements Validator {

	@Override
	public boolean supports(Class aClass) {
		// TODO Auto-generated method stub
		return aClass.equals(User.class);
	}

	@Override
	public void validate(Object obj, Errors errors) {
		// TODO Auto-generated method stub
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "aboutMe", "error.invalid.aboutMe", "AboutMe Required");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "personality", "error.invalid.personality", "Personality Required");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "aboutLookingFor", "error.invalid.aboutLookingFor", "AboutLookingFor Required");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "height", "error.invalid.height", "Height Required");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "weight", "error.invalid.weight", "Weight Required");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "bodyStyle", "error.invalid.bodyStyle", "BodyStyle Required");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "hairColor", "error.invalid.hairColor", "HairColor Required");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "eyeColor", "error.invalid.eyeColor", "EyeColor Required");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "religion", "error.invalid.religion", "Religion Required");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "hometown", "error.invalid.hometown", "Hometown Required");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "educationLevel", "error.invalid.educationLevel", "EducationLevel Required");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "occupation", "error.invalid.occupation", "Occupation Required");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "favoriteActivity", "error.invalid.favoriteActivity", "FavoriteActivity Required");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "favoriteFood", "error.invalid.favoriteFood", "FavoriteFood Required");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "drinking", "error.invalid.drinking", "Drinking Required");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "smoking", "error.invalid.smoking", "Smoking Required");
	}

}
