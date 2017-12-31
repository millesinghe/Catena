package org.catena.test;

import java.io.File;

import org.catena.blockchain.Transaction;
import org.catena.core.Catena;
import org.catena.core.Node;
import org.catena.core.TxManager;
import org.catena.exception.InsufficientFundsException;
import org.catena.util.BlockMaster;
import org.catena.util.Encryptor;
import org.json.JSONObject;

public class RunMe {



	public static void main(String[] args) throws Exception {

		RunMe run = new RunMe();
		run.startTestCase();
		run.verifyTxs("01",1);
	}

	private void verifyTxs(String blockNumber, int txNo) {
		System.out.println("VERIFY TRANSACTION "+(txNo + 1) +" OF BLOCK "+blockNumber+" DETAILS >>>>>>>>>>>");
		BlockMaster blocks = new BlockMaster("_blockchain");
		JSONObject jsonTx = blocks.readBlock(blockNumber, txNo);
		
		TxManager txm = new TxManager();
		Transaction tx = txm.buildTxbyJSON(jsonTx);
		//this.testencryption(tx.getTxSignature());	
		
	}

	private void startTestCase() {
		
		double tokenCapital = 500.0;
		String reciever  = "Milinda_Bandara";
		
		Transaction genesisTx = Catena.getInstance().createGenesisBlock(tokenCapital, reciever);
		this.testencryption(genesisTx.getTxSignature());

		TxManager b = new TxManager();
		try {
			Transaction aa = b.executeTx(reciever, "Thilina_Bandara", 700.0);
			this.testencryption(aa.getTxSignature());
		} catch (InsufficientFundsException e) {
			e.printStackTrace();
		}	
	
		//reciever  = "Thilina_Bandara";
		//aa = b.executeTx(reciever, "Saharsha_Rathnasiri", 100.0);
		//this.testencryption(aa.getTxSignature());
//		this.testencryption(tx1.getTxSignature()); -- need to imp

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
