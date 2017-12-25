package org.catena.blockchain;

import java.util.ArrayList;
import java.util.Set;
import java.util.UUID;

import org.catena.blockchain.content.Header;
import org.catena.core.Node;
import org.catena.util.BlockMaster;
import org.catena.util.Encryptor;
import org.json.JSONObject;

public class Transaction {

	private Header header;

	private String proofTxs;

	private JSONObject txSignature;

	// ==== miners attributes

	private boolean isSpent;

	private int confirmation;

	public Transaction(String sender, Double blockValue, String reciever, Double gas, ArrayList<Transaction> inputTxs, boolean isSpent) {
		String uniqueId = UUID.randomUUID().toString().replace("-", "");
		header = new Header(uniqueId, String.valueOf(blockValue));
		this.setSenderID(sender);
		this.setRecieverID(reciever);
		this.setTxFee(gas);
		this.proofTxs ="";
		
		JSONObject signatureJSON = this.buildSignature(inputTxs);
		this.setTxSignature(signatureJSON);

		this.setSpent(isSpent);
	}

	public Transaction(String uniqueId, String sender, Double blockValue, String reciever, Double gas,
			JSONObject signatureJSON, boolean isSpent) {
		header = new Header(uniqueId, String.valueOf(blockValue));
		this.setSenderID(sender);
		this.setRecieverID(reciever);
		this.setTxFee(gas);
		this.setTxSignature(signatureJSON);
		Node node = new Node();
		this.proofTxs = node.readMsg(signatureJSON).split("=")[0];
		this.setSpent(isSpent);
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

	public JSONObject addToJSONTx() {

		JSONObject jsonTx = new JSONObject();
		jsonTx.put("id", this.header.getIdTX());
		jsonTx.put("sender", this.header.getSenderID());
		jsonTx.put("value", this.header.getTxValue());
		jsonTx.put("gas", this.header.getGasTX());
		jsonTx.put("reciever", this.header.getRecieverID());
		jsonTx.put("time", this.header.getTimestamp());
		jsonTx.put("signature", this.getTxSignature());
		jsonTx.put("isSpend", this.isSpent());
		
		return jsonTx;

	}

	private JSONObject buildSignature(ArrayList<Transaction> inputTxs) {

		String inpTxList = "";
		if (inputTxs != null && !inputTxs.isEmpty()) {
			Set<Transaction> keySet = null; //inputTxs.keySet();
			for (Transaction tx : inputTxs) {
				inpTxList = inpTxList + "," + tx.header.getIdTX() + "";
			}
			inpTxList = inpTxList.substring(1);
		}else {
			inpTxList = "Genesis_Block";
		}
		return this.getDigitalSignature(inpTxList + "=" + this.getTXValue()+"=>"+this.isSpent);
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

	public void doTransaction() {
		BlockMaster write = new BlockMaster("_blockchain/_block01");
		JSONObject writeContent = this.addToJSONTx();
		write.writeBlock(writeContent);
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

	public Header getHeader() {
		return header;
	}

	public void setHeader(Header header) {
		this.header = header;
	}

	/**
	 * @return the proofTxs
	 */
	public String getProofTxs() {
		return proofTxs;
	}

	/**
	 * @param proofTxs
	 *            the proofTxs to set
	 */
	public void setProofTxs(String proofTxs) {
		this.proofTxs = proofTxs;
	}

}
