package assignments.cryptography.finalE.NumberTheory;

import java.math.BigInteger;

public class CRT_h {
    static final BigInteger ZERO = new BigInteger("0");
    static  final BigInteger ONE = new BigInteger("1");
    public static BigInteger inv(BigInteger a, BigInteger m) {
        BigInteger m0 = m, t, q;
        BigInteger x0 = ZERO, x1 = ONE;
        if (m.equals(ONE)) {
            return ZERO;
        }
        // Apply extended Euclid Algorithm
        while (a.compareTo(ONE)==1) { //a>1
            q = a.divide(m); // q is quotient
            t = m;
            m = a.mod(m); // m is remainder now, process same as euclid's algo
            a = t;
            t = x0;
//            x0 = x1 - q * x0;
            x0 = x1.subtract(q.multiply(x0));
            x1 = t;
        }
        // Make x1 positive
        if (x1.compareTo(ONE) == -1) { // x1 < 0
            x1 = x1.add(m0); //x1 += m0;
        }
        return x1;
    }

    public static BigInteger findMinX(BigInteger num[], BigInteger rem[], int k) {
        // Compute product of all numbers
        BigInteger prod = new BigInteger("1");
        for (int i = 0; i < k; i++)
            prod = prod.multiply(num[i]); // num -> mod n
        // Initialize result
        BigInteger result = new BigInteger("0");

        // Apply above formula
        for (int i = 0; i < k; i++) {
            BigInteger pp = prod.divide(num[i]);
            result = result.add(rem[i].multiply(inv(pp, num[i])). multiply(pp));

        }
        return result.mod( prod);
    }

    public static void main(String[] args) {
        // write your code here
        BigInteger num[] = {new BigInteger("4459740564724538700519142326997"),new BigInteger("2281806784635143785292256501293"),
                new BigInteger("2467881921864340392351277277159")}; // mod n
        BigInteger rem[] = {new BigInteger("191699250018696932235548276819"), new BigInteger("2262766903820045933008151262425"),
                new BigInteger("1643829619688527494120778009131")};
        int k = num.length;
        System.out.println("x is " + findMinX(num, rem, k));
    }
}