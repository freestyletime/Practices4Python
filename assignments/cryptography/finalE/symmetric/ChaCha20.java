package assignments.cryptography.finalE.symmetric;

import java.io.FileInputStream;
import java.io.ObjectInputStream;
import java.security.Key;
import java.util.Base64;
import javax.crypto.Cipher;
import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;
import javax.crypto.spec.ChaCha20ParameterSpec;
import javax.crypto.spec.SecretKeySpec;

public class ChaCha20 {
    static String plainText = "This is a plain text which will be encrypted by ChaCha20 Algorithm";

    public static void main(String[] args) throws Exception {
        KeyGenerator keyGenerator = KeyGenerator.getInstance("ChaCha20");
        keyGenerator.init(256);

        byte[] encodedKey     = { 
            (byte) 0xca, (byte) 0xb4, (byte) 0x56, (byte) 0xaf,
            (byte) 0x43, (byte) 0xc9, (byte) 0x37, (byte) 0x0e,
            (byte) 0xac, (byte) 0xb5, (byte) 0x45, (byte) 0x94,
            (byte) 0xa5, (byte) 0x08, (byte) 0x95, (byte) 0x6d,
            (byte) 0x54, (byte) 0xe2, (byte) 0x57, (byte) 0xf9,
            (byte) 0xcc, (byte) 0xc5, (byte) 0xa2, (byte) 0xc0,
            (byte) 0x21, (byte) 0x41, (byte) 0xd3, (byte) 0xa6,
            (byte) 0xf3, (byte) 0x30, (byte) 0x94, (byte) 0x08,
        };
        SecretKey key = new SecretKeySpec(encodedKey, 0, encodedKey.length, "ChaCha20");

        // System.out.println("Original Text  : " + plainText);

        String c = "IjbEsfe86phPa2+6sWZOSE/52Gnx7ZqZMeTLTg==";
        byte[] cipherText = Base64.getDecoder().decode(c);
        // encrypt(plainText.getBytes(), key);
        // System.out.println("Encrypted Text : " + Base64.getEncoder().encodeToString(cipherText));

        String decryptedText = decrypt(cipherText, key);
        System.out.println("DeCrypted Text : " + decryptedText);

    }

    public static byte[] encrypt(byte[] plaintext, SecretKey key) throws Exception {
        byte[] nonceBytes = new byte[12];
        int counter = 5;

        // Get Cipher Instance
        Cipher cipher = Cipher.getInstance("ChaCha20");

        // Create ChaCha20ParameterSpec
        ChaCha20ParameterSpec paramSpec = new ChaCha20ParameterSpec(nonceBytes, counter);

        // Create SecretKeySpec
        SecretKeySpec keySpec = new SecretKeySpec(key.getEncoded(), "ChaCha20");

        // Initialize Cipher for ENCRYPT_MODE
        cipher.init(Cipher.ENCRYPT_MODE, keySpec, paramSpec);

        // Perform Encryption
        byte[] cipherText = cipher.doFinal(plaintext);

        return cipherText;
    }

    public static String decrypt(byte[] cipherText, SecretKey key) throws Exception {
        byte[] nonceBytes = new byte[12];
        int counter = 5;

        // Get Cipher Instance
        Cipher cipher = Cipher.getInstance("ChaCha20");

        // Create ChaCha20ParameterSpec
        ChaCha20ParameterSpec paramSpec = new ChaCha20ParameterSpec(nonceBytes, counter);

        // Create SecretKeySpec
        SecretKeySpec keySpec = new SecretKeySpec(key.getEncoded(), "ChaCha20");

        // Initialize Cipher for DECRYPT_MODE
        cipher.init(Cipher.DECRYPT_MODE, keySpec, paramSpec);

        // Perform Decryption
        byte[] decryptedText = cipher.doFinal(cipherText);
        return new String(decryptedText, "UTF8");
    }
}
