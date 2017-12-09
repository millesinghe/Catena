package org.catena.blockchain;

import org.catena.util.Encryptor;
import org.json.JSONObject;

public class Block {

	private String txValue;
	private JSONObject jsonTXBody;
	

	public Block(String message) {
		this.txValue= message;
		this.jsonTXBody = this.getDigitalSignature();
		System.out.println();
	}
	public void hashMessage() {}

	public JSONObject getDigitalSignature() {
		
		Encryptor _encryptor = Encryptor.getInstance();
		try {
			_encryptor.encryptWithPrivateKey(txValue);
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
	/**
	 * @return the message
	 */
	public Object getMessage() {
		return txValue;
	}

	/**
	 * @param message the message to set
	 */
	public void setMessage(String message) {
		this.txValue = message;
	}	

}
