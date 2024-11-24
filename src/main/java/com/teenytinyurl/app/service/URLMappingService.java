package com.teenytinyurl.app.service;

import com.teenytinyurl.app.exception.MaximumRecordsReachedException;

public interface URLMappingService {
	boolean save(String shortURL, String longURL) throws MaximumRecordsReachedException;
	String fetch(String shortURL);
}
