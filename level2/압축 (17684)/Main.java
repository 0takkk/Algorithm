package com.company;
import java.util.*;

public class Main {
    public static void main(String[] args) {
        System.out.println(solution("KAKAO"));
        System.out.println(solution("TOBEORNOTTOBEORTOBEORNOT"));
        System.out.println(solution("ABABABABABABABAB"));
    }

    public static HashMap<String, Integer> map = new HashMap<>();

    public static int[] solution(String msg) {

        for(int i = 1; i <= 26; i++){
            map.put(Character.toString((char)('A'+(i-1))), i);
        }

        ArrayList<Integer> result = new ArrayList<>();

        int idx = 27;
        boolean flag = false;

        for(int i = 0; i < msg.length(); i++){
            String now = Character.toString(msg.charAt(i));

            while(map.containsKey(now)){
                i++;
                if(i == msg.length()){
                    flag = true;
                    break;
                }
                now += msg.charAt(i);
            }

            if(flag){
                result.add(map.get(now));
                break;
            }

            result.add(map.get(now.substring(0, now.length() - 1)));
            map.put(now, idx++);

            i--;
        }

        int[] answer = new int[result.size()];
        for(int i = 0; i < answer.length; i++){
            answer[i] = result.get(i);
        }
        return answer;
    }

}
