package org.catena.core;

import java.io.File;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Map;

import org.catena.blockchain.Transaction;
import org.catena.util.BlockMaster;
import org.json.JSONObject;

public class TxManager {

	private ArrayList<Transaction> inputTxs = null;
	private ArrayList<Transaction> outputTxs = null;
	private BlockMaster blocks;

	public Transaction executeTx(String sender, String reciever, double amount) {
		System.out.println("\n\n=============== New Transaction ============");
		this.resetStates();
		this.loadBlocks(sender, amount);
		this.filterInputTxs();
		// this.filterOutputTxs();

		return Catena.getInstance().createTX(sender, reciever, amount, inputTxs, outputTxs);

	}

	private void loadBlocks(String sender, double amount) {
		blocks = new BlockMaster("_blockchain");
		File[] fileList = blocks.getBlocks();
		double balance = 0.0;
		for (int i = fileList.length; i > 0; i--) {
			balance = blocks.readBlock(fileList[i - 1], sender, amount);
			if (0 >= balance) {
				i = 0;
			}
		} // balance
			// burn transactions
		this.createBalanceTranscation(sender, (-1 * balance),blocks.getListOfTx().get(0));
		this.burnTranasactions();
		System.out.println();

	}

	private void createBalanceTranscation(String sender, double balanceAmount, JSONObject jsonObject) {
		Transaction tx = this.buildTxbyJSON(jsonObject);
		inputTxs.add(tx );
		
		Transaction balanceTx = new Transaction(sender, balanceAmount, sender, 0.0, inputTxs, false);
		balanceTx.doTransaction();
		outputTxs.add(balanceTx);
	}

	private Transaction buildTxbyJSON(JSONObject jsonTx) {
		Transaction objTx = new Transaction(jsonTx.getString("id"), jsonTx.getString("reciever"),
				jsonTx.getDouble("value"), jsonTx.getString("reciever"), jsonTx.getDouble("gas"), jsonTx.getJSONObject("signature"), true);
		return objTx;		
	}
	
	private void burnTranasactions() {
		for (JSONObject txBlock : blocks.getListOfTx()) {
			/*
			 * txBlock.put("isSpend", true); txBlock.get("isSpend");
			 * txBlock.put("time", new
			 * Timestamp(System.currentTimeMillis()).getTime());
			 * txBlock.put("gas", 0.00);
			 */
			Transaction burnTx = new Transaction(txBlock.getString("id"), txBlock.getString("reciever"),
					txBlock.getDouble("value"), "INTERNAL", 0.0, txBlock.getJSONObject("signature"), true);
			burnTx.doTransaction();
			System.out.println();
		}
	}

	private void resetStates() {
		inputTxs = new ArrayList<Transaction>();
		outputTxs = new ArrayList<Transaction>();
	}

	private void filterInputTxs() {
		if (blocks != null) {
			for (JSONObject jsonTx : blocks.getListOfTx()) {

				String id = jsonTx.getString("id");
				String sender = jsonTx.getString("sender");
				Double blockValue = jsonTx.getDouble("value");
				String reciever = jsonTx.getString("reciever");
				Double gas = jsonTx.getDouble("gas");
				boolean isSpent = jsonTx.getBoolean("isSpend");
				JSONObject signatureJSON = jsonTx.getJSONObject("signature");

				Transaction tx = new Transaction(id, sender, blockValue, reciever, gas, signatureJSON, isSpent);
				inputTxs.add(tx);
			}
		}
	}

	private void filterOutputTxs() {
		outputTxs.add(null);

	}

}
