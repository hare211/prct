package ex.ct;

public class CT {
    public int[] solution(int numer1, int denom1, int numer2, int denom2) {
        int numerator = numer1 * denom2 + numer2 * denom1;
        int denominator = denom1 * denom2;

        int a = numerator;
        int b = denominator;

        while (b != 0) {
            int temp = a % b;
            a = b;
            b = temp;
        }
        int gcd = a;

        numerator /= gcd;
        denominator /= gcd;

        if (denominator < 0) {
            numerator = -numerator;
            denominator = -denominator;
        }

        return new int[]{numerator, denominator};


        }
}




