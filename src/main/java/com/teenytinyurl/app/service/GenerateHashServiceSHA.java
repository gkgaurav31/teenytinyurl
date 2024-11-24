package com.teenytinyurl.app.service;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import org.springframework.stereotype.Component;

@Component
public class GenerateHashServiceSHA implements GenerateHash{

	@Override
	public String generateHash(String longURL) throws NoSuchAlgorithmException{
		
		byte[] message = longURL.getBytes();

		MessageDigest messageDigest = MessageDigest.getInstance("SHA-256");
		messageDigest.update(message);
		
		byte[] digest = messageDigest.digest();
			
		return String.valueOf(digest);
		
	}

}
