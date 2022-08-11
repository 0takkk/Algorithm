import java.io.*;
import java.util.*;

public class Main {

    public static int n, min = Integer.MAX_VALUE;
    public static int[] people;
    public static ArrayList<Integer>[] graph;
    public static HashSet<Integer> set;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        n = Integer.parseInt(br.readLine());
        people = new int[n+1];
        graph = new ArrayList[n+1];
        set = new HashSet<>();

        StringTokenizer st1 = new StringTokenizer(br.readLine());
        for(int i = 1; i <= n; i++){
            people[i] = Integer.parseInt(st1.nextToken());

            st = new StringTokenizer(br.readLine());
            int num = Integer.parseInt(st.nextToken());
            graph[i] = new ArrayList<>();

            while(num-->0){
                graph[i].add(Integer.parseInt(st.nextToken()));
            }
        }

        for(int i = 1; i <= n/2; i++){
            combi(1, 0, i);
        }

        System.out.println(min == Integer.MAX_VALUE ? -1 : min);
    }

    public static void combi(int idx, int cnt, int max){
        if(cnt == max){
            if(isUnion()) getMin();
            return;
        }

        for(int i = idx; i <= n; i++){
            set.add(i);
            combi(i+1, cnt+1, max);
            set.remove(i);
        }
    }

    public static void getMin(){
        int a = 0;
        int b = 0;

        for(int i = 1; i <= n; i++){
            if(set.contains(i)) a += people[i];
            else b += people[i];
        }

        min = Math.min(min, Math.abs(a-b));
    }

    public static boolean isUnion(){
        Queue<Integer> q = new ArrayDeque<>();
        boolean[] visited = new boolean[n+1];
        int count = 1;
        int start = 0;
        for (int s : set) {
            start = s;
            break;
        }

        visited[start] = true;
        q.offer(start);

        while(!q.isEmpty()){
            int now = q.poll();

            for (int next : graph[now]) {
                if(!visited[next] && set.contains(next)){
                    visited[next] = true;
                    q.add(next);
                    count++;
                }
            }
        }

        if(count != set.size()) return false;

        for(int i = 1; i <= n; i++){
            if(!set.contains(i)){
                start = i;
                break;
            }
        }

        q = new ArrayDeque<>();
        visited = new boolean[n+1];
        count = 1;

        visited[start] = true;
        q.offer(start);

        while(!q.isEmpty()){
            int now = q.poll();

            for (int next : graph[now]) {
                if(!visited[next] && !set.contains(next)){
                    visited[next] = true;
                    q.offer(next);
                    count++;
                }
            }
        }

        if(count != n - set.size()) return false;

        return true;
    }


}
