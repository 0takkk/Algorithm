import java.io.*;
import java.util.*;

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        st = new StringTokenizer(br.readLine());
        long input = Long.parseLong(st.nextToken()) + 1;
        int k = Integer.parseInt(st.nextToken());

        String n = String.valueOf(input);
        int size = n.length();
        int[] arr = new int[size+1];

        for(int i = 0; i < size; i++){
            arr[size-i-1] = n.charAt(i)-'0';
        }

        if(check(arr, k)) return;

        for(int i = 0; i < size; i++){
            while(arr[i] != 5){
                arr[i]++;
                int idx = 0;

                while(arr[i+idx] == 10){
                    arr[i+idx+1]++;
                    arr[i+idx] = 0;
                    idx++;
                }

                if(check(arr, k)) return;
            }
        }

        StringBuilder sb = new StringBuilder();
        sb.append("5".repeat(k));
        System.out.println(sb.toString());
    }

    public static boolean check(int[] arr, int k){
        int cnt = 0;

        for(int i = 0; i < arr.length; i++){
            if(arr[i] == 5) cnt++;
        }

        if(cnt >= k){
            long num = 0;
            for(int i = 0; i < arr.length; i++){
                num += (long)Math.pow(10, i) * arr[i];
            }
            System.out.println(num);
            return true;
        }

        return false;
    }
    
}
