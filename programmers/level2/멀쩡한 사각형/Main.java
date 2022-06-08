import java.util.*;


public class Main {
    public static void main(String[] args) {
        System.out.println(solution(8, 12));
    }

    public static long solution(int w, int h) {
        int gcd = gcd(w, h);

        int mw = w / gcd;
        int mh = h / gcd;

        int cnt = mw + mh - 1;

        long ans = (long)w * (long)h - (cnt * gcd);
        return ans;
    }

    public static int gcd(int a, int b){
        int r;

        while(b > 0){
            r = a % b;

            a = b;
            b = r;
        }

        return a;
    }

}
