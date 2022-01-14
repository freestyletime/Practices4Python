package javaMe;

// Java Program to Implement the RSA Algorithm
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.security.*;
import java.util.Base64;
import javax.crypto.Cipher;

public class RSA {

	public void writeToFile(String path, byte[] key) throws IOException {
		File f = new File(path);
		f.getParentFile().mkdirs();

		FileOutputStream fos = new FileOutputStream(f);
		fos.write(key);
		fos.flush();
		fos.close();
	}

	public static String data = "hello world";

	public static void main(String[] args) throws Exception {
		KeyPair keyPair = genKeyPair(1024);
		// 获取公钥，并以base64格式打印出来
		PublicKey publicKey = keyPair.getPublic();
		System.out.println("公钥：" + new String(Base64.getEncoder().encode(publicKey.getEncoded())));
		// 获取私钥，并以base64格式打印出来
		PrivateKey privateKey = keyPair.getPrivate();
		System.out.println("私钥：" + new String(Base64.getEncoder().encode(privateKey.getEncoded())));

		// 保存密钥
		RSA keyPairGenerator = new RSA();
		keyPairGenerator.writeToFile("javaMe/publicKey", publicKey.getEncoded());
		keyPairGenerator.writeToFile("javaMe/privateKey", privateKey.getEncoded());

		// 公钥加密
		byte[] encryptedBytes = encrypt(data.getBytes(), publicKey);
		System.out.println("加密后：");
		System.out.println(new String(encryptedBytes));
		// 私钥解密
		byte[] decryptedBytes = decrypt(encryptedBytes, privateKey);
		System.out.println("解密后：" + new String(decryptedBytes));
	}

	// 生成密钥对ƒ
	public static KeyPair genKeyPair(int keyLength) throws Exception {
		KeyPairGenerator keyPairGenerator = KeyPairGenerator.getInstance("RSA");
		keyPairGenerator.initialize(keyLength);
		return keyPairGenerator.generateKeyPair();
	}

	// 公钥加密
	public static byte[] encrypt(byte[] content, PublicKey publicKey) throws Exception {
		Cipher cipher = Cipher.getInstance("RSA");// java默认"RSA"="RSA/ECB/PKCS1Padding"
		cipher.init(Cipher.ENCRYPT_MODE, publicKey);
		return cipher.doFinal(content);
	}

	// 私钥解密
	public static byte[] decrypt(byte[] content, PrivateKey privateKey) throws Exception {
		Cipher cipher = Cipher.getInstance("RSA");
		cipher.init(Cipher.DECRYPT_MODE, privateKey);
		return cipher.doFinal(content);
	}

		/**
	* Convenience method to convert a byte to a hex string.
	*
	* @param data the byte to convert
	* @return String the converted byte
	*/
	public static String byteToHex(byte data)
	{
	StringBuffer buf = new StringBuffer();
	buf.append(toHexChar((data>>>4)&0x0F));
	buf.append(toHexChar(data&0x0F));
	return buf.toString();
	}

	
	/**
	* Convenience method to convert an int to a hex char.
	*
	* @param i the int to convert
	* @return char the converted char
	*/
	public static char toHexChar(int i)
	{
	if ((0 <= i) && (i <= 9 ))
	return (char)('0' + i);
	else
	return (char)('A' + (i-10));
	}
}
