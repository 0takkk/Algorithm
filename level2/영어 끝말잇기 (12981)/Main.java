package com.company;
import java.util.*;

public class Main {
    public static void main(String[] args) {
	    //System.out.println(solution(3, new String[] {"tank", "kick", "know", "wheel", "land", "dream", "mother", "robot", "tank"}));
        int[] tmp = solution(3, new String[] {"tank", "kick", "know", "wheel", "land", "dream", "mother", "robot", "tank"});
        System.out.println(tmp[0] + " " + tmp[1]);
    }

    public static int[] solution(int n, String[] words) {
        int[] answer = new int[2];

        HashSet<String> set = new HashSet<>();

        String word;
        char first= ' ', last = ' ';

        for(int i = 0; i < words.length; i++){
            word = words[i];

            if(set.contains(word)){
                answer[0] = (i % n) + 1;
                answer[1] = (i / n) + 1;
                break;
            }

            set.add(word);

            first = word.charAt(0);
            if(i > 0){
                if(first != last){
                    answer[0] = (i % n) + 1;
                    answer[1] = (i / n) + 1;
                    break;
                }
            }
            last = word.charAt(word.length()-1);

        }

        return answer;
    }

}
