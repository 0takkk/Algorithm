import java.io.*;
import java.util.*;

public class Main {

    public static class Node{
        int x, cost;

        public Node(int x, int cost) {
            this.x = x;
            this.cost = cost;
        }
    }

    public static int n, root, giga;
    public static ArrayList<Node>[] list;
    public static int[] child, dists;
    public static boolean[] visited;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        root = Integer.parseInt(st.nextToken());

        list = new ArrayList[n+1];
        for(int i = 1; i <= n; i++)
            list[i] = new ArrayList<>();

        for(int i = 1; i < n; i++){
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            int d = Integer.parseInt(st.nextToken());

            list[a].add(new Node(b, d));
            list[b].add(new Node(a, d));
        }

        child = new int[n+1];
        dists = new int[n+1];
        visited = new boolean[n+1];

        visited[root] = true;

        dfs(root, 0);


        int max = 0;
        for(int i = 1; i<= n; i++){
            max = Math.max(max, dists[i]);
        }
        if(giga != 0){
            System.out.println(dists[giga] + " " + (max-dists[giga]));
        }
        else{
            if(giga == root){
                System.out.println(0 + " " + max);
            }
            else{
                System.out.println(max + " " + 0);
            }
        }
    }

    public static int dfs(int now, int dist){

        for(Node next : list[now]){
            if(!visited[next.x]) {
                visited[next.x] = true;
                child[now]++;
                dists[next.x] = dfs(next.x, dist + next.cost);
                visited[next.x] = false;
            }
        }

        if(child[now] > 1) giga = now;

        return dist;
    }

}
