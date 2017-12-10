package org.catena.blockchain;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import java.util.UUID;

import org.catena.blockchain.content.Body;
import org.catena.blockchain.content.Header;
import org.catena.util.BlockWriter;
import org.catena.util.Encryptor;
import org.json.JSONObject;

public class Transaction {

	private Header header;

	private Body auditTx;

	private JSONObject txSignature;

	// ==== miners attributes

	private boolean isSpent;

	private int confirmation;

	public Transaction(String sender, Double blockValue, String reciever, Double gas, Map<Transaction, String> inpTx, Map<Transaction, String> outpTx) {
		String uniqueId = UUID.randomUUID().toString().replace("-", "");
		header = new Header(uniqueId, String.valueOf(blockValue));
		this.setSenderID(sender);
		this.setRecieverID(reciever);
		this.setTxFee(gas);
		
		this.printTxDetails();

		JSONObject signatureJSON = this.buildSignature(inpTx);
		this.setTxSignature(signatureJSON);

		this.init(inpTx,outpTx);
		
		this.setSpent(false);
		
		this.doTransaction();
	}
	
	private void init(Map<Transaction, String> inpTx, Map<Transaction, String> outpTx) {
		Body auditTx = new Body();
		auditTx.setInputTxList(inpTx);
		auditTx.setOutputTxList(outpTx);
		this.setAuditTx(auditTx );
	}

	private void printTxDetails() {

		System.out.println(">>>>>>>>>>>>>>>>>>  TRANSACTION <<<<<<<<<<<<<<<<<");
		System.out.println("ID = " + this.header.getIdTX());
		System.out.println("Sender = " + this.header.getSenderID());
		System.out.println("Value = " + this.header.getTxValue());
		System.out.println("GAS = " + this.header.getGasTX());
		System.out.println("Reciever = " + this.header.getRecieverID());
		System.out.println("Time = " + this.header.getTimestamp());
		System.out.println();
	}
	
	private JSONObject addToJSONTx() {

		JSONObject jsonTx = new JSONObject();
		jsonTx.put("ID",this.header.getIdTX());
		jsonTx.put("Sender",this.header.getSenderID());
		jsonTx.put("Value",this.header.getTxValue());
		jsonTx.put("GAS",this.header.getGasTX());
		jsonTx.put("Reciever",this.header.getRecieverID());
		jsonTx.put("Time", this.header.getTimestamp());
		jsonTx.put("Signature", this.getTxSignature());
		
		return jsonTx;
		
	}

	private void doTransaction() {
		BlockWriter write = new BlockWriter("_blockchain/_block01");
		JSONObject writeContent = this.addToJSONTx();
		write.writeBlock(writeContent); 
	}
	
	private JSONObject buildSignature(Map<Transaction, String> inpTx) {

		String inpTxList = "";
		if (inpTx != null) {
			Set<Transaction> keySet = inpTx.keySet();
			for (Transaction key : keySet) {
				inpTxList = inpTxList +",["+ key.header.getIdTX()+ "]";
			}
			inpTxList = inpTxList.substring(1);
		}
		return this.getDigitalSignature(inpTxList + "="+ this.getTXValue());
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
