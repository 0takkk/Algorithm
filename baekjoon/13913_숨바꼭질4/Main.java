import java.io.*;
import java.util.*;

public class Main {

    public static int time = 0;
    public static int[] parent;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        st = new StringTokenizer(br.readLine());
        int n = Integer.parseInt(st.nextToken());
        int k = Integer.parseInt(st.nextToken());

        bfs(n, k);

        Stack<Integer> stack = new Stack<>();
        int idx = k;
        while(idx != n){
            stack.push(idx);
            idx = parent[idx];
        }
        stack.push(n);

        StringBuilder sb = new StringBuilder();
        while(!stack.isEmpty()){
            sb.append(stack.pop() + " ");
        }

        System.out.println(time);
        System.out.println(sb.toString());
    }

    public static void bfs(int n, int k){
        Queue<Integer> q = new LinkedList<>();
        boolean[] visited = new boolean[100001];
        parent = new int[100001];
        q.offer(n);
        visited[n] = true;

        while(!q.isEmpty()){

            int size = q.size();
            for(int i = 0; i < size; i++) {
                int x = q.poll();

                if (x == k) return;

                for(int j = 0; j < 3; j++){
                    int nx;

                    if(j == 0) nx = x + 1;
                    else if(j == 1) nx = x - 1;
                    else nx =  x * 2;

                    if(nx < 0 || nx > 100000) continue;
                    if(!visited[nx]) {
                        visited[nx] = true;
                        q.offer(nx);
                        parent[nx] = x;
                    }
                }
            }
            time++;
        }
    }

}
