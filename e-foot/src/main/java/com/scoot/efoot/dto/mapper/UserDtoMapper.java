package com.scoot.efoot.dto.mapper;

import com.scoot.efoot.dto.UserDto;
import com.scoot.efoot.model.User;
import com.scoot.efoot.util.UserUtil;

import java.util.ArrayList;
import java.util.List;

public class UserDtoMapper {
	
	public static UserDto toUserDto(User user) {
		
		UserDto userDto=new UserDto();
		userDto.setId(user.getId());
		userDto.setEmail(user.getEmail());
		userDto.setFullName(user.getFullName());
		userDto.setImage(user.getImage());
		userDto.setBackgroundImage(user.getBackgroundImage());
		userDto.setBio(user.getBio());

		userDto.setHeight(user.getHeight());
		userDto.setWeight(user.getWeight());
		userDto.setPosition(user.getPosition());
		userDto.setPreferredFoot(user.getPreferredFoot());

		userDto.setUserType(user.getUserType());


		userDto.setPresident(user.getPresident());
		userDto.setLeague(user.getLeague());
		userDto.setStadium(user.getStadium());
        userDto.setMobile(user.getMobile());
        userDto.setEducation(user.getEducation());
        userDto.setClubAffiliation(user.getClubAffiliation());
		userDto.setBirthDate(user.getBirthDate());
		userDto.setFollowers(toUserDtos(user.getFollowers()));
		userDto.setFollowings(toUserDtos(user.getFollowings()));
		userDto.setLogin_with_google(user.isLogin_with_google());
		userDto.setLocation(user.getLocation());
		userDto.setVerified(UserUtil.isVerified(user.getVerification().getEndsAt()));
		
		return userDto;
	}
	
	public static List<UserDto> toUserDtos(List<User> users) {
		
		List<UserDto> userDtos=new ArrayList<>();
		
		for(User user: users) {
			UserDto userDto=new UserDto();
			userDto.setId(user.getId());
			userDto.setEmail(user.getEmail());
			userDto.setFullName(user.getFullName());
			userDto.setImage(user.getImage());
			userDto.setBackgroundImage(user.getBackgroundImage());
			userDto.setBio(user.getBio());
            userDto.setEducation(user.getEducation());
			userDto.setHeight(user.getHeight());
			userDto.setWeight(user.getWeight());
			userDto.setPosition(user.getPosition());
			userDto.setPreferredFoot(user.getPreferredFoot());
            userDto.setMobile(user.getMobile());
			userDto.setUserType(user.getUserType());


			userDto.setPresident(user.getPresident());
			userDto.setLeague(user.getLeague());
			userDto.setStadium(user.getStadium());

			userDto.setBirthDate(user.getBirthDate());
            //scooter

            userDto.setClubAffiliation(user.getClubAffiliation());
            //
			userDto.setLogin_with_google(user.isLogin_with_google());
			userDto.setLocation(user.getLocation());

			userDtos.add(userDto);
		}
		return userDtos;
	}

}
