public class e1 {
    public static void main(String[] args) {
        System.out.println(solution("abc", "bc"));
        System.out.println(solution("abc", "d"));
        System.out.println(findIt(new int[]{0,1,0,1,0}));
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
}
