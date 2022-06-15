import java.io.*;
import java.util.*;

public class Main {

    public static ArrayList<Integer>[] list;
    public static boolean[] visited;
    public static boolean flag;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        st = new StringTokenizer(br.readLine());
        int n = Integer.parseInt(st.nextToken());
        int m = Integer.parseInt(st.nextToken());

        list = new ArrayList[n];
        for(int i = 0; i < n; i++)
            list[i] = new ArrayList<>();

        while(m-->0){
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());

            list[a].add(b);
            list[b].add(a);
        }

        for(int i = 0; i < n; i++){
            flag = false;
            visited = new boolean[n];
            visited[i] = true;
            dfs(i, 1);

            if(flag) {
                System.out.println(1);
                return;
            }
        }

        System.out.println(0);
    }

    public static void dfs(int idx, int depth){
        if(depth == 5){
            flag = true;
            return;
        }

        for(int next : list[idx]){
            if(!visited[next]){
                visited[next] = true;
                dfs(next, depth+1);
                visited[next] = false;
            }
        }
    }

}
