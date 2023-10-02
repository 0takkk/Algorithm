import java.io.*;
import java.util.*;

public class Main {

    public static ArrayList<Integer>[] graph;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        int n = Integer.parseInt(br.readLine());
        graph = new ArrayList[26];

        for(int i = 0; i < 26; i++) {
            graph[i] = new ArrayList<>();
        }

        while(n-->0) {
            String[] arr = br.readLine().split(" is ");
            graph[arr[0].charAt(0)-'a'].add(arr[1].charAt(0)-'a');
        }

        int m = Integer.parseInt(br.readLine());
        StringBuilder sb = new StringBuilder();

        while(m-->0) {
            String[] arr = br.readLine().split(" is ");
            sb.append(bfs(arr[0].charAt(0)-'a', arr[1].charAt(0)-'a') ? "T" : "F").append("\n");
        }

        System.out.println(sb.toString());
    }

    public static boolean bfs(int start, int end) {
        boolean[] visited = new boolean[26];
        Queue<Integer> q = new ArrayDeque<>();
        q.offer(start);
        visited[start] = true;

        while(!q.isEmpty()) {
            int now = q.poll();

            for (int next : graph[now]) {
                if(next == end) return true;

                if(!visited[next]) {
                    visited[next] = true;
                    q.offer(next);
                }
            }
        }

        return false;
    }

}
