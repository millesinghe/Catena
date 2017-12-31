package org.catena.exception;

public class InsufficientFundsException extends Exception {

	 /**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public InsufficientFundsException(double insufficentFunds) {
	        super("Not Enough Funds to Proceed the Payment \nInsufficent Amount:" +insufficentFunds);
	    }
}
