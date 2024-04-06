import java.io.*;
import java.util.*;

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        int n = Integer.parseInt(br.readLine());
        int[][] people = new int[n][2];

        for(int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());
            people[i][0] = Integer.parseInt(st.nextToken());;
            people[i][1] = Integer.parseInt(st.nextToken());
        }

        int[] rank = new int[n];

        for(int i = 0; i < n; i++) {
            for(int j = 0; j < n; j++) {
                if(i == j) continue;

                if(people[i][0] < people[j][0] && people[i][1] < people[j][1]) {
                    rank[i]++;
                }
            }
        }

        StringBuilder sb = new StringBuilder();
        for(int i = 0; i < n; i++) {
            sb.append(rank[i]+1).append(" ");
        }

        System.out.println(sb.toString());
    }

}