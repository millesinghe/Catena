package org.catena.util;

import java.io.UnsupportedEncodingException;
import java.security.InvalidKeyException;
import java.security.KeyFactory;
import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.NoSuchAlgorithmException;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.X509EncodedKeySpec;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;

import org.apache.commons.codec.binary.Base64;

public class Encryptor {

	private Cipher cipher;

	private String publicKey;
	
	private String encryptedMsg;
	
	
	public Encryptor() throws NoSuchAlgorithmException, NoSuchPaddingException {
		this.cipher = Cipher.getInstance("RSA");
	}

	private String encryptText(String msg, PrivateKey key) throws NoSuchAlgorithmException, NoSuchPaddingException,
			UnsupportedEncodingException, IllegalBlockSizeException, BadPaddingException, InvalidKeyException {
		this.cipher.init(Cipher.ENCRYPT_MODE, key);
		return Base64.encodeBase64String(cipher.doFinal(msg.getBytes("UTF-8")));
	}

	public String decryptWithPublicKey(String msg, String strKey)
			throws InvalidKeyException, UnsupportedEncodingException, IllegalBlockSizeException, BadPaddingException {

		byte[] publicBytes = Base64.decodeBase64(strKey);
		X509EncodedKeySpec keySpec = new X509EncodedKeySpec(publicBytes);
		KeyFactory keyFactory;
		PublicKey pubKey = null;
		try {
			keyFactory = KeyFactory.getInstance("RSA");
			pubKey = keyFactory.generatePublic(keySpec);
		} catch (NoSuchAlgorithmException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		} catch (InvalidKeySpecException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		this.cipher.init(Cipher.DECRYPT_MODE, pubKey);
		return new String(cipher.doFinal(Base64.decodeBase64(msg)), "UTF-8");
	}

	public void encryptWithPrivateKey(String msg) throws Exception {
		Encryptor ac = new Encryptor();
		KeyPair keyPair = KeyPairGenerator.getInstance("RSA").generateKeyPair();

		PrivateKey privateKey = keyPair.getPrivate();
		PublicKey publicKey = keyPair.getPublic();
		
		this.publicKey= Base64.encodeBase64String(publicKey.getEncoded());
		this.encryptedMsg = ac.encryptText(msg, privateKey);
	}


	public String getPublicKey() {
		return publicKey;
	}

	public String getEncryptedMsg() {
		return encryptedMsg;
	}
	
	

}
