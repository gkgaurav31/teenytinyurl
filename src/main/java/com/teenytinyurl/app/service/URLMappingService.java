package com.teenytinyurl.app.service;

public interface URLMappingService {
	boolean save(String shortURL, String longURL);
	String fetch(String shortURL);
}
