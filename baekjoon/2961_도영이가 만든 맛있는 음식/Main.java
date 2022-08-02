import java.io.*;
import java.util.*;

public class Main {

    public static class Node{
        int s, b;

        public Node(int s, int b) {
            this.s = s;
            this.b = b;
        }
    }

    public static int n, min = Integer.MAX_VALUE;
    public static Node[] nodes;
    public static boolean[] visited;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        n = Integer.parseInt(br.readLine());
        nodes = new Node[n];

        for(int i = 0; i < n; i++){
            st = new StringTokenizer(br.readLine());
            int s = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());

            nodes[i] = new Node(s, b);
        }

        visited = new boolean[n];

        backtracking(0, 0, 1, 0);
        System.out.println(min);
    }

    public static void backtracking(int idx, int cnt, int s, int b){
        if(cnt == n+1) return;
        if(cnt > 0){
            min = Math.min(min, Math.abs(s-b));
        }

        for(int i = idx; i < n; i++){
            if(!visited[i]){
                visited[i] = true;
                backtracking(i+1, cnt+1, s * nodes[i].s, b + nodes[i].b);
                visited[i] = false;
            }
        }
    }

}
