import java.io.*;
import java.util.*;

public class Main {

    public static int n, k;
    public static boolean[][] friend;
    public static boolean[] visited;
    public static boolean done;
    public static StringBuilder sb = new StringBuilder();

    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        st = new StringTokenizer(br.readLine());
        k = Integer.parseInt(st.nextToken());
        n = Integer.parseInt(st.nextToken());
        int m = Integer.parseInt(st.nextToken());

        friend = new boolean[n+1][n+1];
        visited = new boolean[n+1];
        int[] friendCount = new int[n+1];

        while(m-->0) {
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());

            friend[a][b] = true;
            friend[b][a] = true;
            friendCount[a]++;
            friendCount[b]++;
        }

        for(int i = 1; i <= n; i++) {
            if(friendCount[i] >= k-1 && !done) {
                visited[i] = true;
                dfs(i, 1);
                visited[i] = false;
            }
        }

        if(!done) System.out.println(-1);
        else {
            System.out.println(sb.toString());
        }
    }

    public static void dfs(int now, int cnt) {
        if(done) return;
        if(cnt == k) {
            done = true;
            for(int i = 1; i <= n; i++) {
                if(visited[i]) {
                    sb.append(i).append("\n");
                }
            }
            return;
        }

        for(int i = now+1; i <= n; i++) {
            if(friend[now][i] && isAllFriend(i)) {
                visited[i] = true;
                dfs(i, cnt+1);
                visited[i] = false;
            }
        }
    }

    public static boolean isAllFriend(int target) {
        for(int i = 1; i <= n; i++) {
            if(visited[i] && !friend[target][i]) {
                return false;
            }
        }

        return true;
    }
}
