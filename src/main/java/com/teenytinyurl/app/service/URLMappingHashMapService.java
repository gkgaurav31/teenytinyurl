package com.teenytinyurl.app.service;

import java.util.HashMap;
import java.util.Map;

import org.springframework.stereotype.Component;

@Component
public class URLMappingHashMapService implements URLMappingService{

	Map<String, String> urlMapping;
	
	public URLMappingHashMapService() {
		
		urlMapping = new HashMap<>();
		
		// for testing:
		urlMapping.put("ABC", "https://test.com");
	
	}
	
	@Override
	public boolean save(String shortURL, String longURL) {
		urlMapping.put(shortURL, longURL);
		return true;
	}

	@Override
	public String fetch(String shortURL) {
		
		if(!urlMapping.containsKey(shortURL)) {
			return ""; // is this a good idea?
		}else
			return urlMapping.get(shortURL);
	}

}
