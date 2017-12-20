package org.catena.test;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import org.catena.blockchain.SuperTransaction;
import org.catena.blockchain.CatenaEngine;
import org.catena.blockchain.Transaction;
import org.catena.blockchain.content.GenesisTx;
import org.catena.mine.Node;
import org.catena.util.Encryptor;
import org.json.JSONObject;

public class RunMe {

	private ArrayList<Transaction> inputTxs = null;
	private ArrayList<Transaction> outputTxs = null;

	public static void main(String[] args) throws Exception {

		RunMe run = new RunMe();
		run.startTestCase();
	}

	private void startTestCase() {
		
		CatenaEngine catena = new CatenaEngine();

		double tokenCapital = 500.0;
		String reciever  = "Milinda_Bandara";
		
		Transaction genesisTx = catena.createGenesisBlock(tokenCapital, reciever);
		this.testencryption(genesisTx.getTxSignature());

//		catena.doTransaction("Milinda_Bandara","Thilina_Bandara",300.0);		
		this.resetStates();
		
		inputTxs.add(genesisTx);
		
		Transaction tx1 = catena.createTX("Milinda_Bandara", "Thilina_Bandara", 200.0, inputTxs, outputTxs);
		
		SuperTransaction b = new SuperTransaction();
		b.proceedTransaction();
		
		this.testencryption(tx1.getTxSignature());

/*
 		this.resetStates();
		inputTxs.add(genesis);
		outputTxs.add(tx1);
		Transaction tx2 = catena.createTX("Milinda_Bandara", "Thilina_Bandara", 300.0, inputTxs, outputTxs);
		this.testencryption(tx2.getTxSignature());

		this.resetStates();
		inputTxs.add(tx1);
		inputTxs.add(tx2);
		Transaction tx3 = catena.createTX("Milinda_Bandara", "James_Bond", 200.0, inputTxs, outputTxs);
		this.testencryption(tx3.getTxSignature());

		this.resetStates();
		inputTxs.add(tx3);
		Transaction tx4 = catena.createTX("James_Bond", "Sathosi Nakamoto", 100.0, inputTxs, outputTxs);
		this.testencryption(tx4.getTxSignature());
*/
	}

	private void resetStates() {
		inputTxs = new ArrayList<Transaction>();
		outputTxs = new ArrayList<Transaction>();
	}

	private void testencryption(JSONObject data) {
		Node node = new Node();
		node.readMsg(data);
	}

	public void testEncryption() throws Exception {
		Encryptor en = new Encryptor();
		System.out.println("----- ENCRYPTION START ---------");
		String msg = "My Name is Khan";
		System.out.println(msg);
		en.encryptWithPrivateKey(msg);
		System.out.println("Public Key - " + en.getPublicKey());
		System.out.println("Message - " + en.getEncryptedMsg());

		System.out.println("----- Decrypt Message ---------");
		String decryptMsg = en.decryptWithPublicKey(en.getEncryptedMsg(), en.getPublicKey());
		System.out.println(decryptMsg);

	}
}
