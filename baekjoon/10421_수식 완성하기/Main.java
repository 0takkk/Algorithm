import java.io.*;
import java.util.*;

public class Main {

    public static int n, k, result;
    public static int[] S, nums;
    public static HashSet<Integer> set;


    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        n = Integer.parseInt(br.readLine());
        st = new StringTokenizer(br.readLine());

        S = new int[n];
        for(int i = 0; i < n; i++){
            S[i] = Integer.parseInt(st.nextToken());
        }

        k = Integer.parseInt(br.readLine());
        st = new StringTokenizer(br.readLine());
        nums = new int[k];
        for(int i = 0; i < k; i++){
            nums[i] = Integer.parseInt(st.nextToken());
        }

        set = new HashSet<>();
        for (int num : nums) {
            set.add(num);
        }

        choose1(0, 0);
        System.out.println(result);
    }

    public static void choose1(int cnt, int val){
        if(cnt == S[0]){
            choose2(0, 0, val);
            return;
        }

        for(int i = 0; i < k; i++){
            choose1(cnt+1, val * 10 + nums[i]);
        }
    }

    public static void choose2(int cnt, int val, int num){
        if(cnt == S[1]){
            int mul = val * num;
            if(checkLen(mul, S[n-1]) && checkNum(mul)){
                result++;
            }
            return;
        }

        for(int i = 0; i < k; i++){
            int mul = num * nums[i];
            if(checkLen(mul, S[cnt+2]) && checkNum(mul)){
                choose2(cnt+1, val * 10 + nums[i], num);
            }
        }
    }

    public static boolean checkLen(int val, int len){
        if(String.valueOf(val).length() == len) return true;
        return false;
    }

    public static boolean checkNum(int val){
        while(val > 0){
            if(!set.contains(val % 10)) return false;
            val /= 10;
        }
        return true;
    }
}
