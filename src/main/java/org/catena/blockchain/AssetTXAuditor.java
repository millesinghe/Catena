package org.catena.blockchain;

import java.util.List;
import java.util.Map;

public class AssetTXAuditor {

	private List<Map<Transaction, Double>> inputTxList;

	private List<Map<Transaction, Double>> outputTxList;

	public List<Map<Transaction, Double>> getInputTxList() {
		return inputTxList;
	}

	public void setInputTxList(List<Map<Transaction, Double>> inputTxList) {
		this.inputTxList = inputTxList;
	}

	public List<Map<Transaction, Double>> getOutputTxList() {
		return outputTxList;
	}

	public void setOutputTxList(List<Map<Transaction, Double>> outputTxList) {
		this.outputTxList = outputTxList;
	}
	
	
}
