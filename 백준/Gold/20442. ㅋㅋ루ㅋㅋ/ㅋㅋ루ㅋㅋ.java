import java.io.*;
import java.util.*;

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        char[] ch = br.readLine().toCharArray();
        int len = ch.length;

        int[] rCnt = new int[len];
        int cnt = 0;
        for(int i = 0; i < len; i++){
            if(ch[i] == 'R') cnt++;
            rCnt[i] = cnt;
        }

        int left = 0;
        int right = len-1;
        int ans = rCnt[len-1];

        int lk = ch[left] == 'K' ? 1 : 0;
        int rk = ch[right] == 'K' ? 1 : 0;

        while(left < right){
            if(lk > 0 && rk > 0){
                int r = rCnt[right] - (left == 0 ? 0 : rCnt[left-1]);
                if(r != 0) ans = Math.max(ans, r + Math.min(lk, rk) * 2);
            }

            if(lk > rk){
                right--;
                if(ch[right] == 'K') rk++;
            }
            else{
                left++;
                if(ch[left] == 'K') lk++;
            }
        }

        System.out.println(ans);
    }

}
