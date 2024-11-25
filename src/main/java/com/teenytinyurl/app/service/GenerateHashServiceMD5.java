package com.teenytinyurl.app.service;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Component;

@Component
@Primary
public class GenerateHashServiceMD5 implements GenerateHash{

	@Override
	public String generateHash(String longURL) throws NoSuchAlgorithmException{
		
		byte[] message = longURL.getBytes();

		MessageDigest messageDigest = MessageDigest.getInstance("MD5");
		messageDigest.update(message);
		
		byte[] digest = messageDigest.digest();
			
		return String.valueOf(digest);
		
	}

}

