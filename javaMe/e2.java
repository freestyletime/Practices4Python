package javaMe;

import java.util.*;

public class e2 {
    public static void main(String[] args) {
        int[] i = parse("iiisdoso");
        for (int x : i)
            System.out.println(x);

        System.out.println(spinWords("Welcome"));
        System.out.println(spinWords("Hey fellow warriors"));

        int[] lst = new int[] {107, 158, 204, 100, 118, 123, 126, 110, 116, 100};
        System.out.println(sumOfDivided(lst));
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
}
