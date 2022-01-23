package javaMe;

import java.util.Arrays;
import java.util.Comparator;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.TreeMap;
import java.util.Map.Entry;

/*
 * Chris Chen 
 * christianchen@gmail.com 
 * Created on Sun Oct 17 2021
 * ^V^ Enjoy every day.
 */
public class e1 {
    public static void main(String[] args) {
        System.out.println(solution("abc", "bc"));
        System.out.println(solution("abc", "d"));
        System.out.println(findIt(new int[] { 0, 1, 0, 1, 0 }));
        System.out.println(Tickets(new int[] { 25, 25, 50 }));
        System.out.println(Tickets(new int[] { 25, 25, 50, 50, 100 }));
        System.out.println(order("4of Fo1r pe6ople g3ood th5e the2"));
        System.out.println(order2("Fo1r the2 g3ood 4of th5e pe6ople"));
        System.out.println(order("Empty input should return empty string"));

        System.out.println(GetSum(-1, 2));
        System.out.println(GetSum(1, -4));
        int[] result = twoSum(new int[] { 1, 2, 3 }, 4);
        System.out.println(result[0]);
        System.out.println(result[1]);
        System.out.println(pigIt("Pig latin is cool"));
        System.out.println(pigIt("This is my string"));
        System.out.println(toRoman(8));
        System.out.println(fromRoman("MMDCXLVIII"));
    }

    public static boolean solution(String str, String ending) {
        return str.endsWith(ending);
    }

    public static int findIt(int[] a) {
        int result = 0;
        for (int n : a) {
            result = n ^ result;
        }
        return result;
    }

    public static String Tickets(int[] peopleInLine) {
        String yes = "YES";
        String no = "NO";

        if (peopleInLine.length == 0)
            return no;
        else {
            int ticket = 25;
            int c_25 = 0;
            int c_50 = 0;

            for (int m : peopleInLine) {
                int change = m - ticket;
                switch (change) {
                    case 0:
                        c_25 += 1;
                        break;
                    case 25:
                        if (c_25 > 0) {
                            c_25 -= 1;
                            c_50 += 1;
                        } else
                            return no;
                        break;
                    case 75:
                        if (c_25 > 0) {
                            if (c_50 > 0) {
                                c_25 -= 1;
                                c_50 -= 1;
                            } else if (c_25 > 2) {
                                c_25 -= 3;
                            } else
                                return no;
                        } else
                            return no;
                        break;
                    default:
                        return no;
                }
            }
            return yes;
        }
    }

    public static String order(String words) {
        String result = "";
        int FLAG = 0;
        if (words == null || words.isEmpty())
            return result;
        String[] arr = words.split(" ");
        TreeMap<Character, String> map = new TreeMap<Character, String>();

        for (String word : arr) {
            FLAG = 0;
            char[] chars = word.toCharArray();
            for (char c : chars) {
                if (Character.isDigit(c)) {
                    FLAG = 1;
                    if (map.containsKey(c)) {
                        map.put(c, map.get(c) + " " + word);
                    } else {
                        map.put(c, word);
                    }
                    break;
                }
            }
            if (FLAG == 0)
                result += word + " ";
        }

        for (Map.Entry<Character, String> entry : map.entrySet())
            result += entry.getValue() + " ";
        return result.substring(0, result.length());
    }

    public static String order2(String words) {
        return Arrays.stream(words.split(" "))
                .sorted(Comparator.comparing(s -> Integer.valueOf(s.replaceAll("\\D", ""))))
                .reduce((a, b) -> a + " " + b).get();
    }

    public static int GetSum(int a, int b) {
        if (a > b) {
            a = a ^ b;
            b = a ^ b;
            a = a ^ b;
        }

        int sum = 0;
        while (a < b)
            sum += a++;
        return sum + b;
    }

    public static int[] twoSum(int[] numbers, int target) {
        for (int i = 0; i < numbers.length - 1; i++) {
            for (int n = i + 1; n < numbers.length; n++) {
                if ((numbers[i] + numbers[n]) == target) {
                    return new int[] { i, n };
                }
            }
        }
        return null;
    }

    public static String pigIt(String str) {
        return Arrays.stream(str.split(" "))
                .map(n -> n.matches("[^A-Za-z]") ? n : n.substring(1).concat(n.substring(0, 1)).concat("ay"))
                .reduce((a, b) -> a + " " + b).get();
    }

    static Map<String, Integer> nRM = new LinkedHashMap<>();
    static {
        nRM.put("M", 1000); nRM.put("CM", 900); nRM.put("D", 500); nRM.put("CD", 400);
        nRM.put("C", 100); nRM.put("XC", 90); nRM.put("L", 50); nRM.put("XL", 40);
        nRM.put("X", 10); nRM.put("IX", 9); nRM.put("V", 5); nRM.put("IV", 4); 
        nRM.put("I", 1);
    }

    public static String toRoman(int n) {
        StringBuffer sb = new StringBuffer();
        for (Entry<String, Integer> entry : nRM.entrySet()) {
            int base = n / entry.getValue();
            n = n - base * entry.getValue();
            if (base > 0) for (int x = 0; x < base; x++) sb.append(entry.getKey());
            if (n == 0) break;
        }
        return sb.toString();
    }

    public static int fromRoman(String romanNumeral) {
        int sum = 0;
        for (Entry<String, Integer> entry : nRM.entrySet()) {
            String base = entry.getKey();
            int count = 0;
            while(romanNumeral.startsWith(base)){
                romanNumeral = romanNumeral.substring(base.length());
                count += 1;
            }
            sum += count * entry.getValue();
            if(romanNumeral.isEmpty()) break;
        }
        return sum;
    }
}
