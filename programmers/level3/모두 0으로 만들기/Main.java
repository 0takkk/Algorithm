import java.util.*;

public class Main {
    public static void main(String[] args) {
        System.out.println(solution(new int[] {-5,0,2,1,2},  new int[][] {{0,1},{3,4},{2,3},{0,3}}));
    }

    static long answer, arr[];
    static boolean[] visited;
    static List<Integer>[] list;
    public static long solution(int[] a, int[][] edges) {
        int length = a.length;
        arr = new long[length];
        list = new ArrayList[length];
        long sum = 0;

        for(int i = 0; i < length; i++) {
            list[i] = new ArrayList<>();
            sum += arr[i] = a[i];
        }

        if(sum != 0) return -1;

        for(int i = 0; i < edges.length; i++) {
            list[edges[i][0]].add(edges[i][1]);
            list[edges[i][1]].add(edges[i][0]);
        }

        visited = new boolean[length];
        visited[0] = true;
        dfs(0);

        return answer;
    }
    
    private static long dfs(int idx) {
        
        for(int i = 0; i < list[idx].size(); i++) {
            int next_vertex = list[idx].get(i);
            if(visited[next_vertex]) continue;
            visited[next_vertex] = true;
            arr[idx] += dfs(next_vertex);
        }

        answer += Math.abs(arr[idx]);
        return arr[idx];
    }
}
