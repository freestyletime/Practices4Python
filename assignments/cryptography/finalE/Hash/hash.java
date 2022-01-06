package assignments.cryptography.finalE.Hash;

import java.security.MessageDigest;
import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

public class hash {
	public static void main(String[] args) throws Exception {
	   
	     String message="h";
	     byte[] message_byte=message.getBytes();// trans from String to byte
	     MessageDigest MD= MessageDigest.getInstance("SHA-256");
	     String b0="cd0aa9856147b6c5b4ff2b7dfee5da20aa38253099ef1b4a64aced233c9afe29";
	     String b1="1b3dae70b4b0a8fd252a7879ec67283c0176729bfebc51364fb9e9fb0598ba9e";
	     String b2="58c89d709329eb37285837b042ab6ff72c7c8f74de0446b091b6a0131c102cfd";
	     String b3="b5038e9b45cfc7a0510164295a33a8968d8132459468c5fc3284d7900235c8e6";
	     String b4="7ca286583020490fb36187b2d9fd6eb8105fe11c1f8936851ecb38a742d9f376";
  
	     String v= bytesToHexstring(MD.digest(message_byte));
	     System.out.println(v);
	     

		 // |7/1 mod 2| = 1
	     String ph1=bytesToHexstring(MD.digest((b0+v).getBytes()));
		 // |7/2 mod 2| = 1
	     String ph2=bytesToHexstring(MD.digest((b1+ph1).getBytes()));
		 // |7/4 mod 2| = 1
	     String ph3=bytesToHexstring(MD.digest((b2+ph2).getBytes()));
		 // |7/8 mod 2| = 0
	     String ph4=bytesToHexstring(MD.digest((ph3+b3).getBytes()));
		 // |7/16 mod 2| = 0
	     String root=bytesToHexstring(MD.digest((ph4+b4).getBytes()));
	     System.out.println(root);

		StringBuffer sb = new StringBuffer(root);
		for(int i = 2 ; i < sb.length(); i+=3){
			sb.insert(i, ",");
		}
		String[] array = sb.toString().split(",");
		List<String> pw = new ArrayList<>();
		String pwd = "";
		for(String str : array){
			pwd = str + pwd;
		}
		System.out.println(pwd);
	}
	
	static String bytesToHexstring(byte[] bytes) {
	    StringBuilder sb = new StringBuilder();
	    for (byte b : bytes) {
	        sb.append(String.format("%02x", b));
	    }
	    return sb.toString();
	}
	
	public static String byteArrayToHex(byte[] a) {
	    StringBuilder sb = new StringBuilder(a.length * 2);
	    for (byte b : a)
	        sb.append(String.format("%02x:", b));
	    return sb.toString();
	}

	public static byte[] hexStringToBytes(String hexString) {
        hexString = hexString.toLowerCase();
        final byte[] byteArray = new byte[hexString.length() >> 1];
        int index = 0;
        for (int i = 0; i < hexString.length(); i++) {
            if (index  > hexString.length() - 1) {
                return byteArray;
            }
            byte highDit = (byte) (Character.digit(hexString.charAt(index), 16) & 0xFF);
            byte lowDit = (byte) (Character.digit(hexString.charAt(index + 1), 16) & 0xFF);
            byteArray[i] = (byte) (highDit << 4 | lowDit);
            index += 2;
        }
        return byteArray;
    }
}
