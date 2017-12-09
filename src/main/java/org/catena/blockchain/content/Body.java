package org.catena.blockchain.content;

import java.util.Map;

import org.catena.blockchain.Transaction;

public class Body {

	private Map<Transaction, String> inputTxList;

	private Map<Transaction, String> outputTxList;

	public Map<Transaction, String> getInputTxList() {
		return inputTxList;
	}

	public void setInputTxList(Map<Transaction, String> inputTxList) {
		this.inputTxList = inputTxList;
	}

	public Map<Transaction, String> getOutputTxList() {
		return outputTxList;
	}

	public void setOutputTxList(Map<Transaction, String> outputTxList) {
		this.outputTxList = outputTxList;
	}
	
	
}
