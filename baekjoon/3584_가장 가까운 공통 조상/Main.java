import java.io.*;
import java.util.*;

public class Main {

    public static int n;
    public static ArrayList<Integer>[] graph;
    public static int[] depth, parents;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        int t = Integer.parseInt(br.readLine());

        while(t-->0){
            n = Integer.parseInt(br.readLine());

            graph = new ArrayList[n+1];
            for(int i = 1; i <= n; i++)
                graph[i] = new ArrayList<>();

            int[] cnt = new int[n+1];
            depth = new int[n+1];
            parents = new int[n+1];

            for(int i = 1; i < n; i++){
                st = new StringTokenizer(br.readLine());
                int parent = Integer.parseInt(st.nextToken());
                int child = Integer.parseInt(st.nextToken());

                graph[parent].add(child);
                cnt[child]++;
                parents[child] = parent;
            }

            st = new StringTokenizer(br.readLine());
            int n1 = Integer.parseInt(st.nextToken());
            int n2 = Integer.parseInt(st.nextToken());

            int root = 0;
            for(int i = 1; i <= n; i++){
                if(cnt[i] == 0) root = i;
            }

            getDepth(root);

            System.out.println(lca(n1, n2));
        }

    }

    public static int lca(int a, int b){
        while(true){
            if(a == b){
                return a;
            }

            if(depth[a] == depth[b]){
                while(a != b){
                    a = parents[a];
                    b = parents[b];
                }
            } else if(depth[a] > depth[b]){
                while(depth[a] > depth[b]){
                    a = parents[a];
                }
            } else {
                while(depth[a] < depth[b]){
                    b = parents[b];
                }
            }
        }
    }

    public static void getDepth(int root){
        Queue<Integer> q = new LinkedList<>();
        q.offer(root);

        while(!q.isEmpty()){
            int now = q.poll();

            for (int next : graph[now]) {
                depth[next] = depth[now]+1;
                q.offer(next);
            }
        }
    }


}
