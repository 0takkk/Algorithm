import java.io.*;
import java.util.*;

public class Main {

    public static int n;
    public static ArrayList<Integer>[] list;
    public static boolean[] visited, isFriend;
    public static boolean isPossible;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        int m = Integer.parseInt(st.nextToken());

        list = new ArrayList[n+1];
        for(int i = 1; i <= n; i++){
            list[i] = new ArrayList<>();
        }

        while(m-->0){
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());

            list[a].add(b);
            list[b].add(a);
        }

        visited = new boolean[n+1];
        isFriend = new boolean[n+1];

        isPossible = true;
        for(int i = 1; i <= n; i++){
            if(!visited[i]){
                visited[i] = true;
                isFriend[i] = true;
                dfs(i, true);
            }
        }

        if(isPossible) System.out.println(1);
        else System.out.println(0);
    }

    public static void dfs(int idx, boolean flag){
        visited[idx] = true;

        if(!isPossible) return;

        for(int next : list[idx]){
            if(!visited[next]){
                isFriend[next] = !flag;
                dfs(next, !flag);
            }
            else{
                if(isFriend[next] == flag){
                    isPossible = false;
                    return;
                }
            }
        }
    }

}
