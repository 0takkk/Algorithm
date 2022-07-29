import java.io.*;
import java.util.*;

public class Main {

    public static int n;
    public static ArrayList<Integer>[] graph;
    public static int[] parent;
    public static boolean[] visited, isCycle;
    public static boolean flag;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        n = Integer.parseInt(br.readLine());
        graph = new ArrayList[n+1];
        parent = new int[n+1];
        visited = new boolean[n+1];
        isCycle = new boolean[n+1];

        for(int i = 1; i <= n; i++){
            graph[i] = new ArrayList<>();
        }

        for(int i = 1; i <= n; i++){
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());

            graph[a].add(b);
            graph[b].add(a);
        }

        dfs(1, 0);

        StringBuilder sb = new StringBuilder();
        for(int i = 1; i <= n; i++){
            sb.append(bfs(i) + " ");
        }

        System.out.println(sb.toString());
    }

    public static void dfs(int now, int pre){
        visited[now] = true;

        for(int next : graph[now]){
            if(next != pre){
                if(!visited[next]){
                    parent[next] = now;
                    dfs(next, now);
                }else{
                    if(flag) continue;

                    int tmp = now;
                    while(tmp != next){
                        isCycle[tmp] = true;
                        tmp = parent[tmp];
                    }
                    isCycle[next] = true;
                    flag = true;
                }
            }
        }
    }

    public static int bfs(int start){
        Queue<Integer> q = new LinkedList<>();
        q.offer(start);
        visited = new boolean[n+1];
        visited[start] = true;

        int cnt = 0;

        while(!q.isEmpty()){
            int size = q.size();

            for(int i = 0; i < size; i++) {
                int now = q.poll();

                if (isCycle[now]) return cnt;

                for(int next : graph[now]){
                    if(!visited[next]){
                        visited[next] = true;
                        q.offer(next);
                    }
                }
            }
            cnt++;
        }

        return cnt;
    }

}
