package assignments.cryptography.finalE.AES;

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

public class Aes {
	public static void main(String[] args) throws Exception {

		byte[] key = {
				(byte) 0xc0,
				(byte) 0xc1,
				(byte) 0xc7,
				(byte) 0xca,
				(byte) 0x60,
				(byte) 0x31,
				(byte) 0x7b,
				(byte) 0xd8,
				(byte) 0x27,
				(byte) 0xfb,
				(byte) 0x1b,
				(byte) 0xee,
				(byte) 0x01,
				(byte) 0x30,
				(byte) 0x00,
				(byte) 0x00,
		};

		// CO C1 C7 CA 60 31 7B D8 27 FB 1B EE 01 30 A4 47
		String ciphertext = "5XWvgqrISJZ+DCXEryChjQ==";
		String plaintext = "codebreaker";
		// SecretKey sk = new SecretKeySpec(key, "AES");
		// Cipher encrypt = Cipher.getInstance("AES");
		// encrypt.init(Cipher.ENCRYPT_MODE, sk);
		// byte[] cipherText = encrypt.doFinal(plaitext.getBytes());
		// String cipher64 = Base64.getEncoder().encodeToString(cipherText);

		// System.out.println(cipher64);
		// System.out.println("Cipher hex: " + cipherText.length);
		// System.out.println("Cipher hex: " + byteArrayToHex(cipherText));
		// System.out.println("plaitext: " + byteArrayToHex(plaitext.getBytes()));

		// Cipher decrypt = Cipher.getInstance("AES");
		// decrypt.init(Cipher.DECRYPT_MODE, sk);
		// byte[] originalplaitextNot64 = Base64.getDecoder().decode(cipherplaitext1);
		// byte[] originalplaitext = decrypt.doFinal(originalplaitextNot64);

		// System.out.println(new String(originalplaitext));

		for (int i = 0; i <= 0xff; i++) {
			for (int j = 0; j <= 0xff; j++) {
				key[key.length - 1] = (byte) j;
				key[key.length - 2] = (byte) i;

				SecretKey sk = new SecretKeySpec(key, "AES");

				Cipher encrypt = Cipher.getInstance("AES/ECB/PKCS5Padding");
				encrypt.init(Cipher.ENCRYPT_MODE, sk);

				byte[] cipherText_byte = encrypt.doFinal(plaintext.getBytes());
				String cipherText_base64Str = Base64.getEncoder().encodeToString(cipherText_byte);

				// System.out.println(cipher64);
				if (cipherText_base64Str.equals(ciphertext)) {
					System.out.println(String.format("%x", i) + ":" + String.format("%x", j));
				}
			}
		}
	}
}
