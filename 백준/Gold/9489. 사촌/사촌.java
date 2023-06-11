import java.io.*;
import java.util.*;

public class Main {

    public static int n, k;
    public static Node[] nodes;

    public static class Node{
        int num;
        int parent;
        int degree;

        public Node(int num, int parent, int degree) {
            this.num = num;
            this.parent = parent;
            this.degree = degree;
        }

        @Override
        public String toString() {
            return "Node{" +
                    "num=" + num +
                    ", parent=" + parent +
                    ", degree=" + degree +
                    '}';
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        StringBuilder sb = new StringBuilder();

        while(true){
            st = new StringTokenizer(br.readLine());
            n = Integer.parseInt(st.nextToken());
            k = Integer.parseInt(st.nextToken());

            if(n == 0 && k == 0) break;

            nodes = new Node[n];

            int targetParent = -1;
            int targetDegree = -1;
            int parent = -1;

            st = new StringTokenizer(br.readLine());
            for(int i = 0; i < n; i++){
                int num = Integer.parseInt(st.nextToken());

                if(i == 0){
                    nodes[i] = new Node(num, -1, 0);
                } else{
                    if(num - nodes[i-1].num != 1){
                        parent++;
                    }
                    nodes[i] = new Node(num, parent, nodes[parent].degree+1);
                }

                if(num == k){
                    targetParent = nodes[i].parent;
                    targetDegree = nodes[i].degree;
                }
            }

//            for(int i = 0; i < n; i++){
//                System.out.println(nodes[i]);
//            }

            int count = 0;
            for(int i = 1; i < n; i++){
                Node node = nodes[i];

                if(nodes[node.parent].parent == nodes[targetParent].parent && node.parent != targetParent){
                    count++;
                }
            }

            sb.append(count+"\n");
        }

        System.out.println(sb.toString());
    }

}