import java.util.*;

public class Main {
    public static void main(String[] args) {
        System.out.println(solution(new int[][]{{5,3},{11,5},{13,3},{3,5},{6,1},{1,3},{8,6},{7,2},{2,2}}));
    }

    public static class Node implements Comparable<Node>{
        int num, x, y;
        Node left;
        Node right;

        public Node(int num, int x, int y) {
            this.num = num;
            this.x = x;
            this.y = y;
        }

        @Override
        public int compareTo(Node o) {
            if(this.y == o.y){
                return this.x - o.x;
            }
            return o.y - this.y;
        }
    }

    public static Node[] nodes;
    public static int[][] answer;

    public static int[][] solution(int[][] nodeinfo) {
        answer = new int[2][nodeinfo.length];

        nodes = new Node[nodeinfo.length];

        for(int i = 0; i < nodeinfo.length; i++){
            nodes[i] = new Node(i+1, nodeinfo[i][0], nodeinfo[i][1]);
        }

        Arrays.sort(nodes);

        Node root = nodes[0];
        for(int i = 1; i < nodes.length; i++){
            addNode(root, nodes[i]);
        }

        preOrder(root, 0);
        postOrder(root, 0);

        return answer;
    }

    public static void addNode(Node root, Node node){
        if(root.x > node.x){
            if(root.left == null){
                root.left = node;
            } else{
                addNode(root.left, node);
            }
        }
        else{
            if(root.right == null){
                root.right = node;
            } else{
                addNode(root.right, node);
            }
        }
    }

    public static int preOrder(Node root, int idx){
        answer[0][idx] = root.num;
        if(root.left != null)
            idx = preOrder(root.left, idx+1);
        if(root.right != null)
            idx = preOrder(root.right, idx+1);

        return idx;
    }

    public static int postOrder(Node root, int idx){
        if(root.left != null)
            idx = postOrder(root.left, idx);
        if(root.right != null)
            idx = postOrder(root.right, idx);
        answer[1][idx] = root.num;

        return idx+1;
    }

}
