package com.scoot.efoot.model;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class Varification {
	
	private boolean status=false;
	private LocalDateTime startedAt;
	private LocalDateTime endsAt;
	private String planType;

}
