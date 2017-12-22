package org.catena.util;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

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

			fw = new FileWriter(this.fileName,true);
			bw = new BufferedWriter(fw);
			bw.write(writeContent.toString()+"\n");

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
				
				if (sender.equals(txJson.get("Reciever").toString())){
					listOfTx.add(txJson);
					System.out.println("Proof Tx -"+ sCurrentLine);
					if(Double.parseDouble(txJson.get("Value").toString()) >= amount){
						return (amount - Double.parseDouble(txJson.get("Value").toString()));
					}else {
						amount = (amount - Double.parseDouble(txJson.get("Value").toString()));
					}
				}
				System.out.println(sCurrentLine);
			}

		} catch (IOException e) {

			e.printStackTrace();

		} finally {
			
		}
		return 0;
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
