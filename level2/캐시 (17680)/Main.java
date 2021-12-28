package com.company;
import java.util.*;

public class Main {
    public static void main(String[] args) {
	    System.out.println(solution(3, new String[] {"Jeju", "Pangyo", "Seoul", "NewYork", "LA", "Jeju", "Pangyo", "Seoul", "NewYork", "LA"}));
        System.out.println(solution(3, new String[] {"Jeju", "Pangyo", "Seoul", "Jeju", "Pangyo", "Seoul", "Jeju", "Pangyo", "Seoul"}));
        System.out.println(solution(2, new String[] {"Jeju", "Pangyo", "Seoul", "NewYork", "LA", "SanFrancisco", "Seoul", "Rome", "Paris", "Jeju", "NewYork", "Rome"}));
        System.out.println(solution(5, new String[] {"Jeju", "Pangyo", "Seoul", "NewYork", "LA", "SanFrancisco", "Seoul", "Rome", "Paris", "Jeju", "NewYork", "Rome"}));
        System.out.println(solution(2, new String[] {"Jeju", "Pangyo", "NewYork", "newyork"}));
        System.out.println(solution(0, new String[] {"Jeju", "Pangyo", "Seoul", "NewYork", "LA"}));
    }

    public static int solution(int cacheSize, String[] cities) {
        int answer = 0;
        ArrayList<String> page = new ArrayList<>();

        if(cacheSize == 0)
            return 5 * cities.length;

        for(String city : cities){
            city = city.toLowerCase();

            if(!page.contains(city)){
                if(page.size() < cacheSize){
                    page.add(city);
                    answer += 5;
                }else{
                    page.remove(0);
                    page.add(city);
                    answer += 5;
                }
            }else{
                page.remove(page.indexOf(city));
                page.add(city);
                answer += 1;
            }
        }
        return answer;
    }
}
