package com.teenytinyurl.app.service;

import java.security.NoSuchAlgorithmException;

public interface GenerateHash {
	
	String generateHash(String longURL) throws NoSuchAlgorithmException;
	
}
