package com.company;
import java.io.*;
import java.util.*;

public class Main {
    public static int n, m, size;
    public static ArrayList<Integer>[] list;
    public static int count[], targetPotion[];
    public static Stack<Integer> stack = new Stack<>();
    public static HashSet<Integer> hasPotion = new HashSet<>();

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());

        count = new int[m];
        targetPotion = new int[m];
        list = new ArrayList[n+1];
        for(int i = 1; i <= n; i++)
            list[i] = new ArrayList<>();

        for(int i = 0; i < m; i++){
            st = new StringTokenizer(br.readLine());
            count[i] = (size = Integer.parseInt(st.nextToken()));
            for(int j = 0; j < size; j++){
                list[Integer.parseInt(st.nextToken())].add(i);
            }
            targetPotion[i] = Integer.parseInt(st.nextToken());
        }

        size = Integer.parseInt(br.readLine());
        st = new StringTokenizer(br.readLine());
        for(int i = 0; i < size; i++){
            stack.add(Integer.parseInt(st.nextToken()));
            hasPotion.add(stack.peek());
        }

        solution();
        System.out.println(hasPotion.size());

        PriorityQueue<Integer> pq = new PriorityQueue<>();
        pq.addAll(hasPotion);

        StringBuilder sb = new StringBuilder();
        while(!pq.isEmpty())
            sb.append(pq.poll() + " ");

        System.out.println(sb.toString());
    }

    public static void solution(){
        while(!stack.isEmpty()){
            int now = stack.pop();

            for(int next : list[now]){
                if(--count[next] == 0 && !hasPotion.contains(targetPotion[next])){
                    stack.add(targetPotion[next]);
                    hasPotion.add(targetPotion[next]);
                }
            }
        }
    }
}
