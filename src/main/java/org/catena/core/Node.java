package org.catena.core;

import java.io.UnsupportedEncodingException;
import java.security.InvalidKeyException;

import javax.crypto.BadPaddingException;
import javax.crypto.IllegalBlockSizeException;

import org.catena.util.Encryptor;
import org.json.JSONException;
import org.json.JSONObject;

public class Node {

	public String readMsg(JSONObject data) {	
		try {
			String result = Encryptor.getInstance().decryptWithPublicKey(data.getString("msg"), data.getString("key"));
			System.out.println(">>> CATENASCAN >>> "+result);
			return result;
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
		return null;
		
	}
}
