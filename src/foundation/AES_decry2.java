package foundation;

import java.security.Key;

import javax.crypto.Cipher;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;

import util.CryptoTools;

public class AES_decry2 {

	public static void main(String[] args) throws Exception {
		// TODO Auto-generated method stub
		byte[] keyBytes = "DO NOT TELL EVE!".getBytes();//  (expressed as an English string)
	    byte[] ivBytes  = CryptoTools.hexToBytes("20FC19123087BF6CAC8D0F1254123004");
	    byte[] ct = CryptoTools.hexToBytes("3188073EA5DB3F5C05B6307B3595607135F5D4B22F2C3EB710AA31377F78B997");
	    Key key = new SecretKeySpec(keyBytes, "AES");
	    IvParameterSpec iv = new IvParameterSpec(ivBytes);
	    Cipher engine = Cipher.getInstance("AES/CBC/PKCS5Padding");
	    
	    engine.init(Cipher.DECRYPT_MODE, key, iv);
	    
	    byte[] pt = engine.doFinal(ct);
	    System.out.println(new String(pt));
	}

}

/*
 * Alice sends messages to Bob using AES/CBC/PKCS5Padding with an 
IV that is incremented by 1 after each message. If Eve knows that the 
first block of the plaintext of any message is either "True" or "False", 
can she mount an attack that decrypts the first block of a given message? 
Write your answer (Yes or No) and justify it. Hint: CPA. 


True
What you get back is Xord with the original Iv and the next
string . So you get the cipher text and xor it with the IV's and
whatever it xors, then if you have the key you can just make sure the
CT's are the same
 */
