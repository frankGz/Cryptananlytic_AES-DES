package foundation;

import java.security.InvalidKeyException;
import java.security.Key;
import java.security.NoSuchAlgorithmException;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.spec.SecretKeySpec;

import util.CryptoTools;

public class YorkModeDES {

	public static void main(String[] args) throws Exception 
	{
		byte[] IV = CryptoTools.hexToBytes("6976466F724D4F50");	
		byte[] CT1= CryptoTools.hexToBytes("437DBAB5607137A5");
		byte[] CT2=CryptoTools.hexToBytes("CFC1031114634087");
		byte[] ky = CryptoTools.hexToBytes("6B79466F724D4F50");
		
		Cipher engine = Cipher.getInstance("DES/ECB/NoPadding");
		Key myKey = new SecretKeySpec(ky, "DES");
		engine.init(Cipher.DECRYPT_MODE, myKey);
		
		byte[] pt1 = engine.doFinal(CT1);
		byte[] pt2 =engine.doFinal(CT2);
		
		byte[] me1=xor(pt1, inverse(IV));
		byte[] me2=xor(pt2, inverse(CT1));
		System.out.print(new String(me1));
		System.out.println(new String(me2));
		
	
		//byte[] ct1 = engine.doFinal(pt1);
		//byte[] ct2 = engine.doFinal(pt2);
		
	}


	public static byte[] inverse(byte[] IV)
	{
		for(int i=0;i<IV.length;i++)
		{
			IV[i]=(byte) ~IV[i];
		}
		return IV;
	}

	public static byte[] xor(byte[] x, byte[] y) throws Exception
	{
		if (x.length != y.length) throw new Exception("Lengths Mismatch!");
		
		byte[] result = new byte[x.length];
		
		for (int i = 0; i < x.length; i++)
		{
			result[i] = (byte) (x[i] ^ y[i]);
		}
		return result; 
	}
}
/*
 *  16B English string is encrypted using DES with the following parameters:
No Padding
The York Mode of Operation (more on this mode below)
The Key: 0x 6B79466F724D4F50
The IV: 0x 6976466F724D4F50
The York Mode of Operation is a custom mode that is very similar to CBC except for one difference: the plaintext of the current block is XORed with the negation of the ciphertext of the previous block. As to the very first block, its plaintext is XORed with the negation of IV. Here is a diagram that shows how this mode operates: 
Note that since this mode is not standard, you will need to implement its logic yourself --the Java cryptography extension is not aware of the York mode. Given that the ciphertext is:
       0x 437DBAB5607137A5CFC1031114634087
determine the letters of the plaintext. Express the plaintext as English characters. 
 */