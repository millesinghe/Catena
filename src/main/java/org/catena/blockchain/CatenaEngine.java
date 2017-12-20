package org.catena.blockchain;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import org.catena.blockchain.content.GenesisTx;
import org.catena.util.BlockWriter;
import org.json.JSONObject;

public class CatenaEngine {
	
	public Transaction createGenesisBlock(double tokenCapital, String reciever) {
		Transaction genesis = new GenesisTx("Genesis_Owner", tokenCapital, reciever, 0.01);
		doTransaction(genesis);
		return genesis;
	}
	
	public Transaction createTX(String sender, String reciever, double value, ArrayList<Transaction> inputTxs, ArrayList<Transaction> outputTxs) {
		Map<Transaction, String> inputTx = new HashMap<Transaction, String>();
		Map<Transaction, String> outputTx = new HashMap<Transaction, String>();
		
		if (inputTxs != null) {
			for(Transaction tx : inputTxs) {
				inputTx.put(tx, tx.getTXValue());	
			}
		}
		if (outputTxs != null) {
			for(Transaction tx : outputTxs) {
				outputTx.put(tx, tx.getTXValue());	
			}
		}		
		Transaction tx1 = new Transaction(sender, value, reciever, 0.01, inputTx, outputTx);
		
		this.doTransaction(tx1);
		
		return tx1;
	}
	
	private void doTransaction(Transaction tx1) {
		BlockWriter write = new BlockWriter("_blockchain/_block01");
		JSONObject writeContent = tx1.addToJSONTx();
		write.writeBlock(writeContent); 
	}
	
	public void doTransaction(String sender, String reciever, double amount) {
		
	}

}
