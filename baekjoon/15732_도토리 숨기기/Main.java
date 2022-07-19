import java.io.*;
import java.util.*;

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        st = new StringTokenizer(br.readLine());
        int n = Integer.parseInt(st.nextToken());
        int k = Integer.parseInt(st.nextToken());
        int d = Integer.parseInt(st.nextToken());

        int[][] rule = new int[k][3];
        for(int i = 0; i < k; i++){
            st = new StringTokenizer(br.readLine());
            rule[i][0] = Integer.parseInt(st.nextToken());
            rule[i][1] = Integer.parseInt(st.nextToken());
            rule[i][2] = Integer.parseInt(st.nextToken());
        }

        int l = 1;
        int r = n;

        while(l <= r){
            int mid = (l + r) / 2;
            int cnt = 0;

            for(int i = 0; i < k; i++){
                int start = rule[i][0];
                int end = rule[i][1];
                int c = rule[i][2];

                if(mid < start) continue;

                if(end <= mid) cnt += (end - start) / c + 1;
                else cnt += ((mid - start) / c + 1);

                if(cnt > d) break;
            }

            if(cnt >= d) r = mid-1;
            else l = mid+1;
        }

        System.out.println(l);
    }

}
