package com.scoot.efoot.service;

import com.scoot.efoot.config.JwtProvider;
import com.scoot.efoot.exception.UserException;
import com.scoot.efoot.model.User;
import com.scoot.efoot.repository.UserRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserServiceImplementation implements UserService {
	
	private UserRepository userRepository;
	private JwtProvider jwtProvider;
	
	public UserServiceImplementation(
			UserRepository userRepository,
			JwtProvider jwtProvider) {
		
		this.userRepository=userRepository;
		this.jwtProvider=jwtProvider;
		
	}

	@Override
	public User findUserById(Long userId) throws UserException {
		User user=userRepository.findById(userId).orElseThrow(() ->  new UserException("user not found with id "+userId));
		return user;
	}

	@Override
	public User findUserProfileByJwt(String jwt) throws UserException {
		
		String email=jwtProvider.getEmailFromJwtToken(jwt);
		
		System.out.println("email"+email);
		
		User user=userRepository.findByEmail(email);
		
		if(user==null) {
			throw new UserException("user not exist with email "+email);
		}
		System.out.println("email user"+user.getEmail());
		return user;
	}

	@Override
	public User updateUser(Long userid,User req) throws UserException {
		
		User user=findUserById(userid);
		
		if(req.getFullName()!= null) {
			user.setFullName(req.getFullName());
		}
		if(req.getImage()!=null) {
			user.setImage(req.getImage());
		}
		if(req.getBackgroundImage()!=null) {
			user.setBackgroundImage(req.getBackgroundImage());
		}
		if(req.getBirthDate()!=null) {
			user.setBirthDate(req.getBirthDate());
		}
		if(req.getLocation()!=null) {
			user.setLocation(req.getLocation());
		}
		if(req.getBio()!=null) {
			user.setBio(req.getBio());
		}
		if(req.getWebsite()!=null) {
			user.setWebsite(req.getWebsite());
		}
		// Update mobile
		if (req.getMobile() != null) {
			user.setMobile(req.getMobile());
		}
		if (req.getEducation() != null) {
			user.setEducation(req.getEducation());
		}


		// Update player status


		// Update height
		if (req.getHeight() != null) {
			user.setHeight(req.getHeight());
		}

		// Update weight
		if (req.getWeight() != null) {
			user.setWeight(req.getWeight());
		}

		// Update position
		if (req.getPosition() != null) {
			user.setPosition(req.getPosition());
		}

		// Update preferred foot
		if (req.getPreferredFoot() != null) {
			user.setPreferredFoot(req.getPreferredFoot());
		}
        //Update club
		if (req.getPresident() != null) {
			user.setPresident(req.getPresident());
		}
		if (req.getLeague() != null) {
			user.setLeague(req.getLeague());
		}
		if (req.getStadium() != null) {
			user.setStadium(req.getStadium());
		}
//Update Scooter
		if (req.getClubAffiliation() != null) {
			user.setClubAffiliation(req.getClubAffiliation());
		}
		return userRepository.save(user);
		
	}

	@Override
	public User followUser(Long userId, User user) throws UserException {
		User followToUser=findUserById(userId);
		
		if(user.getFollowings().contains(followToUser) && followToUser.getFollowers().contains(user)) {
			user.getFollowings().remove(followToUser);
			followToUser.getFollowers().remove(user);
		}
		else {
					followToUser.getFollowers().add(user);
					user.getFollowings().add(followToUser);
		}
		
		userRepository.save(user);
		userRepository.save(followToUser);
		return followToUser;
	}

	@Override
	public List<User> searchUser(String query) {
	
		return userRepository.searchUser(query);
	}

}
   




