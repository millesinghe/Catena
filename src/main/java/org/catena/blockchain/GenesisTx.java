package org.catena.blockchain;

public class GenesisTx extends Transaction {

	public GenesisTx(String sender, Double blockValue, String reciever, Double gas) {
		super(sender, blockValue, reciever, gas, null);
	}
	


}
