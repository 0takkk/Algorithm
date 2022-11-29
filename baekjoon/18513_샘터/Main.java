import java.io.*;
import java.util.*;

public class Main {

    public static class Node{
        int now, parent;

        public Node(int now, int parent) {
            this.now = now;
            this.parent = parent;
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        st = new StringTokenizer(br.readLine());
        int n = Integer.parseInt(st.nextToken());
        int k = Integer.parseInt(st.nextToken());

        Queue<Node> q = new ArrayDeque<>();
        HashSet<Integer> visited = new HashSet<>();

        st = new StringTokenizer(br.readLine());

        while(n-->0){
            int val = Integer.parseInt(st.nextToken());
            q.offer(new Node(val, val));
            visited.add(val);
        }

        long ans = 0;
        int cnt = 0;
        int[] dx = {1, -1};

        while(!q.isEmpty()){
            if(cnt >= k) break;

            Node node = q.poll();
            int now = node.now;
            int parent = node.parent;

            for(int i = 0; i < 2; i++){
                int next = now + dx[i];

                if(!visited.contains(next)){
                    q.offer(new Node(next, parent));
                    visited.add(next);
                    cnt++;
                    ans += Math.abs(parent - next);
                    if(cnt >= k) break;
                }
            }
        }

        System.out.println(ans);
    }

}
