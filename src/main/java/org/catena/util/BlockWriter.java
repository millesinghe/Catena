package org.catena.util;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

import org.json.JSONObject;

public class BlockWriter {

	private String fileName = null;

	public BlockWriter(String fileName) {
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

	public static void main(String[] args) {
		
		BlockWriter write = new BlockWriter("_blockchain/_block01");
		write.writeBlock(null);
	}

}
