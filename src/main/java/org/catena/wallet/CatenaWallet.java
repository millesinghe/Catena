package org.catena.wallet;

import org.catena.util.PropertyFileHandler;

public class CatenaWallet {
	
	private String walletId;
	
	public CatenaWallet() {
		this.walletId = new PropertyFileHandler("catena.properties").readFromPropertyFile("catena.wallet.id");
	}
	
	public static void main(String[] args) throws Exception {
		CatenaWallet wm = new CatenaWallet();
		System.out.println(wm.walletId);
	}
}
