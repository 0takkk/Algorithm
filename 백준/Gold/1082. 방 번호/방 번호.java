import java.io.*;
import java.util.*;

public class Main {

    public static int n;
    public static int[] price;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        n = Integer.parseInt(br.readLine());
        price = new int[n];

        st = new StringTokenizer(br.readLine());
        for(int i = 0; i < n; i++) {
            price[i] = Integer.parseInt(st.nextToken());
        }

        int m = Integer.parseInt(br.readLine());

        int[] ans = new int[100];
        int cost = 0;
        int idx = 0;

        int minIdx = findMin(1);
        if(price[minIdx] <= m) {
            ans[idx++] = minIdx;
            cost += price[minIdx];
            minIdx = findMin(0);
        }
        else {
            System.out.println(0);
            return;
        }

        while(cost + price[minIdx] <= m) {
            ans[idx++] = minIdx;
            cost += price[minIdx];
        }

        for(int i = 0; i < idx; i++) {
            for(int j = n-1; j >= 0; j--) {
                if(cost-price[ans[i]] + price[j] <= m) {
                    cost = cost - price[ans[i]] + price[j];
                    ans[i] = j;
                    break;
                }
            }
        }

        StringBuilder sb = new StringBuilder();
        for(int i = 0; i < idx; i++) {
            sb.append(ans[i]);
        }

        System.out.println(sb.toString());
    }

    public static int findMin(int start) {
        int retIdx = 0;
        int min = 100;

        for(int i = start; i < n; i++) {
            if(min > price[i]) {
                min = price[i];
                retIdx = i;
            }
        }

        return retIdx;
    }

}