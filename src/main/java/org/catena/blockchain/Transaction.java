package org.catena.blockchain;

import java.util.Map;
import java.util.UUID;

import org.catena.blockchain.content.Header;
import org.catena.blockchain.content.Body;
import org.catena.util.Encryptor;
import org.json.JSONObject;

public class Transaction {

	private Header header;

	private Body auditTx;

	private JSONObject txSignature;

	// ==== miners attributes

	private boolean isSpent;

	private int confirmation;

	public Transaction(String sender, Double blockValue, String reciever, Double gas) {
		String uniqueId = UUID.randomUUID().toString().replace("-", "");
		header = new Header(uniqueId, String.valueOf(blockValue));
		this.setSenderID(sender);
		this.setRecieverID(reciever);
		this.setTxFee(gas);
		System.out.println("TXID = " + this.header.getIdTX());
		JSONObject signatureJSON = this.getDigitalSignature(this.header.getIdTX() + this.getTXValue());
		this.setTxSignature(signatureJSON);

		this.setSpent(false);
	}

	private JSONObject getDigitalSignature(String inputValue) {

		Encryptor _encryptor = Encryptor.getInstance();
		try {
			_encryptor.encryptWithPrivateKey(inputValue);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		String encryptMsg = _encryptor.getEncryptedMsg();
		String publicKey = _encryptor.getPublicKey();

		JSONObject json = new JSONObject();
		json.put("msg", encryptMsg);
		json.put("key", publicKey);

		return json;
	}

	public String getTXValue() {
		return header.getTxValue();
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
	 * @return the confirmation
	 */
	public int getConfirmation() {
		return confirmation;
	}

	public void setConfirmation(int confirmation) {
		this.confirmation = confirmation;
	}

	public void setTxFee(double feeTX) {
		header.setGasTX(feeTX);
	}

	/**
	 * @return the isSpent
	 */
	public boolean isSpent() {
		return isSpent;
	}

	/**
	 * @param isSpent
	 *            the isSpent to set
	 */
	public void setSpent(boolean isSpent) {
		this.isSpent = isSpent;
	}

	/**
	 * @return the auditTx
	 */
	public Body getAuditTx() {
		return auditTx;
	}

	/**
	 * @param auditTx
	 *            the auditTx to set
	 */
	public void setAuditTx(Body auditTx) {
		this.auditTx = auditTx;
	}

	/**
	 * @return the txSignature
	 */
	public JSONObject getTxSignature() {
		return txSignature;
	}

	/**
	 * @param signatureJSON
	 *            the txSignature to set
	 */
	public void setTxSignature(JSONObject signatureJSON) {
		this.txSignature = signatureJSON;
	}

}
