import java.io.*;
import java.util.*;

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        st = new StringTokenizer(br.readLine());
        int n = Integer.parseInt(st.nextToken());
        int m = Integer.parseInt(st.nextToken());
        int l = Integer.parseInt(st.nextToken());

        int[] road = new int[n+2];
        st = new StringTokenizer(br.readLine());
        for(int i = 0; i < n; i++) {
            road[i] = Integer.parseInt(st.nextToken());
        }
        road[n+1] = l;

        Arrays.sort(road);

        int left = 1;
        int right = l-1;

        while(left <= right) {
            int mid = (left + right) / 2;
            int cnt = 0;

            for(int i = 0; i < road.length-1; i++) {
                cnt += (road[i+1] - road[i]-1) / mid;
            }

            if(cnt > m) {
                left = mid+1;
            }
            else {
                right = mid-1;
            }
        }

        System.out.println(left);
    }

}