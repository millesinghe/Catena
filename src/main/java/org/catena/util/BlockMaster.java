package org.catena.util;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Reader;
import java.util.ArrayList;
import java.util.List;

import org.json.JSONException;
import org.json.JSONObject;

public class BlockMaster {

	private String fileName = null;

	List<JSONObject> listOfTx = new ArrayList<JSONObject>();

	public BlockMaster(String fileName) {
		super();
		this.fileName = getClass().getClassLoader().getResource(fileName).getPath();
	}

	public void writeBlock(JSONObject writeContent) {
		BufferedWriter bw = null;
		FileWriter fw = null;

		try {

			fw = new FileWriter(this.fileName, true);
			bw = new BufferedWriter(fw);
			bw.write(writeContent.toString() + "\n");

		} catch (IOException e) {

			e.printStackTrace();

		} finally {
			try {
				if (bw != null)
					bw.close();
				if (fw != null)
					fw.close();
			} catch (IOException ex) {
				ex.printStackTrace();
			}
		}
	}

	public File[] getBlocks() {
		File folder = new File(fileName);
		File[] listOfFiles = folder.listFiles();
		return listOfFiles;
	}

	@SuppressWarnings("resource")
	public double readBlock(File file, String sender, double amount) {

		BufferedReader br = null;
		FileReader fr = null;

		try {
			fr = new FileReader(file);
			br = new BufferedReader(fr);

			String sCurrentLine;
			System.out.println("Scanned Transactions >>>>");
			while ((sCurrentLine = br.readLine()) != null) {
				JSONObject txJson = new JSONObject(sCurrentLine);

				if (sender.equals(txJson.get("reciever").toString())
						&& "false".equals(txJson.get("isSpend").toString())) {
					if (this.furtherInvestigate(file, txJson)) {
						listOfTx.add(txJson);
					}
					System.out.println("POW Tx -" + sCurrentLine);
					if (Double.parseDouble(txJson.get("value").toString()) >= amount) {
						return (amount - Double.parseDouble(txJson.get("value").toString()));
					} else {
						amount = (amount - Double.parseDouble(txJson.get("value").toString()));
					}
				}
				System.out.println(sCurrentLine);
			}

		} catch (IOException e) {

			e.printStackTrace();

		} finally {

		}
		return amount;
	}

	@SuppressWarnings("resource")
	public JSONObject readBlock(String block, int txNumber) {

		BufferedReader br = null;
		FileReader fr = null;

		try {
			fr = new FileReader(fileName+File.separator+"_block"+block);
			br = new BufferedReader(fr);

			String sCurrentLine;
			System.out.println("Scanned Transactions >>>>");
			int i = 0;

			while ((sCurrentLine = br.readLine()) != null) {
				if (i == txNumber) {
					return new JSONObject(sCurrentLine);
				}
				i++;
			}

		} catch (IOException e) {

			e.printStackTrace();

		}
		return null;
	}

	private boolean furtherInvestigate(File file, JSONObject txJson) {

		String concatId = "id-" + txJson.get("id").toString() + "-spent-" + txJson.get("isSpend").toString();

		Reader fr = null;
		int eligibleCount = 0;

		try {
			fr = new FileReader(file);

			@SuppressWarnings("resource")
			BufferedReader br = new BufferedReader(fr);

			String sCurrentLine;
			while ((sCurrentLine = br.readLine()) != null) {
				JSONObject tempJson = new JSONObject(sCurrentLine);
				String usedTxCheck = "id-" + tempJson.get("id").toString() + "-spent-"
						+ tempJson.get("isSpend").toString();

				if (usedTxCheck.equals("id-" + txJson.get("id").toString() + "-spent-true")) {
					return false;
				} else if (usedTxCheck.equals(concatId)) {
					eligibleCount++;
				}
			}
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		if (eligibleCount > 0) {
			return true;
		} else {
			return false;
		}

	}

	public List<JSONObject> getListOfTx() {
		return listOfTx;
	}

	public void setListOfTx(List<JSONObject> listOfTx) {
		this.listOfTx = listOfTx;
	}

	public static void main(String[] args) {

		BlockMaster write = new BlockMaster("_blockchain/_block01");
		write.writeBlock(null);
	}

}
