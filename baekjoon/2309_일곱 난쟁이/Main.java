import java.io.*;
import java.util.*;

public class Main {

    public static int[] arr, num;
    public static boolean flag;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        arr = new int[9];

        for(int i = 0; i < 9; i++){
            arr[i] = Integer.parseInt(br.readLine());
        }

        Arrays.sort(arr);
        num = new int[7];

        backtracking(0, 0);

        for (int i : num) {
            System.out.println(i);
        }
    }

    public static void backtracking(int idx, int cnt){
        if(cnt == 7){
            int sum = 0;
            for(int i = 0; i < 7; i++){
                sum += num[i];
            }

            if(sum == 100){
                flag = true;
            }

            return;
        }

        for(int i = idx; i < 9; i++){
            num[cnt] = arr[i];
            backtracking(i+1, cnt+1);
            if(flag) return;
        }
    }

}
