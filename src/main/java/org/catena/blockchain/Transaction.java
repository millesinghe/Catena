package org.catena.blockchain;

import java.util.UUID;

import org.catena.blockchain.transaction.Body;
import org.catena.blockchain.transaction.Header;
import org.json.JSONObject;

public class Transaction {

	private Header header;

	private JSONObject body;
	
	private Double txValue;
	
	private AssetTXAuditor auditTx;
	
	private boolean isSpent;
	
	private int confirmation;
	
	public Transaction() {
		String uniqueId = UUID.randomUUID().toString().replace("-", "");
		header = new Header(uniqueId);
		this.setSpent(false);
	}

	public void buildTx() {
		
		
		System.out.println();
		System.out.println();
	}

	public void sendTransaction() {
	}

	public String getSenderID() {
		return header.getSenderID();
	}

	public void setSenderID(String senderID) {
		header.setSenderID(senderID);
	}

	public String getRecieverID() {
		return header.getRecieverID();
	}

	public void setRecieverID(String recieverID) {
		header.setRecieverID(recieverID);
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

	/**
	 * @return the confirmation
	 */
	public int getConfirmation() {
		return confirmation;
	}

	/**
	 * @param confirmation the confirmation to set
	 */
	public void setConfirmation(int confirmation) {
		this.confirmation = confirmation;
	}

	public void setTxFee(double feeTX) {
		header.setGasTX(feeTX);
	}

	/**
	 * @return the txValue
	 */
	public double getTxValue() {
		return txValue;
	}

	/**
	 * @param txValue the txValue to set
	 */
	public void setTxValue(double txValue) {
		this.txValue = txValue;
	}

	/**
	 * @return the isSpent
	 */
	public boolean isSpent() {
		return isSpent;
	}

	/**
	 * @param isSpent the isSpent to set
	 */
	public void setSpent(boolean isSpent) {
		this.isSpent = isSpent;
	}

	/**
	 * @return the auditTx
	 */
	public AssetTXAuditor getAuditTx() {
		return auditTx;
	}

	/**
	 * @param auditTx the auditTx to set
	 */
	public void setAuditTx(AssetTXAuditor auditTx) {
		this.auditTx = auditTx;
	}

}
