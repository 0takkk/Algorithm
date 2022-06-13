import java.io.*;
import java.util.*;

public class Main {


    public static int n, ans;
    public static int[] nodes;
    public static ArrayList<Integer>[] list;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        StringBuilder sb = new StringBuilder();

        while(true){
            n = Integer.parseInt(br.readLine());
            ans = 0;
            if(n == 0) break;

            nodes = new int[n+1];
            list = new ArrayList[n+1];
            for(int i = 1; i <= n; i++){
                list[i] = new ArrayList<>();
            }

            int[] degree = new int[n+1];

            for(int i = 0; i < n; i++){
                st = new StringTokenizer(br.readLine());

                int v = Integer.parseInt(st.nextToken());
                int bead = Integer.parseInt(st.nextToken());

                nodes[v] = bead;

                int size = Integer.parseInt(st.nextToken());

                for(int j = 0; j < size; j++){
                    int child = Integer.parseInt(st.nextToken());
                    list[v].add(child);
                    degree[child]++;
                }
            }

            int root = 0;
            for(int i = 1; i <= n; i++){
                if(degree[i] == 0) root = i;
            }

            dfs(root);
            sb.append(ans+"\n");
        }
        System.out.println(sb.toString());
    }

    public static int dfs(int idx){
        int cnt = nodes[idx] - 1;

        for(int next : list[idx]){
            cnt += dfs(next);
        }

        ans += Math.abs(cnt);
        return cnt;
    }

}
