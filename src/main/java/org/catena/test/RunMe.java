package org.catena.test;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.security.GeneralSecurityException;
import java.security.InvalidKeyException;
import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.NoSuchAlgorithmException;
import java.security.PrivateKey;
import java.security.PublicKey;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;

import org.apache.commons.codec.binary.Base64;
import org.catena.blockchain.Transaction;
import org.catena.mine.Node;
import org.catena.util.Encryptor;
import org.catena.util.PropertyFileHandler;
import org.json.JSONObject;

public class RunMe {
	
	public static void main(String[] args) throws Exception {
	
		Transaction tx = new Transaction();
		
		PropertyFileHandler prop = new PropertyFileHandler("catena.properties");
		
		tx.setSenderID(prop.readFromPropertyFile("catena.genesis.id"));
		tx.setRecieverID(prop.readFromPropertyFile("catena.wallet.id"));
		tx.setBody("Init Transfer");
		tx.setTxValue(1000.00);
		tx.setTxFee(1.0);
		tx.buildTx();
		JSONObject sss = tx.getBody();
		System.out.println(sss);
		
		/*
		Node n = new Node();
		n.readMsg();*/
	}

	public void testEncryption() throws Exception {
		Encryptor en = new Encryptor();
		System.out.println("----- ENCRYPTION START ---------");
		String msg = "My Name is Khan";
		System.out.println(msg);
		en.encryptWithPrivateKey(msg);
		System.out.println("Public Key - "+ en.getPublicKey());
		System.out.println("Message - "+ en.getEncryptedMsg());

		System.out.println("----- Decrypt Message ---------");
		String decryptMsg = en.decryptWithPublicKey(en.getEncryptedMsg(), en.getPublicKey());
		System.out.println(decryptMsg);
	
	}
}
