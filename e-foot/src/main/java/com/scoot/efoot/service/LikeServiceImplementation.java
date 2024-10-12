package com.scoot.efoot.service;

import com.scoot.efoot.exception.LikeException;
import com.scoot.efoot.exception.TwitException;
import com.scoot.efoot.exception.UserException;
import com.scoot.efoot.model.Like;
import com.scoot.efoot.model.Twit;
import com.scoot.efoot.model.User;
import com.scoot.efoot.repository.LikeRepository;
import com.scoot.efoot.repository.TwitRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class LikeServiceImplementation implements LikesService {

	private LikeRepository likeRepository;
	private TwitService twitService;
	private TwitRepository twitRepository;
	
	public LikeServiceImplementation(
			LikeRepository likeRepository,
			TwitService twitService,
			TwitRepository twitRepository) {
		this.likeRepository=likeRepository;
		this.twitService=twitService;
		this.twitRepository=twitRepository;
	}

	@Override
	public Like likeTwit(Long twitId, User user) throws UserException, TwitException {
		
		Like isLikeExist=likeRepository.isLikeExist(user.getId(), twitId);
		
		if(isLikeExist!=null) {
			likeRepository.deleteById(isLikeExist.getId());
			return isLikeExist;
		}
		
		Twit twit=twitService.findById(twitId);
		Like like=new Like();
		like.setTwit(twit);
		like.setUser(user);
		
		Like savedLike=likeRepository.save(like);
		
		
		twit.getLikes().add(savedLike);
		twitRepository.save(twit);
		
		return savedLike;
	}

	@Override
	public Like unlikeTwit(Long twitId, User user) throws UserException, TwitException, LikeException {
		Like like=likeRepository.findById(twitId).orElseThrow(()->new LikeException("Like Not Found"));
		
		if(like.getUser().getId().equals(user.getId())) {
			throw new UserException("somthing went wrong...");
		}
		
		likeRepository.deleteById(like.getId());
		return like;
	}

	@Override
	public List<Like> getAllLikes(Long twitId) throws TwitException {
		Twit twit=twitService.findById(twitId);
		
		List<Like> likes=likeRepository.findByTwitId(twit.getId());
		return likes;
	}

}
