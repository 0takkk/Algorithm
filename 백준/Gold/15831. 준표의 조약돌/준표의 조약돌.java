import java.io.*;
import java.util.*;

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        st = new StringTokenizer(br.readLine());
        int n = Integer.parseInt(st.nextToken());
        int b = Integer.parseInt(st.nextToken());
        int w = Integer.parseInt(st.nextToken());

        int[] black = new int[n+1];
        int[] white = new int[n+1];
        int blackCnt = 0;
        int whiteCnt = 0;

        String str = br.readLine();
        for(int i = 0; i < n; i++){
            char color = str.charAt(i);

            if(color == 'B') blackCnt++;
            else whiteCnt++;

            black[i+1] = blackCnt;
            white[i+1] = whiteCnt;
        }

        int left = 1;
        int right = 1;
        int ans = 0;

        while(left <= n && right <= n){
            int hasBlack = black[right] - black[left-1];
            int hasWhite = white[right] - white[left-1];

            if(hasBlack <= b && hasWhite >= w){
                ans = Math.max(ans, right-left+1);
            }

            if(left == right){
                right++;
            }
            else if(hasBlack > b){
                left++;
            }
            else{
                right++;
            }
        }

        System.out.println(ans);
    }

}
