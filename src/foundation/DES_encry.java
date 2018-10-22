package foundation;

import java.io.UnsupportedEncodingException;
import java.security.Key;
import java.util.BitSet;

import javax.crypto.Cipher;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.spec.SecretKeySpec;

import util.CryptoTools;

public class DES_encry {

	public static void main(String[] args) throws UnsupportedEncodingException, NoSuchPaddingException, Exception
	{
		byte[] pt="Facebook".getBytes();
		byte[] key="universe".getBytes();
		byte[] ct2;
		byte[] ct1;
		byte[] difference;
		
		BitSet bitset = BitSet.valueOf(pt);
		String out;
		for(int i=0;i<3;i++)
		{
			int rand=(int) (Math.random()*63);
			ct2=encrypt(pt,key);
			bitset.flip(rand);
			pt=bitset.toByteArray();
			ct1=encrypt(pt,key);
			difference=xor(ct1, ct2);
			out =new String(difference,"UTF-8");
			System.out.println(out);
			System.out.println(CryptoTools.bytesToBin(difference));
		}
		
	
	}
	
	public static byte[] encrypt (byte[] pt,byte[] key) throws UnsupportedEncodingException, Exception, NoSuchPaddingException
	{
		
		Cipher engine=Cipher.getInstance("DES/ECB/NoPadding");
		Key myKey=new SecretKeySpec(key,"DES");
		engine.init(Cipher.ENCRYPT_MODE, myKey);
		byte[] ct=engine.doFinal(pt);
		return ct;
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
 * Activity B1

1.	- 0.5 * lg0.5 – 0.25 * lg0.25 – 0.25 * lg0.25 = 1.5
Compression: 1  01  001

2.	 
3.	因为OTP的性质，exhaust attack会尝试所有结，并把所有有意义的组合都列出来，无法找到真正答案，而CASHNOTNEEDED在循环考前的位置，C开头
*/
 

