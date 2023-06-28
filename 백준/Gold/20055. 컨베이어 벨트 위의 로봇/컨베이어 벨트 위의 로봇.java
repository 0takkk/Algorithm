import java.io.*;
import java.util.*;

public class Main {

    public static int n, k;
    public static int[] weight;
    public static boolean[] robot;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        k = Integer.parseInt(st.nextToken());
        weight = new int[2*n];
        robot = new boolean[n];

        st = new StringTokenizer(br.readLine());
        for(int i = 0; i < 2*n; i++){
            weight[i] = Integer.parseInt(st.nextToken());
        }

        System.out.println(simulation());
    }

    public static int simulation(){
        int round = 0;

        while(!isFinish()){
            moveBelt();
            moveRobot();
            raiseRobot();
            round++;
        }

        return round;
    }

    public static boolean isFinish(){
        int cnt = 0;

        for (int i : weight) {
            if(i == 0) cnt++;
        }

        return cnt >= k;
    }

    public static void moveBelt(){
        int tmp = weight[weight.length-1];
        for(int i = weight.length-1; i > 0; i--){
            weight[i] = weight[i-1];
        }
        weight[0] = tmp;

        for(int i = robot.length-1; i > 0; i--){
            robot[i] = robot[i-1];
        }
        robot[0] = false;
    }

    public static void moveRobot(){
        robot[n-1] = false;

        for(int i = n-1; i > 0; i--){
            if(robot[i-1] && !robot[i] && weight[i] > 0){
                robot[i-1] = false;
                robot[i] = true;
                weight[i]--;
            }
        }
    }

    public static void raiseRobot(){
        if(weight[0] > 0){
            weight[0]--;
            robot[0] = true;
        }
    }


}
