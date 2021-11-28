package javaMe;

import java.util.*;
import java.util.Map.Entry;
import java.util.stream.Collectors;

public class e2 {
    public static void main(String[] args) {
        int[] i = parse("iiisdoso");
        for (int x : i)
            System.out.println(x);

        System.out.println(spinWords("Welcome"));
        System.out.println(spinWords("Hey fellow warriors"));

        int[] lst = new int[] { 107, 158, 204, 100, 118, 123, 126, 110, 116, 100 };
        System.out.println(sumOfDivided(lst));
        System.out.println(top3("  //wont won't won't ").toString());
        System.out.println(top3("e e e e DDD ddd DdD: ddd ddd aa aA Aa, bb cc cC e e e").toString());
        System.out.println(top3("  , e   .. ").toString());
        System.out.println(top3("In a village of La Mancha, the name of which I have no desire to call to"
        + "mind, there lived not long since one of those gentlemen that keep a lance"
        + "in the lance-rack, an old buckler, a lean hack, and a greyhound for"
        + "coursing. An olla of rather more beef than mutton, a salad on most"
        + "nights, scraps on Saturdays, lentils on Fridays, and a pigeon or so extra"
        + "on Sundays, made away with three-quarters of his income.").toString());
    }

    public static int[] parse(String data) {
        int cursor = 0;
        List<Integer> list = new ArrayList<>();

        for (char c : data.toCharArray()) {
            switch (c) {
                case 'i': cursor += 1; break;
                case 'd': cursor -= 1; break;
                case 's': cursor *= cursor; break;
                case 'o': list.add(cursor); break;
            }
        }
        return list.stream().mapToInt(Integer::intValue).toArray();
    }

    public static String spinWords(String sentence) {
        return Arrays.stream(sentence.split(" "))
                .map(n -> n.length() < 5 ? n : (new StringBuilder(n).reverse().toString()))
                .reduce((a, b) -> a + " " + b).get();
    }

    public static boolean isPrime(int i){
        if(i == 2) return true;
        for(int q = 2; q < i; q++) if(i % q == 0) return false;
        return true;
    }

    public static List<Integer> findPrimeNums(int i){
        List<Integer> is = new ArrayList<>();
        if(i < 0) i = - i;
        for(int p = 2; p <= i; p++)
            if((i % p == 0) && isPrime(p)) is.add(p);
        return is;
    }
    
    public static String sumOfDivided(int[] l) {
        Map<Integer, List<Integer>> ds = new HashMap<>();
        TreeMap<Integer, Integer> re = new TreeMap<>();
        Map<Integer, Integer> ct = new HashMap<>();
        StringBuilder sb = new StringBuilder();
        for(int i : l) {
            if(ds.containsKey(i)) ct.put(i, ct.containsKey(i) ? ct.get(i) + 1 : 1);
            ds.put(i, findPrimeNums(i));
        }
        for (Map.Entry<Integer, List<Integer>> entry : ds.entrySet()) {
            int k = entry.getKey();
            for(int p : entry.getValue()){
                int u = ct.containsKey(k) ? k + k * ct.get(k) : k;
                re.put(p, re.containsKey(p) ? re.get(p) + u : u);
            }
        }
        for (Map.Entry<Integer, Integer> entry : re.entrySet()) 
            sb.append("(").append(entry.getKey()).append(" ")
            .append(entry.getValue()).append(")");
        return sb.toString();
    }

    public static List<String> top3(String s) {
        String str = s.replaceAll("[^(a-zA-Z)(')]", " ").trim();
        List<String> cons = new LinkedList<>();
        if(str.isEmpty()) return cons; 
        String[] ws = str.split("\\s+");
        Map<String, Integer> rt = new HashMap<>();
        for(String w : ws) {
            if(w.matches("^[']+[a-zA-Z]*") || w.matches("[a-zA-Z]*[']+$")) continue;
            rt.put(w, rt.containsKey(w)? rt.get(w) + 1 : 1);
        }
        
        for(Entry<String, Integer> entry : rt.entrySet()) {
            int i = 0;
            while(i <= cons.size()){
                if(cons.size() == i){
                    cons.add(i, entry.getKey());
                    break;
                }else{
                    String st = cons.get(i);
                    if(rt.get(st) < entry.getValue()){
                        cons.add(i, entry.getKey());
                        break;
                    }else if(rt.get(st) == entry.getValue()){
                        if(st.compareTo(entry.getKey()) > 0) {
                            cons.add(i, entry.getKey());
                            break;
                        }
                    }
                    i +=1;
                }
            }
        }
        List<String> cons2 = new ArrayList<>();
        cons = cons.size() >= 3 ? cons.subList(0, 3) : cons.size() == 0 ? cons : cons.subList(0, cons.size());
        for (String ss : cons) cons2.add(ss.toLowerCase());
        return cons2;
    }
}
