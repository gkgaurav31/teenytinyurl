package com.teenytinyurl.app.exception;

public class MaximumRecordsReachedException extends Exception{
	
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public MaximumRecordsReachedException(String message) {
        super(message);
    }

}
