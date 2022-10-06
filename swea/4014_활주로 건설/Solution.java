import java.io.*;
import java.util.*;

public class Solution {

    public static int n, x;
    public static int[][] map;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        int t = Integer.parseInt(br.readLine());
        StringBuilder sb = new StringBuilder();

        for (int tc = 1; tc <= t; tc++) {
            st = new StringTokenizer(br.readLine());
            n = Integer.parseInt(st.nextToken());
            x = Integer.parseInt(st.nextToken());

            map = new int[n][n];
            for(int i = 0; i < n; i++){
                st = new StringTokenizer(br.readLine());
                for(int j = 0; j < n; j++){
                    map[i][j] = Integer.parseInt(st.nextToken());
                }
            }

            int ans = solve();
            rotate();  // map을 90도 회전시켜서 행과 열 바꿈
            ans += solve();

            sb.append("#").append(tc).append(" ").append(ans).append("\n");
        }

        System.out.println(sb.toString());
    }

    private static int solve() {
        int ans = 0;
        // 한 행마다 확인
        for(int i = 0; i < n; i++) {
            int sameHeight = 1;
            for(int j = 1; j < n; j++){
                if(Math.abs(map[i][j] - map[i][j-1]) >= 2) break;  // 차이가 2이상나면 불가능

                if(map[i][j] == map[i][j-1]) sameHeight++;  // 이전 열과 높이가 같으면 sameHeight 증가
                // 오르막
                else if(map[i][j] - map[i][j-1] == 1){
                    if(sameHeight < x) break;  // 오르막일 때 이전까지의 sameHeight가 x보다 작으면 불가능한 행.
                    sameHeight = 1;  // 가능한 경우라면 sameHeight를 1로 초기화해서 이번 열부터 다시 체크
                }
                // 내리막
                // 내리막이면 뒤에 열을 x개까지만 확인
                else if(map[i][j] - map[i][j-1] == -1){
                    int height = map[i][j];
                    sameHeight = 1;

                    int k = j+x;
                    while(++j < n && j < k && map[i][j] == height){  // j가 n을 벗어나지 않고, x개 까지만 같은 높이 개수를 카운트
                        sameHeight++;
                    }
                    j--;

                    if(sameHeight < x) break;  // x보다 적다면 불가능한 경우.

                    sameHeight = 0;  // 다음 열에 같은 높이가 나올 수 있으므로 0으로 초기화
                }

                if(j == n-1) ans++;  // 끝까지 도착했다면 ans 증가.
            }
        }
        return ans;
    }

    public static void rotate(){
        int[][] tmp = new int[n][n];

        for(int i = 0; i < n; i++){
            for(int j = 0; j < n; j++){
                tmp[i][j] = map[n-j-1][i];
            }
        }

        map = tmp;
    }

}
