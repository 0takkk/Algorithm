import java.io.*;
import java.util.*;

public class Main {

    public static class Node implements Comparable<Node>{
        int start, end, weight;

        public Node(int start, int end, int weight) {
            this.start = start;
            this.end = end;
            this.weight = weight;
        }

        @Override
        public int compareTo(Node o) {
            if(this.end == o.end){
                return this.start - o.start;
            }
            return this.end - o.end;
        }

        @Override
        public String toString() {
            return "Node{" +
                    "start=" + start +
                    ", end=" + end +
                    ", weight=" + weight +
                    '}';
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        st = new StringTokenizer(br.readLine());
        int n = Integer.parseInt(st.nextToken());
        int c = Integer.parseInt(st.nextToken());

        int m = Integer.parseInt(br.readLine());

        Node[] nodes = new Node[m+1];

        for(int i = 1; i <= m; i++){
            st = new StringTokenizer(br.readLine());
            int start = Integer.parseInt(st.nextToken());
            int end = Integer.parseInt(st.nextToken());
            int weight = Integer.parseInt(st.nextToken());

            nodes[i] = new Node(start, end, weight);
        }

        Arrays.sort(nodes, 1, m+1);

        int[] weights = new int[n+1];  // 각 마을당 보낼 수 있는 최대 용량

        int result = 0;
        for(int i = 1; i <= m; i++){
            Node node = nodes[i];
            int start = node.start;
            int end = node.end;
            int weight = node.weight;

            int max = 0;
            boolean isLoad = true;

            for(int j = start; j < end; j++){
                if(weights[j] >= c){
                    isLoad = false;
                    break;
                }
                max = Math.max(max, weights[j]);
            }

            if(isLoad){
                int r = c - max; // 나눠서 보낼 무게 계산
                if(r > weight){
                    r = weight;
                }
                result += r;

                for(int j = start; j < end; j++){
                    weights[j] += r;
                }
            }

        }

        System.out.println(result);
    }

}
