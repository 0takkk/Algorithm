import java.util.*;

public class Main {
    
    public static void main(String[] args) {
        System.out.println(solution(new int[][] {{2,2},{2,4},{3,2},{3,2},{2,1}}));
    }

    public static int solution(int[][] scores) {
        int[] wanho = scores[0];
        Arrays.sort(scores, (a, b) -> a[0] == b[0] ? a[1] - b[1] : b[0] - a[0]);

        int ans = 1;
        int max = 0;
        int wanhoSum = wanho[0] + wanho[1];

        for (int[] score : scores) {
            if(score[1] < max){
                if(score.equals(wanho)) return -1;
            }
            else{
                max = Math.max(max, score[1]);
                if(score[0] + score[1] > wanhoSum){
                    ans++;
                }
            }
        }

        return ans;
    }

}
