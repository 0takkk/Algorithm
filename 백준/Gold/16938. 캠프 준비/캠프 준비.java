import java.io.*;
import java.util.*;

public class Main {

    public static int ans = 0, n, l, r, x;
    public static int[] problems;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        l = Integer.parseInt(st.nextToken());
        r = Integer.parseInt(st.nextToken());
        x = Integer.parseInt(st.nextToken());

        problems = new int[n];
        st = new StringTokenizer(br.readLine());

        for(int i = 0; i < problems.length; i++) {
            problems[i] = Integer.parseInt(st.nextToken());
        }

        Arrays.sort(problems);

        for(int i = 0; i < n-1; i++) {
            dfs(i, 1, problems[i], problems[i]);
        }

        System.out.println(ans);
    }

    public static void dfs(int idx, int cnt, int sum, int min) {
        if(cnt >= 2 && l <= sum && sum <= r && problems[idx] - min >= x) {
            ans++;
        }

        if(sum + problems[idx] > r) return;

        for(int i = idx+1; i < n; i++) {
            dfs(i, cnt+1, sum+problems[i], min);
        }
    }

}