package com.scoot.efoot.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
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
public class User {

	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String fullName;

    private String location;

    private String website;

    private String birthDate;

    @Column(nullable = false, unique = true)
    private String email;

    private String password;

    private String mobile;

    private String image;

    private String backgroundImage;
    
    private String bio;

    private String education;

    private String userType;
    
    private boolean login_with_google;
    
    private boolean is_req_user=false;

    private Double height; // in meters, could be Double or Float

    private Double weight; // in kilograms

    private String position; // e.g., Forward, Midfielder, etc.

    private String preferredFoot; // e.g., Left, Right, Both






    //scooter

    private String clubAffiliation;

    // club

    private String president;

    private String league;

    private String stadium;

//    @ManyToMany(mappedBy = "retwitUser",cascade = CascadeType.ALL)
//    private List<Twit> retwits = new ArrayList<>();
    
    @JsonIgnore
    @OneToMany(mappedBy = "user",cascade = CascadeType.ALL)
    private List<Twit> twit = new ArrayList<>();
    
    @JsonIgnore
    @OneToMany(mappedBy = "user",cascade = CascadeType.ALL)
    private List<Like> likes  = new ArrayList<>();
    
    @Embedded
    private Varification verification;
    
    @JsonIgnore
    @ManyToMany(cascade = CascadeType.ALL)
    private List<User>followers=new ArrayList<>();
    
    @JsonIgnore
    @ManyToMany(mappedBy = "followers")
    private List<User>followings=new ArrayList<>();




    @JsonIgnore
    @OneToMany(mappedBy = "sender", cascade = CascadeType.ALL)
    private List<Offer> createdOffers = new ArrayList<>();

    @JsonIgnore
    @ManyToMany(mappedBy = "applicants")
    private List<Offer> appliedOffers = new ArrayList<>();
}
