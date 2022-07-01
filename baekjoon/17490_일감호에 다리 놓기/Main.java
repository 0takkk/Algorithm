import java.io.*;
import java.util.*;

public class Main {

    public static class Node implements Comparable<Node>{
        int start, end;

        public Node(int start, int end) {
            this.start = start;
            this.end = end;
        }

        @Override
        public int compareTo(Node o) {
            return this.start - o.start;
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        st = new StringTokenizer(br.readLine());
        int n = Integer.parseInt(st.nextToken());
        int m = Integer.parseInt(st.nextToken());
        long k = Long.parseLong(st.nextToken());

        int[] stone = new int[n+1];
        st = new StringTokenizer(br.readLine());
        for(int i = 1; i <= n; i++){
            stone[i] = Integer.parseInt(st.nextToken());
        }

        if(m <= 1) {
            System.out.println("YES");
            return;
        }

        ArrayList<Node> list = new ArrayList<>();
        boolean flag = false;

        while(m-->0){
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());

            int front = Math.min(a, b);
            int back = Math.max(a, b);

            if(front == 1 && back == n){
                flag = true;
                list.add(new Node(back, front));
                continue;
            }

            list.add(new Node(front, back));
        }

        Collections.sort(list);

        long sum = 0;
        int idx = 1;
        for(int i = 0; i < list.size(); i++){
            int min = Integer.MAX_VALUE;

            Node node = list.get(i);
            for(int j = idx; j <= node.start; j++){
                min = Math.min(min, stone[j]);
            }

            if(idx == 1 && !flag){
                for(int j = n; j >= list.get(list.size()-1).end; j--) {
                    min = Math.min(min, stone[j]);
                }
            }

            sum += (long)min;
            idx = node.end;
        }

        if(k >= sum) System.out.println("YES");
        else System.out.println("NO");
    }

}
