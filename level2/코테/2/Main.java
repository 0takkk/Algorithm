package com.company;
import java.util.*;

public class Main {
    public static void main(String[] args) {
        System.out.println(solution(new int[][] {{1,6}, {1, 3}, {3, 5}, {8, 10}, {1, 10}, {100, 200}, {200, 300}}));
    }

    public static int solution(int[][] work) {
        int answer = 0;

        Arrays.sort(work, new Comparator<int[]>(){
            public int compare(int[] t1, int[] t2){
                if(t1[0] > t2[0]) return 1;
                else if(t1[0] < t2[0]) return -1;
                else{
                    if(t1[1] > t2[1]) return -1;
                    else return 1;
                }
            }
        });

        for(int i = 0; i < work.length; i++){
            System.out.println(work[i][0] + " " + work[i][1]);
        }

        Stack<Integer> start = new Stack<>();
        Stack<Integer> end = new Stack<>();
        start.push(work[0][0]);
        end.push(work[0][1]);

        for(int i = 1; i < work.length; i++){
            if(work[i][0] == start.peek()) continue;

            if(start.peek() < work[i][0] && work[i][0] <= end.peek()){
                end.push(Math.max(end.pop(), work[i][1]));
            }
            else{
                start.push(work[i][0]);
                end.push(work[i][1]);
            }

            System.out.println(start);
            System.out.println(end);
        }

        int time = 0;
        while(!start.isEmpty()){
            int startTime = start.pop();
            int endTime = end.pop();

            time += (endTime - startTime);
        }
        
        return time;
    }

}
