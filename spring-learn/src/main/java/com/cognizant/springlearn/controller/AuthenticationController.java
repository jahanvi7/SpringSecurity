package com.cognizant.springlearn.controller;

import java.util.Base64;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RestController;

import io.jsonwebtoken.JwtBuilder;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

@RestController
public class AuthenticationController {

	private static final Logger LOGGER = LoggerFactory.getLogger(AuthenticationController.class);
	
	@GetMapping("/authenticate")
	public Map<String, String> authenticate(@RequestHeader("Authorization") String authHeader){
		LOGGER.info("START");
		LOGGER.debug("Authorization Header={}",authHeader);
		Map<String, String> map = new HashMap<>();
		String user = getUser(authHeader);
		String token = generateJwt(user);
		map.put("token", token);
		LOGGER.info("END");
		return map;
	}
	
	private String getUser(String authHeader) {
		LOGGER.info("Start");
		String [] authHeaderSplit = authHeader.split(" ");
		LOGGER.debug("AuthHeaderSplit = {} :" +authHeaderSplit.toString());
		String converted = new String(Base64.getDecoder().decode(authHeaderSplit[1]));
		LOGGER.debug("Converted = {} :" +converted);
		LOGGER.info("End");
		return converted.split(":")[0];
	}
	
	private String generateJwt(String user) {
		LOGGER.info("START");
		JwtBuilder builder = Jwts.builder();
		builder.setSubject(user);
		// Set the token issue time as current time
		builder.setIssuedAt(new Date());
		// Set the token expiry as 20 minutes from now
		builder.setExpiration(new Date((new Date()).getTime() + 1200000));
		builder.signWith(SignatureAlgorithm.HS256, "secretkey");
		String token = builder.compact();
		LOGGER.debug(token);
		LOGGER.info("END");
		return token;
		
		
	}
}
