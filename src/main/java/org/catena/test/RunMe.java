package org.catena.test;

import java.util.HashMap;
import java.util.Map;

import org.catena.blockchain.Transaction;
import org.catena.blockchain.content.Body;
import org.catena.mine.Node;
import org.catena.util.Encryptor;
import org.json.JSONObject;

public class RunMe {

	public static void main(String[] args) throws Exception {

		RunMe run = new RunMe();
		Transaction genesis = run.genesisBlock();
		run.testencryption(genesis.getTxSignature());
		Transaction tx1 = run.testTransaction(genesis);

		run.testencryption(tx1.getTxSignature());
		
	}

	private Transaction testTransaction(Transaction genesis) {
	
		Map<Transaction, String> prevTx = new HashMap<Transaction, String>();
		prevTx.put(genesis, genesis.getTXValue());
		Transaction tx1 = this.createBlock(genesis.getRecieverID(), 200.00, "Thilina_Namal", 0.01, prevTx, null);
	
		return tx1;
				
	}
	
	private void testencryption(JSONObject data) {
		Node node = new Node();
		node.readMsg(data);		
	}
	
	private Transaction genesisBlock() {
		Transaction genesis = new Transaction("Block_Creator", 500.0, "Milinda_Bandara", 0.01);
		Map<Transaction, String> txPair = new HashMap<Transaction, String>();
		txPair.put(genesis, genesis.getTXValue());
		
		Body auditTx = new Body();
		auditTx.setInputTxList(txPair);
		genesis.setAuditTx(auditTx );
		
		return genesis;
	}

	private Transaction createBlock(String sender, Double blockValue, String reciever, Double gas,
			Map<Transaction, String> inTxs, Map<Transaction, String> outTxs) {
		Transaction _block = new Transaction(sender, blockValue, reciever, gas);
		
		Body auditTx = new Body();
		auditTx.setInputTxList(inTxs);
		auditTx.setOutputTxList(outTxs);
		_block.setAuditTx(auditTx);

		return _block;
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
