package com.scoot.efoot.service;

import com.scoot.efoot.exception.LikeException;
import com.scoot.efoot.exception.TwitException;
import com.scoot.efoot.exception.UserException;
import com.scoot.efoot.model.Like;
import com.scoot.efoot.model.User;

import java.util.List;

public interface LikesService {
	
	public Like likeTwit(Long twitId, User user) throws UserException, TwitException;
	
	public Like unlikeTwit(Long twitId, User user) throws UserException, TwitException, LikeException;
	
	public List<Like> getAllLikes(Long twitId) throws TwitException;

}
