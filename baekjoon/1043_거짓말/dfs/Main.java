import java.io.*;
import java.util.*;

public class Main {

    public static boolean[] isKnow;
    public static HashSet<Integer>[] graph;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        st = new StringTokenizer(br.readLine());
        int n = Integer.parseInt(st.nextToken());
        int m = Integer.parseInt(st.nextToken());



        st = new StringTokenizer(br.readLine());
        int truth = Integer.parseInt(st.nextToken());

        isKnow = new boolean[n+1];
        while(truth-->0){
            isKnow[Integer.parseInt(st.nextToken())] = true;
        }

        graph = new HashSet[n+1];
        for(int i = 1; i <= n; i++){
            graph[i] = new HashSet<>();
        }

        ArrayList<Integer>[] party = new ArrayList[m];
        for(int i = 0; i < m; i++){
            party[i] = new ArrayList<>();
            st = new StringTokenizer(br.readLine());
            int count = Integer.parseInt(st.nextToken());

            party[i].add(Integer.parseInt(st.nextToken()));

            for(int j = 1; j < count; j++){
                party[i].add(Integer.parseInt(st.nextToken()));

                graph[party[i].get(j)].add(party[i].get(j-1));
                graph[party[i].get(j-1)].add(party[i].get(j));
            }
        }

        for(int i = 1; i <= n; i++){
            if(isKnow[i]) dfs(i);
        }

        int ans = 0;
        for(int i = 0; i < m; i++){
            if(!isKnow[party[i].get(0)]) ans++;
        }

        System.out.println(ans);
    }

    public static void dfs(int now){
        for (int next : graph[now]) {
            if(!isKnow[next]){
                isKnow[next] = true;
                dfs(next);
            }
        }
    }

}
