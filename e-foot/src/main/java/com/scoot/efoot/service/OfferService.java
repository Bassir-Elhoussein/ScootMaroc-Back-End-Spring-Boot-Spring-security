package com.scoot.efoot.service;

import com.scoot.efoot.exception.OfferException;
import com.scoot.efoot.exception.UserException;
import com.scoot.efoot.model.Offer;

import com.scoot.efoot.model.User;


import java.util.List;

public interface OfferService {

    public Offer createOffer(Offer req, User user)throws UserException, OfferException;

    public List<Offer> findAllOffer();



    public Offer findById(Long OfferId) throws OfferException;

    public void deleteOfferById(Long OfferId,Long userId) throws OfferException, UserException;

    public Boolean isApplied(Offer req, User user) throws OfferException;

    public Offer createApplication(Offer req, User user) throws OfferException;

    public List<Offer> getUsersOffer(User user);

}
