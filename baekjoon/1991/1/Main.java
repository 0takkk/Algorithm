package com.company;
import java.io.*;

public class Main {
    static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

    public static class Node{
        char data;
        Node left;
        Node right;

        public Node(char data){
            this.data = data;
        }
    }

    public static class Tree{
        Node root;

        public void addNode(char data, char leftdata, char rightdata){
            if(root == null){  // 비어있을 경우
                root = new Node(data);

                if(leftdata != '.'){
                    root.left = new Node(leftdata);
                }
                if(rightdata != '.'){
                    root.right = new Node(rightdata);
                }
            }else{  // 비어있지 않을 경우
                search(root, data, leftdata, rightdata);
            }

        }

        public void search(Node root, char data, char leftdata, char rightdata){
            if(root == null)
                return;
            else if(root.data == data){
                if(leftdata != '.'){
                    root.left = new Node(leftdata);
                }
                if(rightdata != '.'){
                    root.right = new Node(rightdata);
                }
            }
            else{
                search(root.left, data, leftdata, rightdata);
                search(root.right, data, leftdata, rightdata);
            }
        }

        public void preOrder(Node root) throws IOException{
            bw.write(root.data);
            if(root.left != null)
                preOrder(root.left);
            if(root.right != null)
                preOrder(root.right);
        }

        public void inOrder(Node root) throws IOException{
            if(root.left != null)
                inOrder(root.left);
            bw.write(root.data);
            if(root.right != null)
                inOrder(root.right);
        }

        public void postOrder(Node root) throws IOException{
            if(root.left != null)
                postOrder(root.left);
            if(root.right != null)
                postOrder(root.right);
            bw.write(root.data);
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());

        Tree tree = new Tree();

        for(int i = 0; i < n; i++){
            char[] data;
            data = br.readLine().replaceAll(" ", "").toCharArray();
            tree.addNode(data[0], data[1], data[2]);
        }

        tree.preOrder(tree.root);
        bw.write("\n");

        tree.inOrder(tree.root);
        bw.write("\n");

        tree.postOrder(tree.root);

        bw.flush();
        bw.close();
        br.close();
    }
}
