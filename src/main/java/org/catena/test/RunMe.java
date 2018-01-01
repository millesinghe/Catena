package org.catena.test;

import java.io.File;

import org.catena.blockchain.Transaction;
import org.catena.core.Catena;
import org.catena.core.TransactionBuilder;
import org.catena.core.Node;
import org.catena.core.TxManager;
import org.catena.exception.InsufficientFundsException;
import org.catena.util.Encryptor;
import org.json.JSONObject;

public class RunMe {



	public static void main(String[] args) throws Exception {

		RunMe run = new RunMe();
		//run.startGensesisTestCase();
		run.verifyTxs("02",5);
		//System.out.println(run.checkBalanace("Thilina_Bandara"));
		//run.startNewTXBlock();
	}

	private void startNewTXBlock() {
		TxManager b = new TxManager();
		Transaction aa;
		try {
			aa = b.executeTx("Milinda_Bandara", "Saharasha_Rathnasiri", 100.0);
			this.testencryption(aa.getTxSignature());	
		} catch (InsufficientFundsException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}	
	}

	private double checkBalanace(String ownerId) {
		Catena blockchain = new Catena("_blockchain");
		return blockchain.getFundAmount(ownerId);
	}

	private void verifyTxs(String blockNumber, int txNo) {
		System.out.println("VERIFY TRANSACTION "+(txNo + 1) +" OF BLOCK "+blockNumber+" DETAILS >>>>>>>>>>>");
		Catena blocks = new Catena("_blockchain");
		JSONObject jsonTx = blocks.readBlock(blockNumber, txNo);
		
		TxManager txm = new TxManager();
		Transaction tx = txm.buildTxbyJSON(jsonTx);
		//this.testencryption(tx.getTxSignature());	
		
	}

	private void startGensesisTestCase() {
		
		double tokenCapital = 500.0;
		String reciever  = "Milinda_Bandara";
		
		Transaction genesisTx = TransactionBuilder.getInstance().createGenesisBlock(tokenCapital, reciever);
		this.testencryption(genesisTx.getTxSignature());

		TxManager b = new TxManager();
		try {
			Transaction aa = b.executeTx(reciever, "Thilina_Bandara", 200.0);
			this.testencryption(aa.getTxSignature());
			

			aa = b.executeTx(reciever, "Thilina_Bandara", 100.0);
			this.testencryption(aa.getTxSignature());
			
			aa = b.executeTx( "Thilina_Bandara", "Saharasha_Rathnasiri", 250.0);
			this.testencryption(aa.getTxSignature());
			
		} catch (InsufficientFundsException e) {
			e.printStackTrace();
		}	

		
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

// ================================================
	

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
