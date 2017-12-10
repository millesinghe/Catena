package org.catena.test;

import java.util.HashMap;
import java.util.Map;

import org.catena.blockchain.Transaction;
import org.catena.blockchain.content.Body;
import org.catena.blockchain.content.GenesisTx;
import org.catena.mine.Node;
import org.catena.util.Encryptor;
import org.json.JSONObject;

public class RunMe {

	public static void main(String[] args) throws Exception {

		RunMe run = new RunMe();

		Map<Transaction, String> inpList = new HashMap<Transaction, String>();
		Map<Transaction, String> outpList = new HashMap<Transaction, String>();

		Transaction genesis = new GenesisTx("Block_Creator", 500.0, "Milinda_Bandara", 0.01, null, null);
		run.testencryption(genesis.getTxSignature());

		inpList = new HashMap<Transaction, String>();
		outpList = new HashMap<Transaction, String>();
		inpList.put(genesis, genesis.getTXValue());
		Transaction tx1 = run.testTransaction("Milinda_Bandara", "Milinda_Bandara", 200.0, inpList, outpList);
		run.testencryption(tx1.getTxSignature());

		inpList = new HashMap<Transaction, String>();
		outpList = new HashMap<Transaction, String>();
		inpList.put(genesis, genesis.getTXValue());
		outpList.put(tx1, tx1.getTXValue());
		Transaction tx2 = run.testTransaction("Milinda_Bandara", "Thilina_Bandara", 300.0, inpList, outpList);
		run.testencryption(tx2.getTxSignature());

		inpList = new HashMap<Transaction, String>();
		outpList = new HashMap<Transaction, String>();
		inpList.put(tx1, tx1.getTXValue());
		inpList.put(tx2, tx2.getTXValue());
		Transaction tx3 = run.testTransaction("Milinda_Bandara", "James_Bond", 300.0, inpList, outpList);
		run.testencryption(tx3.getTxSignature());
	}

	private Transaction testTransaction(String sender, String reciever, double value, Map<Transaction, String> inputTx,
			Map<Transaction, String> outputTx) {
		Transaction tx1 = new Transaction(sender, value, reciever, 0.01, inputTx, outputTx);
		return tx1;
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
