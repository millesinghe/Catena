package org.catena.util;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

public class BlockWriter {

	private String fileName = null;

	public BlockWriter(String fileName) {
		super();
		this.fileName = getClass().getClassLoader().getResource(fileName).getPath();
	}

	public void writeBlock() {
		BufferedWriter bw = null;
		FileWriter fw = null;

		try {

			String content = "This is the content to write into file\n";

			fw = new FileWriter(this.fileName);
			bw = new BufferedWriter(fw);
			bw.write(content);

			System.out.println("Done");

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
		write.writeBlock();
	}

}
