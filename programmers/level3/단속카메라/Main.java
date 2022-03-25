import java.util.*;

public class Main {
    public static void main(String[] args) {
        System.out.println(solution(new int[][] {{-20,-15}, {-14,-5}, {-18,-13}, {-5,-3}}));
        System.out.println(solution(new int[][] {{1, 6}, {7, 10}, {3, 5}, {11, 20}}));
    }

    public static int solution(int[][] routes) {
        int answer = 0;
        int camera = -30000;

        Arrays.sort(routes, Comparator.comparingInt(o -> o[1]));

        for (int[] route : routes) {
            if(camera >= route[0] && camera <= route[1]) continue;

            if(camera < route[1]){
                camera = route[1];
                answer++;
            }
        }
        
        return answer;
    }

}
