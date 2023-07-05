import java.io.*;
import java.util.*;

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        int n = Integer.parseInt(br.readLine());
        int[] height = new int[n];

        st = new StringTokenizer(br.readLine());
        for(int i = 0; i < n; i++){
            height[i] = Integer.parseInt(st.nextToken());
        }

        int ans = 0;
        int[] arrow = new int[1000001];

        for (int h : height) {
            if(arrow[h] > 0){
                arrow[h]--;
                arrow[h-1]++;
            }
            else{
                arrow[h-1]++;
                ans++;
            }
        }

        System.out.println(ans);
    }

}
