package org.catena.util;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.URL;
import java.util.Properties;

public class PropertyFileHandler {

	URL strFilePath = null;
	Properties prop = new Properties();
	InputStream input = null;
	OutputStream output = null;

	public PropertyFileHandler(String filePath) {
		strFilePath = getClass().getClassLoader().getResource(filePath);
	}

	public String readFromPropertyFile(String key) {
		try {
			input = new FileInputStream(strFilePath.getPath());
			prop.load(input);
			String value = prop.getProperty(key);
			return value;
		} catch (IOException ex) {
		} finally {
			if (input != null) {
				try {
					input.close();
				} catch (IOException e) {
				}
			}
		}
		return null;
	}

	public void writeToPropertyFile(String key, String value) {
		try {
			output = new FileOutputStream(strFilePath.getPath());
			prop.setProperty(key, value);
			prop.store(output, null);

		} catch (IOException io) {
		} finally {
			if (output != null) {
				try {
					output.close();
				} catch (IOException e) {
				}
			}

		}
	}
}
