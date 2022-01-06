package assignments.cryptography.finalE;

import java.math.BigInteger;

import assignments.cryptography.finalE.Crypto.Utils;

public class Test {
    public static void main(String[] args) throws Exception{
        // Get the byte array, b, of the hash of the message file.
        // Construct a new byte array,0b, with a zero byte at index 0 then b.
        // Construct a BigInteger, B, from 0b.
        // The hash value is B.mod(p).
        
        String message_1 = "Signatures are so much fun";
        String message_2 = "to play with";

        /*** SHA256 */  
        Crypto _c = new Crypto();
        Crypto.HASH hash = _c .new HASH(); 
        String digest_sha256_1 = hash.SHA256(message_1);
        String digest_sha256_2 = hash.SHA256(message_2);
        byte[]  b_1 = digest_sha256_1.getBytes();
        byte[]  b_2 = digest_sha256_2.getBytes();

        byte[] byte1 = new byte[b_1.length + 1];
        for (int i = 0; i <= b_1.length; i ++){
            if(i == 0) byte1[i] = (byte) 0x00;
            else byte1[i] = b_1[i - 1];
        }

        byte[] byte2 = new byte[b_2.length + 1];
        for (int i = 0; i <= b_2.length; i ++){
            if(i == 0) byte2[i] = (byte) 0x00;
            else byte2[i] = b_2[i - 1];
        }

        // String hex_output = "";
        // String eye;
        // for (int i = 0; i < b_1.length; i++) {
		// 	eye = byteToHex(b_1[i]);
		// 	hex_output += eye;
		// 	if (i < b_1.length - 1) {
		// 		hex_output += ":";
		// 	}
		// }

        // System.out.println(hex_output);


        BigInteger b1 = new BigInteger(b_1);
        BigInteger b2 = new BigInteger(b_2);
        BigInteger p = new BigInteger("1108763003735791619954956188174516481632915747603958198829971");

        System.out.println(b1.mod(p));
        System.out.println(b2.mod(p));
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
}
