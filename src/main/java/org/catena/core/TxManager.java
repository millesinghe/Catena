package org.catena.core;

import java.io.File;
import java.util.ArrayList;
import java.util.Map;

import org.catena.blockchain.Transaction;
import org.catena.util.BlockMaster;
import org.json.JSONObject;

public class TxManager {

	private ArrayList<Transaction> inputTxs = null;
	private ArrayList<Transaction> outputTxs = null;
	private BlockMaster blocks;

	public Transaction proceedTransaction(String sender, String reciever, double amount) {
		System.out.println("\n\n=============== New Transaction ============");
		this.resetStates();
		this.loadBlocks(sender, amount);
		this.filterInputTxs();
		//this.filterOutputTxs();

		return Catena.getInstance().createTX(sender, reciever, amount, inputTxs, outputTxs);

	}

	private void loadBlocks(String sender, double amount) {
		blocks = new BlockMaster("_blockchain");
		File[] fileList = blocks.getBlocks();

		for (int i = fileList.length; i > 0; i--) {
			if (0 >= blocks.readBlock(fileList[i - 1], sender, amount)) {
				i = 0;
			}
		}// balance
		System.out.println();

	}

	private void resetStates() {
		inputTxs = new ArrayList<Transaction>();
		outputTxs = new ArrayList<Transaction>();
	}

	private void filterInputTxs() {
		for (JSONObject jsonTx : blocks.getListOfTx()) {

			String id = jsonTx.getString("ID");
			String sender = jsonTx.getString("Sender");
			Double blockValue = jsonTx.getDouble("Value");
			String reciever = jsonTx.getString("Reciever");
			Double gas = jsonTx.getDouble("GAS");
			boolean isSpent = false;
			JSONObject signatureJSON = jsonTx.getJSONObject("Signature");
					
			Transaction tx = new Transaction(id, sender, blockValue, reciever, gas, signatureJSON, isSpent);
			inputTxs.add(tx);
		}

	}

	private void filterOutputTxs() {
		outputTxs.add(null);

	}

}
