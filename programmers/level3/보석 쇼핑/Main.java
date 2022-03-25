package com.company;
import java.util.*;

public class Main {
    public static void main(String[] args) {
        int[] tmp = solution(new String[] {"DIA", "RUBY", "RUBY", "DIA", "DIA", "EMERALD", "SAPPHIRE", "DIA"});
        for (int i : tmp) {
            System.out.print(i + " ");
        }
    }

    public static int[] solution(String[] gems) {
        int[] answer = new int[2];

        HashSet<String> set = new HashSet<>();
        set.addAll(Arrays.asList(gems));
        int size = set.size();

        HashMap<String, Integer> map = new HashMap<>();
        Queue<String> q = new LinkedList<>();
        int start = 0;
        int tmpStart = 0;
        int minCount = Integer.MAX_VALUE;

        for(int i = 0; i < gems.length; i++){
            map.put(gems[i], map.getOrDefault(gems[i], 0) + 1);
            q.add(gems[i]);

            while(true){
                String gem = q.peek();
                if(map.get(gem) > 1){
                    map.put(gem, map.get(gem) - 1);
                    q.poll();
                    tmpStart++;
                } else break;
            }

            if(map.size() == size){
                if(minCount > q.size()){
                    minCount = q.size();
                    start = tmpStart;
                }
            }

        }

        answer[0] = start + 1;
        answer[1] = start + minCount;

        return answer;
    }

}
