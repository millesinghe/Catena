package org.catena.blockchain;

import org.catena.blockchain.transaction.Body;
import org.catena.blockchain.transaction.Header;
import org.json.JSONObject;

public class Transaction {

	private String senderID;

	private String recieverID;

	private Header header;

	private JSONObject body;
	
	public void buildTx() {
		
	}

	public void sendTransaction() {
	}

	public String getSenderID() {
		return senderID;
	}

	public void setSenderID(String senderID) {
		this.senderID = senderID;
	}

	public String getRecieverID() {
		return recieverID;
	}

	public void setRecieverID(String recieverID) {
		this.recieverID = recieverID;
	}

	public Header getHeader() {
		return header;
	}

	public void setHeader(Header header) {
		this.header = header;
	}

	/**
	 * @return the message
	 */
	public JSONObject getBody() {
		return body;
	}

	/**
	 * @param message the message to set
	 */
	public void setBody(String message) {
		this.body = new Body(message).getDigitalSignature();
	}

}
