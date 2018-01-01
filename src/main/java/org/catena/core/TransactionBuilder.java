package org.catena.core;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import org.catena.blockchain.GenesisTx;
import org.catena.blockchain.Transaction;
import org.json.JSONObject;

public class TransactionBuilder {

	private static TransactionBuilder instance;

	private TransactionBuilder() {
	}

	public static synchronized TransactionBuilder getInstance() {
		if (instance == null) {
			instance = new TransactionBuilder();
		}
		return instance;
	}

	public Transaction createGenesisBlock(double tokenCapital, String reciever) {
		Transaction genesis = new GenesisTx("Genesis_Owner", tokenCapital, reciever, 0.01);
		genesis.doTransaction();
		return genesis;
	}

	public Transaction createTX(String sender, String reciever, double value, ArrayList<Transaction> inputTxs,
			ArrayList<Transaction> outputTxs) {
		
		Transaction tx = new Transaction(sender, value, reciever, 0.01,inputTxs, false);
		tx.doTransaction();
		return tx;
	}

	
}
