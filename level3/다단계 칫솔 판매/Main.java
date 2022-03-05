package com.company;
import java.util.*;

public class Main {
    public static void main(String[] args) {
//        System.out.println(solution("hit", "hot", new String[] {"hot", "dot", "dog", "lot", "log"}));
        int[] tmp = solution(new String[] {"john", "mary", "edward", "sam", "emily", "jaimie", "tod", "young"},
                new String[] {"-", "-", "mary", "edward", "mary", "mary", "jaimie", "edward"},
                new String[] {"young", "john", "tod", "emily", "mary"},
                new int[] {12, 4, 2, 5, 10});
        for (int i : tmp) {
            System.out.print(i + " ");
        }
    }

    public static HashMap<String, Integer> map;
    public static HashMap<Integer, Integer> parents;
    public static LinkedHashMap<Integer, Integer> money;

    public static int[] solution(String[] enroll, String[] referral, String[] seller, int[] amount) {

        map = new HashMap<>();
        map.put("-", 0);
        for(int i = 0; i < enroll.length; i++){
            map.put(enroll[i], i+1);
        }

        parents = new HashMap<>();
        money = new LinkedHashMap<>();
        money.put(0, 0);
        for(int i = 0; i < enroll.length; i++){
            parents.put(map.get(enroll[i]), map.get(referral[i]));
            money.put(map.get(enroll[i]), 0);
        }

        for(int i = 0; i < seller.length; i++){
            dfs(map.get(seller[i]), amount[i]*100);
        }

        int[] answer = new int[enroll.length];

        for (Integer m : money.keySet()) {
            if(m != 0){
                answer[m-1] = money.get(m);
            }
        }

        return answer;
    }

    public static void dfs(int seller, int amount){
        if(seller == 0) {
            money.put(seller, money.get(seller) + amount);
            return;
        }
        else if(amount == 0) return;

        if(parents.containsKey(seller)) {
            int parent = parents.get(seller);
            money.put(seller, money.get(seller) + amount - (int)(amount * 0.1));
            dfs(parent, (int)(amount * 0.1));
        }
    }

}
