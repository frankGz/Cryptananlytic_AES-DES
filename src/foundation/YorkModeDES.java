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
