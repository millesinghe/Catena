package org.catena.blockchain.transaction;

import java.sql.Timestamp;

public class Header {

	public Header(String uniqueId) {
		this.idTX = uniqueId;
		this.timestamp = new Timestamp(System.currentTimeMillis()).getTime();
	}

	private String idTX;

	private double gasTX;

	private long timestamp;
	
	private String senderID;

	private String recieverID;

	public String getIdTX() {
		return idTX;
	}

	public void setIdTX(String idTX) {
		this.idTX = idTX;
	}

	public double getGasTX() {
		return gasTX;
	}

	public void setGasTX(double gasTX) {
		this.gasTX = gasTX;
	}

	public long getTimestamp() {
		return timestamp;
	}

	public void setTimestamp(long timestamp) {
		this.timestamp = timestamp;
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
	
	
}
