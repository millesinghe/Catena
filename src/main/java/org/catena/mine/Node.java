package org.catena.mine;

import java.io.UnsupportedEncodingException;
import java.security.InvalidKeyException;

import javax.crypto.BadPaddingException;
import javax.crypto.IllegalBlockSizeException;

import org.catena.util.Encryptor;
import org.json.JSONException;
import org.json.JSONObject;

public class Node {

	public void readMsg() {
		String tx = "{\"msg\":\"OBAdq7aWcAkk1fs48fnvIfI9mqVgzPIjt7NlXarYkLth3/0MIPG6UT53BSIBrcNENDHOOxAkPhsDSyf834CbmPehtqeFluZxtMONUrclCVhoBlBVT1SeBg8EZVX6BUHid61lo6OhFebxXF6wej9zcU9ytJEGdHe5EJ0otqQw8CA=\",\"key\":\"MIGfMA0GCSqGSIb3DQEBAQUAA4GNADCBiQKBgQCFGPHGZesaWh45rqjlcjaeoRyRBh3138wr6l50Fp2BKNu+44Muq1s86KzFbu45f9y2qaGnTEMJjiPJz5ZPyrfalNFJZ+jb8Xm/fe3uUdhX8NZmPtAALvedaBq4bO9ctW2lxownqkSkuIiTPZ/fFVnJKzucbexWCEI2Jp8ujtkMsQIDAQAB\"}\r\n";
		JSONObject data = new JSONObject(tx);
		
		try {
			String result = Encryptor.getInstance().decryptWithPublicKey(data.getString("msg"), data.getString("key"));
			System.out.println(result);
		} catch (InvalidKeyException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IllegalBlockSizeException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (BadPaddingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
}
