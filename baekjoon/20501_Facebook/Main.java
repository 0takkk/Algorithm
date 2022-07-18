import java.io.*;
import java.util.*;

public class Main {

    public static int n, size;
    public static int[][] num;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        n = Integer.parseInt(br.readLine());
        size = n/30 + 1;
        num = new int[n+1][size];

        for(int i = 1; i <= n; i++){
            input(br.readLine().toCharArray(), i);
        }

        int q = Integer.parseInt(br.readLine());
        StringBuilder sb = new StringBuilder();

        while(q-->0){
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());


            sb.append(calc(a, b)+ "\n");
        }

        System.out.println(sb.toString());
    }

    public static void input(char[] ch, int i){
        int sum = 0;
        int key = 1;
        int idx = size-1;

        for(int j = ch.length-1; j >= 0; j--){
            if(ch[j] == '1'){
                sum += key;
            }

            if(key == 1 << 31){
                num[i][idx--] = sum;
                key = 1;
                sum = 0;
            }

            key = key << 1;
        }
        num[i][idx--] = sum;
    }

    public static int calc(int a, int b){
        int cnt = 0;

        for(int i = size-1; i >= 0; i--){
            int tmp = num[a][i] & num[b][i];

            while(tmp != 0){
                tmp &= tmp-1;
                cnt++;
            }
        }

        return cnt;
    }

}
