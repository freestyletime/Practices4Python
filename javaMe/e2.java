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
}
