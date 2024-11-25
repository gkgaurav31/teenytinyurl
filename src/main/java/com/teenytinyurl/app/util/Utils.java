package com.teenytinyurl.app.util;

import java.util.Random;

public class Utils {

	public String cleanHash(String url) {
	    
		final String ALPHA_NUMERIC = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789";
	    
		// take only alphanumeric characters
        String cleanedHash = url.replaceAll("[^a-zA-Z0-9]", "");
		
        // append random characters to make the length 6
        Random random = new Random();
        while (cleanedHash.length() < 6) {
            int index = random.nextInt(ALPHA_NUMERIC.length()); 
            cleanedHash += ALPHA_NUMERIC.charAt(index);
        }
        
        return cleanedHash.substring(0, 6);  
	}
	
}
