import java.io.*;
import java.util.*;

public class Main {

    public static class Node implements Comparable<Node>{
        int num, end;

        public Node(int num, int end) {
            this.num = num;
            this.end = end;
        }

        @Override
        public int compareTo(Node o) {
            if(this.end == o.end) return this.num - o.num;
            return this.end - o.end;
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        int n = Integer.parseInt(br.readLine());
        int[][] arr = new int[n+1][2];

        for(int i = 1; i <= n; i++){
            st = new StringTokenizer(br.readLine());
            arr[i][0] = Integer.parseInt(st.nextToken());
            arr[i][1] = Integer.parseInt(st.nextToken());
        }

        Arrays.sort(arr, new Comparator<int[]>() {
            @Override
            public int compare(int[] o1, int[] o2) {
                if(o1[0] == o2[0]) return o1[1] - o2[1];
                return o1[0] - o2[0];
            }
        });

        int idx = 1;
        int[] seat = new int[100001];
        PriorityQueue<Integer> space = new PriorityQueue<>();
        PriorityQueue<Node> pq = new PriorityQueue<>();

        pq.offer(new Node(1, arr[1][1]));
        seat[1] = 1;

        for(int i = 2; i <= n; i++){
            int start = arr[i][0];
            int end = arr[i][1];

            if(!pq.isEmpty() && pq.peek().end <= start){
                while(!pq.isEmpty() && pq.peek().end <= start){
                    space.offer(pq.poll().num);
                }
                int num = space.poll();
                seat[num]++;
                pq.offer(new Node(num, end));
            }
            else{
                int num;

                if(space.isEmpty()) num = ++idx;
                else num = space.poll();

                pq.offer(new Node(num, end));
                seat[num]++;
            }
        }

        StringBuilder sb = new StringBuilder();
        int size = 0;
        for(int i = 1; i < seat.length; i++){
            if(seat[i] == 0) break;
            size++;
            sb.append(seat[i] + " ");
        }

        System.out.println(size);
        System.out.println(sb.toString());
    }

}
