package com.company;
import java.util.*;

public class Main {
    public static void main(String[] args) {
        System.out.println(solution("hit", "cog", new String[] {"hot", "dot", "dog", "lot", "log", "cog"}));
        System.out.println(solution("hit", "hot", new String[] {"hot", "dot", "dog", "lot", "log"}));
    }

    public static int solution(String begin, String target, String[] words) {
        int answer = 0;

        Queue<String> q = new LinkedList<>();
        q.offer(begin);
        boolean[] visited = new boolean[words.length];
        boolean flag = false;

        while(!q.isEmpty()){
            answer++;

            int size = q.size();
            for(int i = 0; i < size; i++) {
                String s = q.poll();

                if (s.equals(target)) {
                    flag = true;
                    break;
                }

                for(int j = 0; j < words.length; j++){
                    int count = 0;

                    if(!visited[j]){
                        for(int k = 0; k < s.length(); k++){
                            if(s.charAt(k) != words[j].charAt(k)){
                                count++;
                            }
                            if(count >= 2) break;
                        }
                    }

                    if(count == 1){
                        q.offer(words[j]);
                        System.out.println(words[j]);
                        System.out.println(answer);
                        visited[j] = true;
                    }

                }
            }

            if(flag) break;
        }

        if(flag) return answer-1;
        else return 0;
    }

}
