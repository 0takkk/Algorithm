import java.util.*;

class Solution {
   
     public static class People {
        HashMap<String, Person> people = new HashMap<>();

        public People() {
            people.put("-", new Person(-1, "-", null, 0));
        }

        public Person findByName(String name) {
            return people.get(name);
        }

        public void addPerson(int idx, String name, String bossName) {
            Person boss = findByName(bossName);
            people.put(name, new Person(idx, name, boss, 0));
        }
    }

    public static class Person {
        int idx;
        String name;
        Person boss;
        int cost;

        public Person(int idx, String name, Person boss, int cost) {
            this.idx = idx;
            this.name = name;
            this.boss = boss;
            this.cost = cost;
        }

        public String getName() {
            return name;
        }

        public void calculateCost(int cost) {
            if(boss == null) {
                this.cost += cost;
                return;
            }

            int duty = (int)(cost * 0.1);
            this.cost += (cost - duty);
            this.boss.calculateCost(duty);
        }
    }

    public static int[] solution(String[] enroll, String[] referral, String[] seller, int[] amount) {
        People people = new People();

        for(int i = 0; i < enroll.length; i++) {
            people.addPerson(i, enroll[i], referral[i]);
        }

        for(int i = 0; i < seller.length; i++) {
            String name = seller[i];
            Person person = people.findByName(name);
            person.calculateCost(amount[i] * 100);
        }

        int[] answer = new int[enroll.length];
        HashMap<String, Person> result = people.people;
        for (String name : result.keySet()) {
            Person person = result.get(name);
            if(person.boss != null) {
                answer[person.idx] = person.cost;
            }
        }

        return answer;
    }
}