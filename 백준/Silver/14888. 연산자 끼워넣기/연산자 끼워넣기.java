import java.io.*;
import java.util.*;

public class Main {

    public static int n, min, max;
    public static int[] num, op;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        min = Integer.MAX_VALUE;
        max = Integer.MIN_VALUE;
        n = Integer.parseInt(br.readLine());

        num = new int[n];
        st = new StringTokenizer(br.readLine());
        for(int i = 0; i < n; i++) {
            num[i] = Integer.parseInt(st.nextToken());
        }

        op = new int[4];
        st = new StringTokenizer(br.readLine());
        for(int i = 0; i < 4; i++) {
            op[i] = Integer.parseInt(st.nextToken());
        }

        dfs(1, num[0]);

        System.out.println(max);
        System.out.println(min);
    }

    public static void dfs(int idx, int cal) {
        if(idx == n) {
            min = Math.min(min, cal);
            max = Math.max(max, cal);
            return;
        }

        for(int i = 0; i < 4; i++) {
            if(op[i] > 0) {
                op[i]--;
                dfs(idx+1, calc(idx, cal, i));
                op[i]++;
            }
        }
    }

    public static int calc(int idx, int cal, int oper) {
        int number = num[idx];
        switch (oper) {
            case 0:
                return cal + number;
            case 1:
                return cal - number;
            case 2:
                return cal * number;
            default:
                return cal / number;
        }
    }

}