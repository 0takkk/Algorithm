import java.io.*;
import java.util.*;

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        char[] ch = br.readLine().toCharArray();
        int len = ch.length;

        int[] kCnt = new int[len];
        int cnt = 0;
        for(int i = 0; i < len; i++){
            if(ch[i] == 'K') cnt++;
            kCnt[i] = cnt;
        }

        int ans = len - cnt;

        int left = 0;
        int right = len-1;

        while(left <= right){
            int lk = 0;
            if(left != 0) lk = kCnt[left-1];

            int kNum = Math.min(lk, kCnt[len-1] - kCnt[right]);
            int rNum = right - left + 1 - (kCnt[right] - lk);

            if(rNum != 0) ans = Math.max(ans, kNum * 2 + rNum);

            if(left > 0 && kCnt[left-1] >= kCnt[len-1] - kCnt[right]) right--;
            else left++;
        }

        System.out.println(ans);
    }

}
