package org.catena.blockchain.transaction;

import org.catena.util.Encryptor;
import org.json.JSONObject;

public class Body {

	private String message;

	public Body(String msg) {
		this.message= msg;
	}
	public void hashMessage() {}

	public JSONObject getDigitalSignature() {
		
		Encryptor _encryptor = Encryptor.getInstance();
		try {
			_encryptor.encryptWithPrivateKey(message);
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
	public String getMessage() {
		return message;
	}

	/**
	 * @param message the message to set
	 */
	public void setMessage(String message) {
		this.message = message;
	}
}
