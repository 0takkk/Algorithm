import java.io.*;
import java.util.*;

public class Solution {

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        int t = Integer.parseInt(br.readLine());
        StringBuilder sb = new StringBuilder();

        for (int tc = 1; tc <= t; tc++) {
            st = new StringTokenizer(br.readLine());
            int n = Integer.parseInt(st.nextToken());

            int INF = 987654321;
            int[][] dist = new int[n][n];  // 거리의 최솟값을 저장할 배열
            for(int i = 0; i < n; i++){
                Arrays.fill(dist[i], INF);  // INF로 초기화
            }

            for(int i = 0; i < n; i++){
                for(int j = 0; j < n; j++){
                    if(Integer.parseInt(st.nextToken()) == 1) dist[i][j] = 1;  // 연결되어 있으면 1
                    if(i == j) dist[i][j] = 0;  // 자기 자신은 0
                }
            }

            // 플로이드 와샬
            for(int k = 0; k < n; k++){
                for(int i = 0; i < n; i++){
                    for(int j = 0; j < n; j++){
                        dist[i][j] = Math.min(dist[i][j], dist[i][k] + dist[k][j]);
                    }
                }
            }

            int ans = INF;
            for(int i = 0; i < n; i++){
                int rowSum = 0;  // 한 정점에서 갈 수 있는 정점들의 거리의 합
                for(int j = 0; j < n; j++){
                    rowSum += dist[i][j];
                }

                ans = Math.min(ans, rowSum);  // 최솟값
            }

            sb.append("#").append(tc).append(" ").append(ans).append("\n");
        }

        System.out.println(sb.toString());
    }

}
