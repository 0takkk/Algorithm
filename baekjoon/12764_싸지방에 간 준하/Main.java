import java.io.*;
import java.util.*;

public class Main {

    public static class Node implements Comparable<Node>{
        int num;
        int end;

        public Node(int num, int end) {
            this.num = num;
            this.end = end;
        }

        @Override
        public int compareTo(Node o) {
            return this.end - o.end;
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        int n = Integer.parseInt(br.readLine());
        int[][] arr = new int[n][2];

        for(int i = 0; i < n; i++){
            st = new StringTokenizer(br.readLine());
            arr[i][0] = Integer.parseInt(st.nextToken());
            arr[i][1] = Integer.parseInt(st.nextToken());
        }

        Arrays.sort(arr, new Comparator<int[]>() {
            @Override
            public int compare(int[] o1, int[] o2) {
                return o1[0] - o2[0];
            }
        });

        PriorityQueue<Node> seat = new PriorityQueue<>();
        PriorityQueue<Integer> vacant = new PriorityQueue<>();
        int[] count = new int[100001];

        seat.offer(new Node(1, arr[0][1]));
        count[1] = 1;

        int idx = 1;

        for(int i = 1; i < n; i++){
            int start = arr[i][0];
            int end = arr[i][1];

            while(!seat.isEmpty() && seat.peek().end <= start){
                int num = seat.poll().num;
                vacant.offer(num);
            }

            if(vacant.isEmpty()){
                seat.offer(new Node(++idx, end));
                count[idx]++;
            }
            else{
                int num = vacant.poll();
                seat.offer(new Node(num, end));
                count[num]++;
            }
        }

        StringBuilder sb = new StringBuilder();
        sb.append(idx+"\n");
        for(int i = 1; i < count.length; i++){
            if(count[i] == 0 ) break;
            sb.append(count[i] + " ");
        }

        System.out.println(sb.toString());
    }

}
