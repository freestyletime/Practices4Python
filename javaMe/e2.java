package javaMe;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class e2 {
    public static void main(String[] args) {
        int[] i = parse("iiisdoso");
        for (int x : i)
            System.out.println(x);

        System.out.println(spinWords("Welcome"));
        System.out.println(spinWords("Hey fellow warriors"));
        System.out.println(factors(86240));
        System.out.println(factors(7775460));
        System.out.println(factors(10705716));

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

    /** Check the prime number */
    static boolean isPrime(int n) {
        if (n <= 1) return false; 
        else if (n == 2) return true;
        else if (n % 2 == 0) return false;
        for (int i = 3; i <= Math.sqrt(n); i += 2) if (n % i == 0) return false;
        return true;
    }

    public static String factors(int n) {
        StringBuilder result = new StringBuilder();
        for (int i = 2;; i++) {
            if(isPrime(n)) {
                result.append("(" + n + ")");
                return result.toString();
            }
            if (isPrime(i)) {
                for (int r = 0, m = 1;; m++) {
                    double p = Math.pow(i, m);
                    if (n % p == 0)
                        r = m;
                    else if(r != 0){
                        n /= Math.pow(i, r);
                        if (r == 1) result.append("(" + i + ")");
                        else result.append("(" + i + "**" + r + ")");
                        if (n == 1) return result.toString();
                        else break;
                        } else break;
                }
            }
        }
    }
}
