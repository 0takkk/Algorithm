import java.io.*;
import java.util.*;

public class Solution {

    public static int n, max, min;
    public static int[] oper, num, selected;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        int t = Integer.parseInt(br.readLine());
        StringBuilder sb = new StringBuilder();

        for (int tc = 1; tc <= t; tc++) {
            max = Integer.MIN_VALUE;
            min = Integer.MAX_VALUE;

            n = Integer.parseInt(br.readLine());

            oper = new int[4];
            num = new int[n];

            st = new StringTokenizer(br.readLine());
            for(int i = 0; i < 4; i++){
                oper[i] = Integer.parseInt(st.nextToken());
            }

            st = new StringTokenizer(br.readLine());
            for(int i = 0; i < n; i++){
                num[i] = Integer.parseInt(st.nextToken());
            }

            selected = new int[n-1];
            dfs(0);

            sb.append("#").append(tc).append(" ").append(max - min).append("\n");
        }

        System.out.println(sb.toString());
    }

    public static void dfs(int idx){
        if(idx == n-1){
            int sum = calc();

            if(max < sum) max = sum;
            if(min > sum) min = sum;
            return;
        }

        for(int i = 0; i < 4; i++){
            if(oper[i] > 0){
                oper[i]--;
                selected[idx] = i;
                dfs(idx+1);
                oper[i]++;
            }
        }
    }

    public static int calc(){
        int sum = num[0];

        for(int i = 1; i < n; i++){
            sum = calc(sum, selected[i-1], num[i]);
        }

        return sum;
    }

    public static int calc(int sum, int op, int next){
        switch(op){
            case 0:
                return sum + next;
            case 1:
                return sum - next;
            case 2:
                return sum * next;
            default:
                return sum / next;
        }
    }

}
