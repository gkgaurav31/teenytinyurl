package com.teenytinyurl.app.service;

import java.security.NoSuchAlgorithmException;

public interface ShortURL {
	
	String generateShortURL(String longURL) throws NoSuchAlgorithmException;
	String fetchLongURL(String shortURL);
	
}
