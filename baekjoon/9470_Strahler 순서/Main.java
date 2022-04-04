import java.io.*;
import java.util.*;

public class Main {

    public static int k, m, p;
    public static int[] strahler, edge;
    public static ArrayList<Integer>[] graph, reverse;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        int t = Integer.parseInt(br.readLine());
        StringBuilder sb = new StringBuilder();

        while(t-->0){
            st = new StringTokenizer(br.readLine());
            k = Integer.parseInt(st.nextToken());
            m = Integer.parseInt(st.nextToken());
            p = Integer.parseInt(st.nextToken());

            strahler = new int[m+1];
            edge = new int[m+1];
            graph = new ArrayList[m+1];
            reverse = new ArrayList[m+1];
            for(int i = 1; i <= m; i++){
                graph[i] = new ArrayList<>();
                reverse[i] = new ArrayList<>();
            }

            for(int i = 0; i < p; i++){
                st = new StringTokenizer(br.readLine());
                int a = Integer.parseInt(st.nextToken());
                int b = Integer.parseInt(st.nextToken());

                edge[b]++;
                graph[a].add(b);
                reverse[b].add(a);
            }

            int max = topologicalSort();

            sb.append(k + " " + max + '\n');
        }
        System.out.println(sb.toString());
    }

    public static int topologicalSort(){
        Queue<Integer> q = new LinkedList<>();

        for(int i = 1; i <= m; i++){
            if(edge[i] == 0){
                q.offer(i);
                strahler[i] = 1;
            }
        }

        int answer = 0;

        while(!q.isEmpty()){
            int now = q.poll();

            for(int next : graph[now]){
                edge[next]--;
                if(edge[next] == 0){
                    q.offer(next);

                    int max = 0;
                    int cnt = 1;

                    for(int prev : reverse[next]){
                        if(max < strahler[prev]){
                            max = strahler[prev];
                            cnt = 1;
                        } else if(max == strahler[prev]){
                            cnt++;
                        }
                    }

                    if(cnt >= 2) strahler[next] = max+1;
                    else strahler[next] = max;
                    
                    answer = Math.max(answer, strahler[next]);
                }

            }
        }

        return answer;
    }

}
