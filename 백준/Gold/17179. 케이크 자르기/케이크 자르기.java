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

        int[] cut = new int[m+2];
        for(int i = 1; i <= m; i++) {
            cut[i] = Integer.parseInt(br.readLine());
        }
        cut[m+1] = l;

        int[] people = new int[n];
        for(int i = 0; i < n; i++) {
            people[i] = Integer.parseInt(br.readLine());
        }

        StringBuilder sb = new StringBuilder();
        for (int person : people) {
            int ans = 0;
            int left = 0;
            int right = l;

            while(left <= right) {
                int mid = (left + right) / 2;
                int cnt = 0;
                int pre = 0;
                for(int i = 0; i < m+2; i++) {
                    if(cut[i] - pre > mid) {
                        pre = cut[i];
                        cnt++;
                    }
                }

                if(cnt > person) {
                    left = mid+1;
                }
                else {
                    right = mid-1;
                    ans = mid;
                }
            }

            sb.append(ans).append("\n");
        }

        System.out.println(sb.toString());
    }

}