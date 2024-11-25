package com.teenytinyurl.app.service;

import java.security.NoSuchAlgorithmException;

import org.springframework.stereotype.Service;

import com.teenytinyurl.app.exception.MaximumRecordsReachedException;
import com.teenytinyurl.app.util.Utils;

@Service
public class ShortURLHashService implements ShortURL{

    private final GenerateHash generateHash;
    private final URLMappingService urlMappingService;
    
    // constructor-based dependency injection
    public ShortURLHashService(GenerateHash generateHash, URLMappingService urlMappingService) {
    	this.generateHash = generateHash;
    	this.urlMappingService = urlMappingService;
    }

	@Override
	public String generateShortURL(String longURL) throws NoSuchAlgorithmException, MaximumRecordsReachedException {
        
		String shortURL = generateHash.generateHash(longURL);
		
		// take only alphanumeric and first 6 characters
		Utils utils = new Utils(); // can use DI here
        shortURL = utils.cleanHash(shortURL); 
		
		// save to DB
		urlMappingService.save(shortURL, longURL);
		
		return shortURL;
		
	}

	@Override
	public String fetchLongURL(String shortURL) {
		return urlMappingService.fetch(shortURL);
	}
	
}
