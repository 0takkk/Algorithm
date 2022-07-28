import java.io.*;
import java.util.*;

public class Main {

    public static int n, max = Integer.MIN_VALUE, min = Integer.MAX_VALUE;
    public static int[] num, calc;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        n = Integer.parseInt(br.readLine());
        st = new StringTokenizer(br.readLine());

        num = new int[n];
        for(int i = 0; i < n; i++){
            num[i] = Integer.parseInt(st.nextToken());
        }

        calc = new int[4];
        st = new StringTokenizer(br.readLine());
        for(int i = 0; i < 4; i++){
            calc[i] = Integer.parseInt(st.nextToken());
        }

        backtracking(1, num[0]);

        System.out.println(max);
        System.out.println(min);
    }

    public static void backtracking(int idx, int sum){
        if(idx == n){
            max = Math.max(max, sum);
            min = Math.min(min, sum);
            return;
        }
        
        for(int i = 0; i < 4; i++){
            if(calc[i] > 0){
                calc[i]--;
                if(i == 0) backtracking(idx+1, sum + num[idx]);
                else if(i == 1) backtracking(idx+1, sum - num[idx]);
                else if(i == 2) backtracking(idx+1, sum * num[idx]);
                else backtracking(idx+1, sum / num[idx]);
                calc[i]++;
            }
        }
    }

}
