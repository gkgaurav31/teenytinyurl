package com.teenytinyurl.app.service;

import java.util.HashMap;
import java.util.Map;

import org.springframework.stereotype.Component;

import com.teenytinyurl.app.exception.MaximumRecordsReachedException;

@Component
public class URLMappingHashMapService implements URLMappingService{
	
	private static final long MAXIUMUM_RECORDS = 1000000000L;

	Map<String, String> urlMapping;
	
	public URLMappingHashMapService() {
		
		urlMapping = new HashMap<>();
		
		// for testing:
		urlMapping.put("ABC", "https://test.com");
	
	}
	
	@Override
	public boolean save(String shortURL, String longURL) throws MaximumRecordsReachedException {
		
		if(urlMapping.size() >= MAXIUMUM_RECORDS) {
			throw new MaximumRecordsReachedException("Record limit reached. Please try later.");
		}
		
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
