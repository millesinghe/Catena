package org.catena.core;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import org.catena.blockchain.GenesisTx;
import org.catena.blockchain.Transaction;
import org.catena.util.BlockMaster;
import org.json.JSONObject;

public class Catena {

	private static Catena instance;

	private Catena() {
	}

	public static synchronized Catena getInstance() {
		if (instance == null) {
			instance = new Catena();
		}
		return instance;
	}

	public Transaction createGenesisBlock(double tokenCapital, String reciever) {
		Transaction genesis = new GenesisTx("Genesis_Owner", tokenCapital, reciever, 0.01);
		doTransaction(genesis);
		return genesis;
	}

	public Transaction createTX(String sender, String reciever, double value, ArrayList<Transaction> inputTxs,
			ArrayList<Transaction> outputTxs) {
		
		Transaction tx1 = new Transaction(sender, value, reciever, 0.01,inputTxs);
		this.doTransaction(tx1);
		return tx1;
	}

	private void doTransaction(Transaction tx1) {
		BlockMaster write = new BlockMaster("_blockchain/_block01");
		JSONObject writeContent = tx1.addToJSONTx();
		write.writeBlock(writeContent);
	}
}
