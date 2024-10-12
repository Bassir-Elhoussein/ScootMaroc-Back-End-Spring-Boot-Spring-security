package com.scoot.efoot.model;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
@Table(name="likes")
public class Like {
	
  	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
	  
  	@ManyToOne
  	private User user;
  	
  	@ManyToOne
  	private Twit twit;
  	
 
}
