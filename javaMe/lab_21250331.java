package javaMe;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public class lab_21250331{

    private static boolean hasE = false;
    private static final String E = "empty";
    public static String firstGrammar(String[] ss){
        List<String> bs = new ArrayList<>();
        StringBuilder sb = new StringBuilder();
        Map<String, String> regs = new LinkedHashMap<>();
        if(ss.length > 0) {
            String b = ss[0].split("-")[1];
            for(String n : b.split("\\|")) bs.add(n.trim());
        }
        sb.append("{");
        if(ss.length > 1) {
            for(int i = 1; i< ss.length; i++){
                String[] r = ss[i].split("-");
                regs.put(r[0].trim(), r[1].trim()); // deal with it
            }

            for(String b : bs) sb.append(recursionCheck(regs, b));
            if(hasE){
                hasE = false;
                sb.append(E).append(",");
            }
        } else for(String b : bs) sb.append(b.split(" ")[0]).append(",");
        sb.deleteCharAt(sb.length() - 1).append("}");

        return sb.toString();
    }

    public static String recursionCheck(Map<String, String> regs, String body){
        StringBuffer sb = new StringBuffer();
        for (String b : body.split("\\|")){
            if (E.equals(b.trim())) hasE = true;
            else{
                for (String x : b.split(" ")){
                    if(regs.containsKey(x)){
                        sb.append(recursionCheck(regs, regs.get(x)));
                    }else {
                        sb.append(x).append(",");
                        break;
                    }
                }
            }
        }
        return sb.toString();
    }


    public static void main(String[] args) {
        // Exercise 1
        System.out.println(firstGrammar(new String[]{"B - a B | empty"}));
        System.out.println(firstGrammar(new String[]{"C - c C | empty"}));
        // Exercise 2
        System.out.println(firstGrammar(new String[]{
            "S - B b | C d",
            "B - a B | empty",
            "C - c C | empty"}));
    }
}