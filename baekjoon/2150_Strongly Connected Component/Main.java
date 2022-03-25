package com.company;
import java.io.*;
import java.util.*;

public class Main {

    public static int v, e, num, count;
    public static ArrayList<Integer>[] graph;
    public static int[] order;
    public static boolean[] visited;
    public static Stack<Integer> stack;
    public static ArrayList<Integer>[] result;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        st = new StringTokenizer(br.readLine());
        v = Integer.parseInt(st.nextToken());
        e = Integer.parseInt(st.nextToken());

        graph = new ArrayList[v+1];
        for(int i = 1; i <= v; i++) graph[i] = new ArrayList<>();

        for(int i = 0; i < e; i++){
            st = new StringTokenizer(br.readLine());

            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());

            graph[a].add(b);
        }

        visited = new boolean[v+1];
        order = new int[v+1];
        stack = new Stack<>();
        result = new ArrayList[v+1];
        for(int i = 0; i <= v; i++) result[i] = new ArrayList<>();

        for(int i = 1; i <= v; i++){
            if(order[i] == 0) dfs(i);
        }

        StringBuilder sb = new StringBuilder();

        sb.append(count + "\n");

        for (ArrayList<Integer> tmp : result) {
            if(tmp.size() > 0){
                for (Integer i : tmp) {
                    sb.append(i + " ");
                }
                sb.append("-1\n");
            }
        }

        System.out.println(sb.toString());
    }

    public static int dfs(int idx){
        order[idx] = ++num;
        stack.add(idx);
        int parent = order[idx];

        for(int next : graph[idx]){
            if(order[next] == 0) parent = Math.min(parent, dfs(next));  // 방문하지 않은 이웃
            else if(!visited[next]) parent = Math.min(parent, order[next]);  // 처리중인 이웃
        }

        if(parent == order[idx]){  // 부모가 자기 자신인 경우
            ArrayList<Integer> tmp = new ArrayList<>();
            while(true){
                int top = stack.pop();
                tmp.add(top);
                visited[top] = true;
                if(top == idx) break;
            }
            Collections.sort(tmp);
            int min = tmp.get(0);
            result[min] = tmp;
            count++;
        }

        return parent;
    }

}
