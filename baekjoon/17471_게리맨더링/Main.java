import java.io.*;
import java.util.*;

public class Main {

    public static int n, total, ans = Integer.MAX_VALUE;
    public static int[] people;
    public static ArrayList<Integer>[] graph;
    public static boolean[] visited;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        n = Integer.parseInt(br.readLine());
        people = new int[n+1];

        st = new StringTokenizer(br.readLine());
        for(int i = 1; i <= n; i++){
            people[i] = Integer.parseInt(st.nextToken());  // 인구 배열
            total += people[i];  // 인구 총 합
        }

        graph = new ArrayList[n+1];  // 관계
        for(int i = 1; i <= n; i++){
            graph[i] = new ArrayList<>();
            st = new StringTokenizer(br.readLine());
            int cnt = Integer.parseInt(st.nextToken());

            for(int j = 0; j < cnt; j++){
                graph[i].add(Integer.parseInt(st.nextToken()));
            }
        }

        visited = new boolean[n+1];
        for(int i = 1; i <= n/2; i++){  // combination으로 1 ~ n/2개 뽑아봄, 그 이상은 중복됨.
            combi(1, 0, i);
        }

        System.out.println(ans == Integer.MAX_VALUE ? -1 : ans);
    }

    public static void combi(int idx, int cnt, int max){
        if(cnt == max){  // max개를 뽑았으면 뽑힌 정점들이 2개의 부분으로 나누어졌는지 확인
            if(isTwoPartition(max)){  // 2개의 부분으로 나누어졌다면 최솟값 갱신
                int sum = 0;
                for(int i = 1; i <= n; i++){
                    if(visited[i]) sum += people[i];
                }

                ans = Math.min(ans, Math.abs(total-2*sum));
            }
            return;
        }

        for(int i = idx; i <= n; i++){
            if(!visited[i]){
                visited[i] = true;
                combi(i+1, cnt+1, max);
                visited[i] = false;
            }
        }
    }

    public static boolean isTwoPartition(int max){
        // 선택한 정점
        boolean[] vis = new boolean[n+1];
        Queue<Integer> q = new ArrayDeque<>();
        for(int i = 1; i <= n; i++){
            if(visited[i]){
                q.offer(i);  // 선택된 정점 중 하나 큐에 넣기
                vis[i] = true;
                break;
            }
        }

        // 선택된 정점을 bfs를 돌아 모두 연결되어 있는지 확인
        int cnt = 1;  // 카운트
        while(!q.isEmpty()){
            int now = q.poll();

            for (int next : graph[now]) {
                if(!vis[next] && visited[next]){   
                    vis[next] = true;
                    cnt++;
                    q.offer(next);
                }
            }
        }

        if(cnt != max) return false;  // 선택된 정점들이 연결되어 있지 않는 경우 return false


        // 선택 안 한 정점
        vis = new boolean[n+1];
        q = new ArrayDeque<>();
        for(int i = 1; i <= n; i++){
            if(!visited[i]){  // 선택되지 않은 정점들중 하나를 큐에 넣기
                q.offer(i);
                vis[i] = true;
                break;
            }
        }

        // 선택되지 않은 정점을 bfs를 돌아 모두 연결되어 있는지 확인
        cnt = 1;
        while(!q.isEmpty()){
            int now = q.poll();

            for (int next : graph[now]) {
                if(!vis[next] && !visited[next]){
                    vis[next] = true;
                    cnt++;
                    q.offer(next);
                }
            }
        }

        if(cnt != (n-max)) return false; // 선택되지 않은 정점들이 연결되어 있지 않는 경우 return false

        return true;  // 위에서 return 되지 않았으면 2개의 부분으로 나뉜것.
    }

}
