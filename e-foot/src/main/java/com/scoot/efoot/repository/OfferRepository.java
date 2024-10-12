package com.scoot.efoot.repository;

import com.scoot.efoot.model.Offer;
import com.scoot.efoot.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface OfferRepository extends JpaRepository<Offer, Integer> {

    Optional<Offer> findById(int id);

    List<Offer> findAll();

    List<Offer> findBySender(User user);

    void deleteById(int id);
}