package com.company;
import java.util.*;

public class Main {
    public static void main(String[] args) {
        int[] tmp = solution(new String[] {"john", "mary", "edward", "sam", "emily", "jaimie", "tod", "young"},
                new String[] {"-", "-", "mary", "edward", "mary", "mary", "jaimie", "edward"},
                new String[] {"young", "john", "tod", "emily", "mary"},
                new int[] {12, 4, 2, 5, 10});
        for (int i : tmp) {
            System.out.print(i + " ");
        }
    }

    public static class Person{
        String name;
        Person parent;
        int money;

        public Person(String name, Person parent, int money) {
            this.name = name;
            this.parent = parent;
            this.money = money;
        }

        public void getReward(int m){
            int moneyToParent = (int)(m * 0.1);
            this.money += (m - moneyToParent);

            if(moneyToParent == 0) return;

            if(this.parent != null){
                this.parent.getReward(moneyToParent);
            }
        }
    }

    public static int[] solution(String[] enroll, String[] referral, String[] seller, int[] amount) {
        HashMap<String, Person> map = new HashMap<>();

        for (String name : enroll) {
            map.put(name, new Person(name, null, 0));
        }

        for(int i = 0; i < enroll.length; i++){
            if(referral[i].equals("-")) continue;

            map.get(enroll[i]).parent = map.get(referral[i]);
        }

        for(int i = 0; i < seller.length; i++){
            map.get(seller[i]).getReward(amount[i] * 100);
        }

        int[] ans = new int[enroll.length];
        for(int i = 0; i < ans.length; i++){
            ans[i] = map.get(enroll[i]).money;
        }

        return ans;
    }

}
