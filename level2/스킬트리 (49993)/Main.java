package com.company;
import java.util.*;

public class Main {
    public static void main(String[] args) {
        System.out.println(solution("CBD", new String[] {"BACDE", "CBADF", "AECB", "BDA"}));
    }

    public static int solution(String skill, String[] skill_trees) {
        int answer = 0;

        char[] skillOrder = skill.toCharArray();

        for(int i = 0; i < skill_trees.length; i++){
            int[] skillIdx = new int[skillOrder.length];

            String now = skill_trees[i];
            for(int j = 0; j < skillOrder.length; j++){
                if(now.contains(skillOrder[j]+"")){
                    skillIdx[j] = now.indexOf(skillOrder[j]);
                }
                else{
                    skillIdx[j] = Integer.MAX_VALUE;
                }
            }

            boolean flag = true;
            for(int j = 0; j < skillIdx.length-1; j++){
                if(skillIdx[j] > skillIdx[j+1]){
                    flag = false;
                    break;
                }
            }

            if(flag) answer++;
        }

        return answer;
    }

}
