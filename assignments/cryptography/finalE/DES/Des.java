package assignments.cryptography.finalE.DES;

import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;
import javax.crypto.spec.GCMParameterSpec;
import javax.crypto.spec.SecretKeySpec;

import java.io.ByteArrayInputStream;
import java.io.FileInputStream;
import java.io.ObjectInputStream;
import java.math.BigInteger;
import java.nio.ByteBuffer;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Base64;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

import javax.crypto.Cipher;

public class Des {

	// Key generator type

	public static void main(String[] args) throws Exception {
		// ----------------------
		String ciphertext_string = "g4T1HQebkEg5lHVM+lre4g==";
		byte[] ciphertext_byte = ciphertext_string.getBytes();
		byte[] ciphertext_base64 = Base64.getDecoder().decode(ciphertext_byte);
		String plaintext = "PrimeCurios";
		byte[] plaintext_byte = plaintext.getBytes();
		byte[] key = { (byte) 0x04, (byte) 0x91, (byte) 0x04, (byte) 0xcd,
				(byte) 0x57, (byte) 0x01, (byte) 0x00, (byte) 0x00};// number of 8 ee

		for (int i = 0; i <= 0xff; i++) {
			key[6] = (byte) i;
			for (int j = 0; j <= 0xff; j++) {
				key[7] = (byte) j;

				SecretKey DESkey = new SecretKeySpec(key, "DES");
				Cipher encryptCipher = Cipher.getInstance("DES");
				encryptCipher.init(Cipher.ENCRYPT_MODE, DESkey);
				byte[] newciphertext_byte = encryptCipher.doFinal(plaintext.getBytes());
				String newCiphertext_string = Base64.getEncoder().encodeToString(newciphertext_byte);
				if (newCiphertext_string.equals(ciphertext_string)) {
					System.out.println(byteArrayToHex(key));
					return;
				}
			}
		}
		/*
		 * to decrypt
		 * SecretKey DESkey =?
		 * Cipher decryptCipher = Cipher.getInstance("DES");
		 * decryptCipher.init(Cipher.DECRYPT_MODE, DESkey);
		 * 
		 * byte[] newplaintext_byte= decryptCipher.doFinal(ciphertext_base64);
		 * 
		 * System.out.println("Decrypted message: " + new String(newplaintext_byte));
		 * System.out.println(ciphertext_string);
		 * System.out.println(byteArrayToHex(key));
		 * 
		 */
	}

	public static String byteArrayToHex(byte[] a) {
		StringBuilder sb = new StringBuilder(a.length * 2);
		for (byte b : a) sb.append(String.format("%02x:", b));
		return sb.toString();
	}
}