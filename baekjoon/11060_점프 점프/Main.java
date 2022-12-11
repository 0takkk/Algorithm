import java.io.*;
import java.util.*;

public class Main {

    public static int n;
    public static int[] arr;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        n = Integer.parseInt(br.readLine());
        arr = new int[n];

        st = new StringTokenizer(br.readLine());
        for(int i = 0; i < n; i++){
            arr[i] = Integer.parseInt(st.nextToken());
        }

        System.out.println(bfs());
    }

    public static int bfs(){
        boolean[][] visited = new boolean[1001][n];
        Queue<Integer> q = new ArrayDeque<>();
        q.offer(0);
        visited[0][0] = true;

        int time = 0;

        while(!q.isEmpty()){
            int size = q.size();
            while(size-->0) {
                int now = q.poll();

                if (now == n - 1) return time;

                for (int i = 1; i <= arr[now]; i++) {
                    int next = now + i;

                    if (next >= n) continue;

                    if (!visited[time][next]) {
                        visited[time][next] = true;
                        q.offer(next);
                    }
                }
            }
            time++;
        }

        return -1;
    }

}
