package com.teenytinyurl.app.controller;

import java.security.NoSuchAlgorithmException;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.teenytinyurl.app.exception.MaximumRecordsReachedException;
import com.teenytinyurl.app.service.ShortURLHashService;
import com.teenytinyurl.app.service.URLMappingService;
import com.teenytinyurl.model.ShortURLRequest;
import com.teenytinyurl.model.ShortURLResponse;

@RestController
@CrossOrigin(origins = "*")
public class ShortURLController {
	
	private ShortURLHashService shortURLHashService;
	private URLMappingService urlMappingService;
	
	// dependency injection
	public ShortURLController(ShortURLHashService shortURLHashService, URLMappingService urlMappingService) {
		this.shortURLHashService = shortURLHashService;
		this.urlMappingService = urlMappingService;
	}
	
	@PostMapping("/api/shorten")
	public ResponseEntity<ShortURLResponse> shortenURL(@RequestBody ShortURLRequest shortURLRequest ) {
		
		try {
			
			String longURL = shortURLRequest.getLongURL();
			
			String shortURL = shortURLHashService.generateShortURL(longURL);
			
			ShortURLResponse shortURLResponse = new ShortURLResponse();
			
			shortURLResponse.setLongURL(longURL);
			shortURLResponse.setShortUrl(shortURL);
			
	        return ResponseEntity.status(HttpStatus.CREATED).body(shortURLResponse);
	        
		} catch (NoSuchAlgorithmException e) {
			
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(new ShortURLResponse());
			
		} catch (MaximumRecordsReachedException e) {	
			
            return ResponseEntity.status(HttpStatus.TOO_MANY_REQUESTS)
                    .body(new ShortURLResponse());
		}

	}
	
	@GetMapping("/{shortURL}")
	public ResponseEntity<Void> fetchLongURL(@PathVariable("shortURL") String shortURL){
		
		ShortURLResponse response = new ShortURLResponse();
		
		String longURL = "https://" + urlMappingService.fetch(shortURL);
		
		if(longURL.equals("")) {
           return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
		}
		
		response.setShortUrl(shortURL);
		response.setLongURL(longURL);
		
		// Location Header for Redirection
		HttpHeaders headers = new HttpHeaders();
		headers.add("Location", longURL);  
		
		return ResponseEntity.status(HttpStatus.FOUND)
				.headers(headers)
				.build();
	}
	
}
