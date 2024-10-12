package com.scoot.efoot.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class UserDto {
	
	private Long id;
	private String fullName;
	private String email;
	private String image;
	
    private String location;

    private String website;

    private String birthDate;

    private String mobile;

    private String backgroundImage;
    
    private String bio;

    private String education;

    private boolean req_user;
    
    private boolean login_with_google;
    private Double height; // in meters, could be Double or Float

    private Double weight; // in kilograms

    private String position; // e.g., Forward, Midfielder, etc.

    private String preferredFoot; // e.g., Left, Right, Both

    private String userType;

    private String president;



    private String league;

    private String stadium;
    //scooter

    private String clubAffiliation;
    
    private List<UserDto>followers=new ArrayList<>();
    
    private List<UserDto>followings=new ArrayList<>();
    
    private boolean followed;
    
    private boolean isVerified;

}
