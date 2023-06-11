import java.io.*;
import java.util.*;

public class Main {

    public static class Node{

        int num, parent;

        public Node(int num, int parent) {
            this.num = num;
            this.parent = parent;
        }

    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        StringBuilder sb = new StringBuilder();
        while(true){
            st = new StringTokenizer(br.readLine());
            int n = Integer.parseInt(st.nextToken());
            int k = Integer.parseInt(st.nextToken());

            if(n == 0 && k == 0) break;

            Node[] nodes = new Node[n];

            int targetParent = -1;
            int parentIdx = -1;

            st = new StringTokenizer(br.readLine());
            for(int i = 0; i < n; i++){
                int num = Integer.parseInt(st.nextToken());

                if(i == 0){
                    nodes[i] = new Node(num, -1);
                }
                else {
                    if(num - nodes[i-1].num != 1) parentIdx++;
                    nodes[i] = new Node(num, parentIdx);
                }

                if(num == k){
                    targetParent = nodes[i].parent;
                }
            }

            int cnt = 0;
            for(int i = 1; i < n; i++){
                Node node = nodes[i];

                if(nodes[node.parent].parent == nodes[targetParent].parent && node.parent != targetParent){
                    cnt++;
                }
            }

            sb.append(cnt).append("\n");
        }

        System.out.println(sb.toString());
    }


}
