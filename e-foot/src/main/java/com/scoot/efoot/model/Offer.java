package com.scoot.efoot.model;

import jakarta.persistence.*;
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
@Entity
public class Offer {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "sender_id", nullable = false)
    private User sender; // User (Club or Scooter) making the offer

    @Column(nullable = false)
    private String userType; // Player, Club, or Scooter
    private String backgroundImage;
    private Double height; // Height of the player
    private Double weight; // Weight of the player
    private String position; // e.g., Forward, Midfielder, etc.
    private String preferredFoot; // e.g., Left, Right, Both
    private String education;
    private String location;

    @Column(nullable = false)
    private String details; // Custom details about the offer or player

    @ManyToMany
    @JoinTable(
            name = "offer_applications",
            joinColumns = @JoinColumn(name = "offer_id"),
            inverseJoinColumns = @JoinColumn(name = "user_id")
    )
    private List<User> applicants = new ArrayList<>();
}