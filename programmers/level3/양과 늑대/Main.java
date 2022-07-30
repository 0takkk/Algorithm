import java.util.*;

class Solution {
    
    public static int n, MAX;
    public static ArrayList<Integer>[] graph;
    public static int[] Info;
    
    public int solution(int[] info, int[][] edges) {
        int answer = 0;
        Info = info;
        
        n = info.length;
        graph = new ArrayList[n];
        for(int i = 0; i < n; i++){
            graph[i] = new ArrayList<>();
        }
        
        for(int[] edge : edges){
            graph[edge[0]].add(edge[1]);
        }
        
        ArrayList<Integer> list = new ArrayList<>();
        list.add(0);
        dfs(0, 0, 0, list);
        
        return MAX;
    }
    
    public static void dfs(int idx, int s, int w, List<Integer> list){
        if(Info[idx] == 0) s++;
        else w++;
        
        if(w >= s) return;
        MAX = Math.max(MAX, s);
        
        ArrayList<Integer> next = new ArrayList<>();
        next.addAll(list);
        next.remove(Integer.valueOf(idx));
        
        for(int child : graph[idx]){
            next.add(child);
        }
        
        for(int n : next){
            dfs(n, s, w, next);
        }
    }
    
}
