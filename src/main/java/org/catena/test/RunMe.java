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
		Transaction genesis = new GenesisTx("Block_Creator", 500.0, "Milinda_Bandara", 0.01, null);
		run.testencryption(genesis.getTxSignature());

		Transaction tx1 = run.testTransaction(genesis, "Thilina_Namal", 200.0);
		run.testencryption(tx1.getTxSignature());

		Transaction tx2 = run.testTransaction(genesis, "Milinda_Bandara", 300.0);
		run.testencryption(tx2.getTxSignature());
	}

	private Transaction testTransaction(Transaction prevTx, String reciever, double value) {
		Map<Transaction, String> inpList = new HashMap<Transaction, String>();
		inpList.put(prevTx, prevTx.getTXValue());
		Transaction tx1 = new Transaction(prevTx.getRecieverID(), value, reciever, 0.01, inpList);
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
