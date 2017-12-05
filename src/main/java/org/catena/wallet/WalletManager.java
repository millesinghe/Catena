package org.catena.wallet;

import java.util.UUID;

import org.catena.util.PropertyFileHandler;

public class WalletManager {

	private PropertyFileHandler prop = null;

	public WalletManager() {
		prop = new PropertyFileHandler("catena.properties");
	}

	public void initWallet() {
		String walletID = prop.readFromPropertyFile("catena.wallet.id");
		String value = UUID.randomUUID().toString();
		if (walletID.equals("")) {
			prop.writeToPropertyFile("catena.wallet.id", value);
		}
	}

	public static void main(String[] args) throws Exception {
		WalletManager wm = new WalletManager();
		wm.initWallet();
	}

}
