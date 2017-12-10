package org.catena.blockchain.content;

import java.util.HashMap;
import java.util.Map;

import org.catena.blockchain.Transaction;

public class GenesisTx extends Transaction {

	public GenesisTx(String sender, Double blockValue, String reciever, Double gas, Map<Transaction, String> inpTx, Map<Transaction, String> outpTx) {
		super(sender, blockValue, reciever, gas, inpTx, outpTx);
		this.init();
	}
	
	private void init() {
		Map<Transaction, String> txPair = new HashMap<Transaction, String>();
		txPair.put(this, this.getTXValue());
		
		Body auditTx = new Body();
		auditTx.setInputTxList(txPair);
		this.setAuditTx(auditTx );
	}

}
