package com.scoot.efoot.service;

import com.scoot.efoot.exception.OfferException;
import com.scoot.efoot.exception.UserException;
import com.scoot.efoot.model.Offer;
import com.scoot.efoot.model.User;
import com.scoot.efoot.repository.OfferRepository;
import com.scoot.efoot.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class OfferServiceImplementation implements OfferService {

    @Autowired
    private OfferRepository offerRepository;
    @Autowired
     private UserRepository userRepository;

    @Override
    public Offer createOffer(Offer req, User user) throws UserException, OfferException {
       // req.setSender(user);
        Offer offer = new Offer();
        offer.setSender(user);
        offer.setUserType(user.getUserType());
        offer.setBackgroundImage(req.getBackgroundImage());
        offer.setHeight(req.getHeight());
        offer.setEducation(req.getEducation());
        offer.setLocation(req.getLocation());
        offer.setPosition(req.getPosition());
        offer.setDetails(req.getDetails());
        offer.setWeight(req.getWeight());
        offer.setPreferredFoot(req.getPreferredFoot());
       // offer.set;

        return offerRepository.save(offer);
    }

    @Override
    public List<Offer> findAllOffer() {
        return offerRepository.findAll();
    }

    @Override
    public Offer findById(Long offerId) throws OfferException {
        return offerRepository.findById(offerId.intValue())
                .orElseThrow(() -> new OfferException("Offer not found with id: " + offerId));
    }

    @Override
    public void deleteOfferById(Long offerId, Long userId) throws OfferException, UserException {
        Offer offer = findById(offerId);
        if (!offer.getSender().getId().equals(userId)) {
            throw new UserException("User is not authorized to delete this offer");
        }
        offerRepository.deleteById(offerId.intValue());
    }



    @Override
    public Boolean isApplied(Offer req, User user) throws OfferException {
        System.out.println("isApplied service running");
        if (req == null || user == null) {
            throw new OfferException("Offer or User cannot be null");
        }
        String userType = req.getUserType();
        if ("player".equals(userType)) {
            System.out.println("isApplied service running for player");
            List<Offer> appliedOffers = user.getAppliedOffers();
            if (appliedOffers != null && !appliedOffers.isEmpty()) {
                System.out.println("Applied offers: " + appliedOffers.size());
                for (Offer offer : appliedOffers) {
                    if (offer.getId().equals(req.getId())) {
                        System.out.println("Match found: " + offer.getId());
                        return true; // User has already applied for the offer
                    }
                }
            } else {
                System.out.println("No applied offers found");
            }
        }
        return false; // User has not applied for the offer
    }
    @Override
    public Offer createApplication(Offer req, User user) throws OfferException {
        // Ensure the applicants list is initialized
        if (req.getApplicants() == null) {
            req.setApplicants(new ArrayList<>());
        }

        // Ensure the appliedOffers list is initialized
        if (user.getAppliedOffers() == null) {
            user.setAppliedOffers(new ArrayList<>());
        }

        // Add the user to the applicants list
        List<User> applicants = req.getApplicants();
        List<Offer> appliedOffers = user.getAppliedOffers();

        // Check if the user has already applied
        if (!applicants.contains(user)) {
            applicants.add(user);
            req.setApplicants(applicants);

            // Add the offer to the user's applied offers
            appliedOffers.add(req);
            user.setAppliedOffers(appliedOffers);

            // Save the updated user and offer
            userRepository.save(user);
            return offerRepository.save(req);
        } else {
            throw new OfferException("User has already applied to this offer");
        }
    }

    @Override
    public List<Offer> getUsersOffer(User user) {
        return offerRepository.findBySender(user);
    }
}