import java.io.*;
import java.util.*;

public class Main {

    public static class Node{
        int left, right;

        public Node(int left, int right) {
            this.left = left;
            this.right = right;
        }
    }

    public static int n, idx = 1;
    public static ArrayList<Node>[] graph;
    public static ArrayList<Integer>[] depth;
    public static int[] col;



    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        n = Integer.parseInt(br.readLine());
        graph = new ArrayList[n+1];
        depth = new ArrayList[n+1];
        col = new int[n+1];

        for(int i = 1; i <= n; i++){
            graph[i] = new ArrayList<>();
            depth[i] = new ArrayList<>();
        }

        int[] degree = new int[n+1];

        for(int i = 0; i < n; i++){
            st = new StringTokenizer(br.readLine());
            int num = Integer.parseInt(st.nextToken());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());

            graph[num].add(new Node(a, b));
            if(a != -1) degree[a]++;
            if(b != -1) degree[b]++;
        }

        int root = 0;
        for(int i = 1; i <= n; i++){
            if(degree[i] == 0) root = i;
        }

        dfs(root, 1);

        int max = 0;
        int h = 0;

        for(int i = 1; i <= n; i++){
            int diff = 0;
            if(depth[i].size() == 0) break;
            else if(depth[i].size() == 1) {
                diff = 1;
            } else {
                int tmp_min = Integer.MAX_VALUE;
                int tmp_max = 0;

                for (int index : depth[i]) {
                    if (tmp_min > col[index]) tmp_min = col[index];
                    if (tmp_max < col[index]) tmp_max = col[index];
                }

                diff = tmp_max - tmp_min + 1;
            }

            if(max < diff){
                max = diff;
                h = i;
            }
        }

        System.out.println(h + " " + max);
    }

    public static void dfs(int now, int dep){
        depth[dep].add(now);

        for(Node next : graph[now]){
            if(next.left != -1) dfs(next.left, dep+1);
            col[now] = idx++;
            if(next.right != -1) dfs(next.right, dep+1);
        }
    }

}
