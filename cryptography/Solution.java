
import java.io.*;
import java.security.*;
import java.security.spec.KeySpec;

import javax.crypto.*;
import javax.crypto.spec.DESKeySpec;

import java.util.Base64;

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
	public static void main(String[] args) throws Exception {
		Key key;
		try {
			BufferedReader br = new BufferedReader(new FileReader(new File("./cryptography/keys.ser")));
			String line;
			while ((line = br.readLine()) != null) {
				KeySpec keySpec = new DESKeySpec(convertHexStringToByteArray(line));
            	key = SecretKeyFactory.getInstance("DES").generateSecret(keySpec);
				if (encryption(key)) break;
			}
		} catch (FileNotFoundException fnfe) {
			System.out.println("Key file not found, rolling my own now \n\n");
			KeyGenerator generator = KeyGenerator.getInstance("DES");
			generator.init(new SecureRandom());
			key = generator.generateKey();
			ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream("DESSecretKey.ser"));
			out.writeObject(key);
			out.close();
		}
	}

	private static boolean encryption(Key key) throws NoSuchAlgorithmException, NoSuchPaddingException,
			InvalidKeyException, UnsupportedEncodingException, IllegalBlockSizeException, BadPaddingException {

		String plainText = "The password is URA ONI KUDAKI";
		String ciphertext = "8pRPVdSLU+XvaAQVBgJaAmuO5cLDrP8MK0ojgGayriQ=";
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

	public static byte[] convertHexStringToByteArray(String hexString) {
        if ((hexString.length() % 2) != 0) {
            throw new IllegalArgumentException("Invalid hex string (length % 2 != 0)");
        }

        byte[] array = new byte[hexString.length() / 2];
        for (int i = 0, arrayIndex = 0; i < hexString.length(); i += 2, arrayIndex++) {
            array[arrayIndex] = Integer.valueOf(hexString.substring(i, i + 2), 16).byteValue();
        }

        return array;
    }
}