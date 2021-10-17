import java.util.Arrays;
import java.util.Comparator;
import java.util.Map;
import java.util.TreeMap;

public class e1 {
    public static void main(String[] args) {
        System.out.println(solution("abc", "bc"));
        System.out.println(solution("abc", "d"));
        System.out.println(findIt(new int[]{0,1,0,1,0}));
        System.out.println(Tickets(new int[]{25, 25, 50}));
        System.out.println(Tickets(new int[]{25, 25, 50, 50, 100}));
        System.out.println(order("4of Fo1r pe6ople g3ood th5e the2"));
        System.out.println(order2("Fo1r the2 g3ood 4of th5e pe6ople"));
        System.out.println(order("Empty input should return empty string"));
    }

    public static boolean solution(String str, String ending) {
        return str.endsWith(ending);
    }

    public static int findIt(int[] a) {
        int result = 0;
        for(int n:a) {
            result = n ^ result;
        }
        return result;
    }

    public static String Tickets(int[] peopleInLine) {
        String yes = "YES";
        String no = "NO";

        if (peopleInLine.length == 0) return no;
        else {
            int ticket = 25;
            int c_25 = 0;
            int c_50 = 0;
            int c_100 = 0;

            for (int m : peopleInLine){
                int change = m - ticket;
                switch (change) {
                    case 0:
                        c_25 += 1;
                        break;
                    case 25:
                        if(c_25 > 0){
                            c_25 -= 1;
                            c_50 += 1;
                        } else return no;
                        break;
                    case 75:
                        if(c_25 > 0){
                            if(c_50 > 0){
                                c_25  -= 1;
                                c_50  -= 1;
                                c_100 += 1;
                            }else if(c_25 > 2){
                                c_25  -= 3;
                                c_100 += 1;
                            }else return no;
                        } else return no;
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
        if(words == null || words.isEmpty()) return result;
        String[] arr = words.split(" ");
        TreeMap<Character,String> map = new TreeMap<Character,String>();

        for(String word : arr){
            FLAG = 0;
            char[] chars = word.toCharArray();
            for(char c : chars){
                if(Character.isDigit(c)){
                    FLAG = 1;
                    if(map.containsKey(c)){
                        map.put(c, map.get(c) + " " + word);
                    }else{
                        map.put(c, word);
                    }
                    break;
                }
            }
            if(FLAG == 0) result += word + " ";
        }

        for (Map.Entry<Character, String> entry : map.entrySet())  result += entry.getValue() + " ";
        return result.substring(0, result.length());
    }

    public static String order2(String words) {
        return Arrays.stream(words.split(" "))
          .sorted(Comparator.comparing(s -> Integer.valueOf(s.replaceAll("\\D", ""))))
          .reduce((a, b) -> a + " " + b).get();
      }
}
