import java.io.*;
import java.util.*;

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        int n = Integer.parseInt(br.readLine());
        int[] dp = new int[1000001];

        Queue<Integer> q = new ArrayDeque<>();
        q.offer(n);

        while(!q.isEmpty()){
            int now = q.poll();

            if(now == 1) break;

            if(now % 3 == 0 && dp[now/3] == 0){
                dp[now/3] = now;
                q.offer(now/3);
            }
            if(now % 2 == 0 && dp[now/2] == 0){
                dp[now/2] = now;
                q.offer(now/2);
            }
            if(dp[now-1] == 0){
                dp[now-1] = now;
                q.offer(now-1);
            }
        }

        Stack<Integer> stack = new Stack<>();
        for(int i = 1; i != n; i = dp[i]){
            stack.push(i);
        }

        System.out.println(stack.size());
        StringBuilder sb = new StringBuilder();
        sb.append(n).append(" ");
        while(!stack.isEmpty()){
            sb.append(stack.pop()).append(" ");
        }

        System.out.println(sb.toString());
    }

}
