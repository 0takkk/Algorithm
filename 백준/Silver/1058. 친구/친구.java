import java.io.*;
import java.util.*;

public class Main {

    public static class Node {
        int x, depth;

        public Node(int x, int depth) {
            this.x = x;
            this.depth = depth;
        }
    }

    public static int n;
    public static boolean[][] friend;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        n = Integer.parseInt(br.readLine());
        friend = new boolean[n+1][n+1];

        for(int i = 1; i <= n; i++) {
            String str = br.readLine();
            for(int j = 1; j <= n; j++) {
                if(str.charAt(j-1) == 'Y') friend[i][j] = true;
            }
        }

        int ans = 0;

        for(int i = 1; i <= n; i++) {
            ans = Math.max(ans, bfs(i));
        }

        System.out.println(ans);
    }

    public static int bfs(int start) {
        int[] cost = new int[n+1];
        Arrays.fill(cost, Integer.MAX_VALUE);

        Queue<Node> q = new ArrayDeque<>();
        q.offer(new Node(start, 0));
        cost[start] = 0;

        int cnt = -1;

        while(!q.isEmpty()) {
            Node now = q.poll();
            if(now.depth > 2) continue;

            cnt++;

            for(int next = 1; next <= n; next++) {
                if(friend[now.x][next] && cost[next] > now.depth+1) {
                    cost[next] = now.depth+1;
                    q.offer(new Node(next, cost[next]));
                }
            }
        }

        return cnt;
    }

}