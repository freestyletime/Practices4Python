package assignments.cryptography.finalE.DES;

import java.io.*;
import java.security.*;
import java.security.spec.KeySpec;

import javax.crypto.*;
import javax.crypto.spec.DESKeySpec;

import java.util.Base64;
import java.util.concurrent.atomic.AtomicBoolean;

/* 
	Solution steps
 		1. There are 5 known conditions namely 
			Adopting DES encryption
			Plaintext
			Ciphertext(base64 decode)
			Key Fragment[(70:AD:57:00:F0:EF / 96 bits) (DES key standard is 16 hexs / 128 bits)]
			Mode of operation (ECB\PKCS5PAdding)
		
		2. So, we can complement the rest of the key (4 hexs / 32 bits) then 
			use this guessed key to encrypt the given plaintext and we will get the result, a new ciphertext.
		
		3. Let this new ciphertext compares the given one. 
			if they are identical, the key you guessed is correct.
			otherwise this key is incorrect.
			
		
		We can download a java file about DES encryption and decryption functions in moodle.
		And use python code produces all possible DES keys.
		(the posibility of key is 16 pow 4 equals 65536)
		Then we use java code to read these keys line by line.
		Every line we read is a DES key (we need to traslate hex string to bytes)
		Whilst use it to encrypt the Plaintext.
		Finilly, we can get a correct answer with time.
*/ 

public class Solution {

	static AtomicBoolean atom = new AtomicBoolean(false);
	public static void main(String[] args) throws Exception {
		try {
			BufferedReader br = new BufferedReader(new FileReader(new File("./assignments/cryptography/keys.ser")));
			String line;
			while ((line = br.readLine()) != null) {
				final KeySpec keySpec = new DESKeySpec(hexStringToByteArray(line));
            	final Key key = SecretKeyFactory.getInstance("DES").generateSecret(keySpec);
				atom.set(encryption(key));
				if (atom.get()) break;
			}
		} catch (FileNotFoundException fnfe) {
			System.out.println("Key file not found, rolling my own now \n\n");
		}
	}

	private static boolean encryption(final Key key) throws NoSuchAlgorithmException, NoSuchPaddingException,
			InvalidKeyException, UnsupportedEncodingException, IllegalBlockSizeException, BadPaddingException {

		String plainText = "PrimeCurios";
		String ciphertext = "g4T1HQebkEg5lHVM+lre4g==";
		byte[] pretty_key = key.getEncoded();
		String hex_output = "";
		String eye;

		for (int i = 0; i < pretty_key.length; i++) {
			eye = byteToHex(pretty_key[i]);
			hex_output += eye;
			if (i < pretty_key.length - 1) {
				hex_output += ":";
			}
		}

		// Get a cipher object
		Cipher cipher = Cipher.getInstance("DES/ECB/PKCS5Padding");

		// Encrypt the input string
		cipher.init(Cipher.ENCRYPT_MODE, key);
		byte[] stringBytes = plainText.getBytes("UTF8");
		byte[] raw = cipher.doFinal(stringBytes);
		byte[] encodedBytes = Base64.getEncoder().encode(raw);
		String b64output = new String(encodedBytes);

		if (b64output.equals(ciphertext)) {
			System.out.println("__________________________________________\n");
			System.out.println("Successful Decryption");
			System.out.println("Full key is " + hex_output + "\n");
			System.out.println("Ciphertext (in Base64) is  " + b64output + "\n");
			System.out.println("__________________________________________\n");
			return true;
		} else {
			System.out.println("__________________________________________\n");
			System.out.println("Failed Decryption");
			System.out.println("Key is " + hex_output + "\n");
			System.out.println("Ciphertext (in Base64) is  " + b64output + "\n");
			System.out.println("__________________________________________\n");
			return false;
		}
	}

	/**
	 * Convenience method to convert a byte to a hex string.
	 *
	 * @param data the byte to convert
	 * @return String the converted byte
	 */
	public static String byteToHex(byte data) {
		StringBuffer buf = new StringBuffer();
		buf.append(toHexChar((data >>> 4) & 0x0F));
		buf.append(toHexChar(data & 0x0F));
		return buf.toString();
	}

	/**
	 * Convenience method to convert an int to a hex char.
	 *
	 * @param i the int to convert
	 * @return char the converted char
	 */
	public static char toHexChar(int i) {
		if ((0 <= i) && (i <= 9))
			return (char) ('0' + i);
		else
			return (char) ('A' + (i - 10));
	}

	public static byte[] hexStringToByteArray(String s) {
        int len = s.length();
        byte[] data = new byte[len / 2];
        try {
            for (int i = 0; i < len; i += 2) {
                data[i / 2] = (byte) ((Character.digit(s.charAt(i), 16) << 4)
                        + Character.digit(s.charAt(i+1), 16));
            }
        } catch (Exception e) {
           // Log.d("", "Argument(s) for hexStringToByteArray(String s)"+ "was not a hex string");
        }
        return data;
    }
}