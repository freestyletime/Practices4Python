package assignments.cryptography.finalE.classical;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;
import java.util.TreeMap;

public class Classic {

    public static String getCipherText(String file) throws IOException{
        File f = new File(file);
        FileReader fr = new FileReader(f);
        StringBuffer sb = new StringBuffer();
        while (fr.ready()) sb.append((char) fr.read());
        fr.close();

        StringBuffer saver = new StringBuffer();
        String[] tokens = sb.toString().split("\\s+");
        for(String t : tokens) if(!t.isBlank() && !t.chars().allMatch(Character::isDigit)) saver.append(t);

        return saver.toString();
    }

    public static Map<String, String> getCodeBook(String file) throws IOException{
        FileInputStream fis = new FileInputStream(file);
        BufferedReader br = new BufferedReader(new InputStreamReader(fis));
        Map<String, String> cb = new HashMap<>();
        String tmp = null;
        while((tmp = br.readLine()) != null){
            String[] kv = tmp.split(":");
            cb.put(kv[0].trim(), kv[1].trim());
        }
        fis.close();
        br.close();

        return cb;
    }


    public static void main(String[] args) throws Exception {
        Map<String, Integer> counter = new HashMap<>();
        Map<String, Integer> counter1 = new HashMap<>();
        // Map<String, String> cb = getCodeBook("assignments/cryptography/finalE/classical/codeBook_21.txt");
        String cipherText = getCipherText("assignments/cryptography/finalE/classical/Classic_Probem_3.txt");

        for (String word : cipherText.split("(?<=\\G.{3})"))
             counter.put(word, counter.containsKey(word) ? counter.get(word) + 1 : 1);
        
        System.out.println(counter);
        // for (Entry<String, Integer> entry : counter.entrySet())
        //      if(entry.getValue() >= 4) counter1.put(entry.getKey(), entry.getValue());

    }
}
