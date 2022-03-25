package com.company;
import java.io.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Main {

    public static String result = "";
    public static Map<String, List<String>> tree = new HashMap<>();

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());

        for(int i = 0; i < n; i++){
            String[] str = br.readLine().split(" ");
            List<String> list = new ArrayList<>();

            list.add(str[1]);
            list.add(str[2]);
            tree.put(str[0], list);
        }

        preOrder("A");
        result += "\n";

        inOrder("A");
        result += "\n";

        postOrder("A");

        System.out.println(result);
    }

    public static void preOrder(String s){
        if(s.equals("."))
            return;

        result += s;
        preOrder(tree.get(s).get(0));
        preOrder(tree.get(s).get(1));
    }

    public static void inOrder(String s){
        if(s.equals("."))
            return;

        inOrder(tree.get(s).get(0));
        result += s;
        inOrder(tree.get(s).get(1));
    }

    public static void postOrder(String s){
        if(s.equals("."))
            return;

        postOrder(tree.get(s).get(0));
        postOrder(tree.get(s).get(1));
        result += s;
    }
}
