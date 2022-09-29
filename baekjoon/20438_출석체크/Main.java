import java.io.*;
import java.util.*;

public class Main {

    public static int n;
    public static int[] student;
    public static HashSet<Integer> sleep;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        int k = Integer.parseInt(st.nextToken());
        int q = Integer.parseInt(st.nextToken());
        int m = Integer.parseInt(st.nextToken());

        student = new int[n+3];

        sleep = new HashSet<>();
        st = new StringTokenizer(br.readLine());
        for(int i = 0; i < k; i++){
            sleep.add(Integer.parseInt(st.nextToken()));
        }

        int[] code = new int[q];
        st = new StringTokenizer(br.readLine());
        for(int i = 0; i < q; i++){
            code[i] = Integer.parseInt(st.nextToken());
        }

        for (int c : code) {
            forward(c);
        }

        int[] sum = new int[n+3];
        sum[3] = student[3] == 0 ? 1 : 0;
        for(int i = 4; i < n+3; i++){
            sum[i] = student[i] == 0 ? sum[i-1] + 1 : sum[i-1];
        }

        StringBuilder sb = new StringBuilder();
        while(m-->0){
            st = new StringTokenizer(br.readLine());
            int s = Integer.parseInt(st.nextToken());
            int e = Integer.parseInt(st.nextToken());

            int cnt = sum[e] - sum[s];
            if(student[s] == 0) cnt++;

            sb.append(cnt).append("\n");
        }

        System.out.println(sb.toString());
    }

    public static void forward(int num){
        if(!sleep.contains(num) && student[num] == 0){
            student[num] = 1;
            for(int i = num; i < n+3; i+=num){
                forward(i);
            }
        }
    }

}
