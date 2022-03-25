package com.company;
import java.util.*;

public class Main {
    public static void main(String[] args) {
	    System.out.println(solution(new String[][] {
                {"100","ryan","music","2"},
                {"200","apeach","math","2"},
                {"300","tube","computer","3"},
                {"400","con","computer","4"},
                {"500","muzi","music","3"},
                {"600","apeach","music","2"}}));
    }

    public static ArrayList<String> combi_keys = new ArrayList<>();
    public static ArrayList<List<String>> candidate_keys = new ArrayList<>();

    public static int solution(String[][] relation) {
        int answer = 0;
        int column = relation[0].length;
        boolean[] visited = new boolean[column];

        for(int i = 1; i <= column; i++){
            combi(visited, 0, i);
        }

        for(String i : combi_keys){
            checkUnique(i, relation);
        }

        answer = candidate_keys.size();
        return answer;
    }

    public static void checkUnique(String str, String[][] relation){
        String[] keys = str.split("");
        HashSet<String> set = new HashSet<>();

        for(int i = 0; i < relation.length; i++){
            String tmp = "";
            for(int j = 0; j < keys.length; j++){
                tmp += relation[i][Integer.parseInt(keys[j])] + " ";
            }
            set.add(tmp);
        }

        if(set.size() == relation.length){
            checkMinimal(keys);
        }
    }

    public static void checkMinimal(String[] keys){
        List<String> key = Arrays.asList(keys);
        boolean flag = true;

        for(int i = 0; i < candidate_keys.size(); i++){
            if(key.containsAll(candidate_keys.get(i))){
                flag = false;
                break;
            }
        }

        if(flag)
            candidate_keys.add(key);
    }

    public static void combi(boolean[] visited, int start, int r){
        if(r == 0){
            String num = "";
            for(int i = 0; i < visited.length; i++){
                if(visited[i]){
                    num = num + i;
                }
            }

            combi_keys.add(num);
        }
        else{
            for(int i = start; i < visited.length; i++){
                visited[i] = true;
                combi(visited, i+1, r-1);
                visited[i] = false;
            }
        }
    }

}
