import java.io.*;
import java.util.*;

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        int n = Integer.parseInt(br.readLine());
        String str = br.readLine();

        int left = 0;
        int right = 0;
        int ans = 0;

        int[] alpa = new int[26];
        int cnt = 0;

        while(left <= right && right < str.length()){
            if(++alpa[str.charAt(right++) - 'a'] == 1) cnt++;

            while(cnt > n){
                if(--alpa[str.charAt(left) - 'a'] == 0) cnt--;
                left++;
            }

            ans = Math.max(ans, right-left);
        }

        System.out.println(ans);
    }

}
