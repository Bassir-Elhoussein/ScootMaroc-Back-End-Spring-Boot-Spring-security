package com.scoot.efoot.request;

import lombok.Data;

@Data
public class LoginWithGooleRequest {
	
	private String credential;
	private String clientId;

}
