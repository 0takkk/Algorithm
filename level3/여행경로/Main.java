package com.company;
import java.util.*;

public class Main {
    public static void main(String[] args) {
        String[] tmp = solution(new String[][]{{"ICN", "JFK"}, {"HND", "IAD"}, {"JFK", "HND"}});
        for (String s : tmp) {
            System.out.print(s + " ");
        }
    }

    public static boolean[] visited;
    public static ArrayList<String> result;

    public static String[] solution(String[][] tickets) {
        String[] answer = {};

        visited = new boolean[tickets.length];
        result = new ArrayList<>();

        dfs("ICN", "ICN", tickets, 0);

        Collections.sort(result);
        answer = result.get(0).split(" ");

        return answer;
    }

    public static void dfs(String from, String route, String[][] tickets, int cnt){
        if(cnt == tickets.length){
            result.add(route);
            return;
        }

        for(int i = 0; i < tickets.length; i++){
            if(from.equals(tickets[i][0]) && !visited[i]){
                visited[i] = true;
                dfs(tickets[i][1], route+ " " + tickets[i][1], tickets, cnt+1);
                visited[i] = false;
            }
        }
    }

}
