package com.teenytinyurl.app.service;

import java.security.NoSuchAlgorithmException;

import com.teenytinyurl.app.exception.MaximumRecordsReachedException;

public interface ShortURL {
	
	String generateShortURL(String longURL) throws NoSuchAlgorithmException, MaximumRecordsReachedException;
	String fetchLongURL(String shortURL);
	
}
