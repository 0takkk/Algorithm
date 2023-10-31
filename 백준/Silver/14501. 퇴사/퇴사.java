import java.io.*;
import java.util.*;

public class Main {

    public static int n, ans = 0, times[], pays[];

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        n = Integer.parseInt(br.readLine());
        times = new int[n+1];
        pays = new int[n+1];

        for(int i = 1; i <= n; i++) {
            st = new StringTokenizer(br.readLine());
            times[i] = Integer.parseInt(st.nextToken());
            pays[i] = Integer.parseInt(st.nextToken());
        }

        dfs(1, 0);
        System.out.println(ans);
    }

    public static void dfs(int day, int sum) {
        if(day > n) {
            ans = Math.max(ans, sum);
            return;
        }

        if(day + times[day] <= n+1) {
            dfs(day + times[day], sum + pays[day]);
        }

        dfs(day+1, sum);
    }

}