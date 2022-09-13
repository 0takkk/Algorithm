import java.io.*;
import java.util.*;

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        int n = Integer.parseInt(br.readLine());
        int[] weight = new int[n];
        int[] count = new int[n];

        st = new StringTokenizer(br.readLine());
        for(int i = 0; i < n; i++){
            weight[i] = Integer.parseInt(st.nextToken());
        }

        int m = Integer.parseInt(br.readLine());
        int[] boxes = new int[m];

        st = new StringTokenizer(br.readLine());
        for(int i = 0; i < m; i++){
            boxes[i] = Integer.parseInt(st.nextToken());
        }

        Arrays.sort(weight);
        Arrays.sort(boxes);

        if(boxes[m-1] > weight[n-1]){
            System.out.println(-1);
            return;
        }

        int boxIdx = m-1;
        while(boxIdx >= 0){
            int box = boxes[boxIdx--];

            int countMinIdx = -1;
            int minCount = Integer.MAX_VALUE;
            for(int i = n-1; i >= 0; i--){
                if(weight[i] < box) break;

                if(minCount > count[i]){
                    minCount = count[i];
                    countMinIdx = i;
                }
            }

            count[countMinIdx]++;
        }

        int ans = -1;
        for (int c : count) {
            ans = Math.max(ans, c);
        }

        System.out.println(ans);
    }

}
