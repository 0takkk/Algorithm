package com.company;
import java.io.*;
import java.util.*;

public class Main {
    public static int n, m;
    public static String[] people;
    public static HashMap<String, Integer> map = new HashMap<>();
    public static ArrayList<Integer>[] list;
    public static int degree[];

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        n = Integer.parseInt(br.readLine());
        people = new String[n];
        degree = new int[n];

        st = new StringTokenizer(br.readLine());
        for(int i = 0; i < n; i++)
            people[i] = st.nextToken();
        Arrays.sort(people);

        for(int i = 0; i < n; i++)
            map.put(people[i], i);

        m = Integer.parseInt(br.readLine());

        list = new ArrayList[n];
        for(int i = 0; i < n; i++)
            list[i] = new ArrayList<>();

        for(int i = 0; i < m; i++){
            st = new StringTokenizer(br.readLine());
            int child = map.get(st.nextToken());
            int parent = map.get(st.nextToken());

            list[parent].add(child);
            degree[child]++;
        }

        solve();

    }

    public static void solve(){
        Queue<Integer> q = new LinkedList<>();
        ArrayList<Integer> root = new ArrayList<>();

        for(int i = 0; i < n; i++){
            if(degree[i] == 0){
                q.offer(i);
                root.add(i);
            }
        }

        ArrayList<Integer>[] children = new ArrayList[n];
        for(int i = 0; i < n; i++)
            children[i] = new ArrayList<>();

        while(!q.isEmpty()){
            int now = q.poll();

            for(int next : list[now]){
                degree[next]--;
                if(degree[next] == 0){
                    children[now].add(next);
                    q.offer(next);
                }
            }
        }

        System.out.println(root.size());
        for(int idx : root)
            System.out.print(people[idx] + " ");

        System.out.println();

        for(int i = 0; i < n; i++){
            System.out.print(people[i] + " " + children[i].size() + " ");
            children[i].sort(null);
            for(int idx : children[i]){
                System.out.print(people[idx] + " ");
            }
            System.out.println();
        }
    }
}
