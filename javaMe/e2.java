package javaMe;

import java.util.*;
import java.util.Map.Entry;
import java.util.function.Function;
import java.util.stream.Collectors;

import static java.util.stream.Collectors.groupingBy;

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
        System.out.println(top3("  ... ").toString());
        System.out.println(top3("In a village of La Mancha, the name of which I have no desire to call to"
                + "mind, there lived not long since one of those gentlemen that keep a lance"
                + "in the lance-rack, an old buckler, a lean hack, and a greyhound for"
                + "coursing. An olla of rather more beef than mutton, a salad on most"
                + "nights, scraps on Saturdays, lentils on Fridays, and a pigeon or so extra"
                + "on Sundays, made away with three-quarters of his income.").toString());

        System.out.println(validParentheses("()"));
        System.out.println(validParentheses(")(()))"));
        System.out.println(validParentheses("("));
        System.out.println(validParentheses("(())((()())())"));
        int[] nums = new int[] {2,2,2,2,2};
        // System.out.println(threeSum(nums));
        System.out.println(fourSum(nums, 8));
    }

    public static int[] parse(String data) {
        int cursor = 0;
        List<Integer> list = new ArrayList<>();

        for (char c : data.toCharArray()) {
            switch (c) {
                case 'i':
                    cursor += 1;
                    break;
                case 'd':
                    cursor -= 1;
                    break;
                case 's':
                    cursor *= cursor;
                    break;
                case 'o':
                    list.add(cursor);
                    break;
            }
        }
        return list.stream().mapToInt(Integer::intValue).toArray();
    }

    public static String spinWords(String sentence) {
        return Arrays.stream(sentence.split(" "))
                .map(n -> n.length() < 5 ? n : (new StringBuilder(n).reverse().toString()))
                .reduce((a, b) -> a + " " + b).get();
    }

    public static boolean isPrime(int i) {
        if (i == 2)
            return true;
        for (int q = 2; q < i; q++)
            if (i % q == 0)
                return false;
        return true;
    }

    public static List<Integer> findPrimeNums(int i) {
        List<Integer> is = new ArrayList<>();
        if (i < 0)
            i = -i;
        for (int p = 2; p <= i; p++)
            if ((i % p == 0) && isPrime(p))
                is.add(p);
        return is;
    }

    public static String sumOfDivided(int[] l) {
        Map<Integer, List<Integer>> ds = new HashMap<>();
        TreeMap<Integer, Integer> re = new TreeMap<>();
        Map<Integer, Integer> ct = new HashMap<>();
        StringBuilder sb = new StringBuilder();
        for (int i : l) {
            if (ds.containsKey(i))
                ct.put(i, ct.containsKey(i) ? ct.get(i) + 1 : 1);
            ds.put(i, findPrimeNums(i));
        }
        for (Map.Entry<Integer, List<Integer>> entry : ds.entrySet()) {
            int k = entry.getKey();
            for (int p : entry.getValue()) {
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
        if (str.isEmpty())
            return cons;
        String[] ws = str.split("\\s+");
        Map<String, Integer> rt = new HashMap<>();
        for (String w : ws) {
            if (w.matches("^[']+[a-zA-Z]*"))
                continue;
            rt.put(w.toLowerCase(), rt.containsKey(w) ? rt.get(w.toLowerCase()) + 1 : 1);
        }

        for (Entry<String, Integer> entry : rt.entrySet()) {
            for (int i = 0; i <= cons.size(); i++) {
                if (cons.size() == i) {
                    cons.add(i, entry.getKey());
                    break;
                } else {
                    String st = cons.get(i);
                    if (rt.get(st) < entry.getValue()) {
                        cons.add(i, entry.getKey());
                        break;
                    } else if (rt.get(st) == entry.getValue()) {
                        if (st.compareTo(entry.getKey()) > 0) {
                            cons.add(i, entry.getKey());
                            break;
                        }
                    }
                }
            }
        }
        return cons = cons.size() >= 3 ? cons.subList(0, 3) : cons.subList(0, cons.size());
    }

    public static List<String> top3_2(String s) {
        return Arrays.stream(s.toLowerCase().split("[^a-z*|']"))
                .filter(o -> !o.isEmpty() && !o.replace("'", "").isEmpty())
                .collect(groupingBy(Function.identity(), Collectors.counting())).entrySet().stream()
                .sorted(Collections.reverseOrder(Map.Entry.comparingByValue()))
                .map(Map.Entry::getKey)
                .limit(3)
                .collect(Collectors.toList());
    }

    public static boolean validParentheses(String parens) {
        int sum = 0;
        for(int i = 0; i< parens.length(); i ++){
            String s = parens.substring(i, i + 1);
            if(s.equals("(")) sum += 1;
            else if(s.equals(")")) sum -= 1;
            else continue;
            if(sum < 0) return false;
        }
        return sum == 0 ? true : false;
    }

    public static int maxArea(int[] height) {
        int area = 0;
        int left = 0;
        int right = height.length - 1;
        while(left < right) {
            int l = height[left];
            int r = height[right];
            int s = right - left;

            int tmp = Math.min(l, r) * s;
            if(tmp > area) area = tmp;

            if(l < r) left += 1; else  right += 1;
        }
        return area;
    }

    public static List<List<Integer>> fourSum(int[] nums, int target) {
        List<List<Integer>> result = new ArrayList<>();
        if(nums.length < 4) return result;
        Arrays.sort(nums);
        int i = 0;
       
        while (i < nums.length - 3){
            int target1 = nums[i];
            int j = i + 1;
            while (j < nums.length - 2){
                int target2 = nums[j];
                twoSum(result, nums, j + 1, target, target1, target2);
                while(j < nums.length - 2 && nums[j] == target2) j += 1;
            }
            while(i < nums.length - 3 && nums[i] == target1) i += 1;
        }
        return result;
    }

    public static void twoSum(List<List<Integer>> result, int[] num, int start, int target, int target1, int target2){
        int left = start;
        int right = num.length - 1;

        while(left < right) {
            int l = num[left];
            int r = num[right];
            int gap = l + r + target1 + target2;
            
            if(gap < target){
                while(left < right && num[left] == l) left += 1;
            }else if(gap > target){
                while(left < right && num[right] == r) right -= 1;
            }else{
                result.add(Arrays.asList(target1, target2, l, r));
                while(left < right && num[left] == l) left += 1;
                while(left < right && num[right] == r) right -= 1;
            }
        }
    }

    public static List<List<Integer>> threeSum(int[] nums) {
        List<List<Integer>> result = new ArrayList<>();
        if(nums.length < 3) return result;
        // sort(nums);
        Arrays.sort(nums);
        int i = 0;
        while (i < nums.length - 2){
            int target = nums[i];
            twoSum(result, nums, i + 1, target);
            while(i < nums.length - 2 && nums[i] == target) i += 1;
        }
        return result;
    }

    public static void twoSum(List<List<Integer>> result, int[] num, int start, int target){
        int left = start;
        int right = num.length - 1;

        while(left < right) {
            int l = num[left];
            int r = num[right];
            int gap = l + r + target;
            
            if(gap < 0 ){
                while(left < right && num[left] == l) left += 1;
            }else if(gap > 0){
                while(left < right && num[right] == r) right -= 1;
            }else{
                result.add(Arrays.asList(target, l, r));
                while(left < right && num[left] == l) left += 1;
                while(left < right && num[right] == r) right -= 1;
            }
        }
    }

    public static void sort(int[] nums){
        for(int i = 0; i < nums.length - 1; i++){
            for(int j = i + 1; j < nums.length; j++){
                if(nums[i] > nums[j]){
                    nums[i] = nums[i] ^ nums[j];
                    nums[j] = nums[i] ^ nums[j];
                    nums[i] = nums[i] ^ nums[j];
                }
            }
        }
    }
}
