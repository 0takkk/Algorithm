import java.io.*;
import java.util.*;

public class Main {


    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        st = new StringTokenizer(br.readLine());
        long input = Long.parseLong(st.nextToken()) + 1;
        String n = String.valueOf(input);
        int k = Integer.parseInt(st.nextToken());


        int size = n.length();
        int[] arr = new int[size+1];

        for(int i = 0; i < size; i++){
            int num = n.charAt(i) - '0';
            arr[size-i-1] = num;
        }

        int nCnt = counting(size, arr);
        if(nCnt >= k) {
            printNum(size, arr);
            return;
        }

        for(int i = 0; i < size; i++){
            while(arr[i] != 5) {
                arr[i]++;
                int idx = 0;
                while (arr[i + idx] == 10) {
                    arr[i + idx+1]++;
                    arr[i+idx] = 0;
                    idx++;
                }

                nCnt = counting(size, arr);
                if(nCnt >= k) {
                    printNum(size, arr);
                    return;
                }
            }

        }

        StringBuilder sb = new StringBuilder();
        sb.append("5".repeat(k));
        System.out.println(sb.toString());
    }

    private static void printNum(int size, int[] arr) {
        long nNum = 0;
        for(int j = 0; j <= size; j++){
            nNum += (long)Math.pow(10, j) * arr[j];
        }

        System.out.println(nNum);
    }

    private static int counting(int size, int[] arr) {
        int nCnt = 0;
        for(int j = 0; j <= size; j++){
            if(arr[j] == 5) nCnt++;
        }
        return nCnt;
    }

}
