import java.io.*;
import java.util.*;

public class Main {

    public static int n, m;
    public static int[][] arr;

    public static int[] dx = {0, 0, 1, -1};
    public static int[] dy = {1, -1, 0, 0};

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        int t = Integer.parseInt(st.nextToken());

        arr = new int[n+1][m];

        for(int i = 1; i <= n; i++){
            st = new StringTokenizer(br.readLine());
            for(int j = 0; j < m; j++){
                arr[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        while(t-->0){
            st = new StringTokenizer(br.readLine());
            int x = Integer.parseInt(st.nextToken());
            int d = Integer.parseInt(st.nextToken());
            int k = Integer.parseInt(st.nextToken());

            rotate(x, d, k);
            remove();
        }

        int sum = 0;
        for(int i = 1; i <= n; i++){
            for(int j = 0; j < m; j++){
                sum += arr[i][j];
            }
        }

        System.out.println(sum);
    }

    public static void rotate(int x, int d, int k){
        int[][] tmp = new int[n+1][m];

        for(int i = 1; i <= n; i++){
            if (i % x != 0) continue;

            for(int j = 0; j < m; j++){
                tmp[i][j] = arr[i][j];
            }

            for(int j = 0; j < m; j++){
                if(d == 0){
                    arr[i][(j + k) % m] = tmp[i][j];
                }
                else{
                    arr[i][(j - k + m) % m] = tmp[i][j];
                }
            }
        }
    }

    public static void remove(){
        boolean[][] check = new boolean[n+1][m];
        boolean flag = false;

        int sum = 0;
        int cnt = 0;

        for(int i = 1; i <= n; i++){
            for(int j = 0; j < m; j++){
                if(arr[i][j] == 0) continue;
                sum += arr[i][j];
                cnt++;

                for(int k = 0; k < 4; k++){
                    int x = i + dx[k];
                    int y = j + dy[k];

                    if(x < 1 || x > n) continue;

                    if(y < 0) y = m-1;
                    if(y >= m) y = 0;

                    if(arr[i][j] == arr[x][y]){
                        check[i][j] = true;
                        check[x][y] = true;
                        flag = true;
                    }
                }
            }
        }

        if(flag) {
            for (int i = 1; i <= n; i++) {
                for (int j = 0; j < m; j++) {
                    if (check[i][j]) arr[i][j] = 0;
                }
            }
        }
        else{
            double avg = (double)sum / cnt;

            for (int i = 1; i <= n; i++) {
                for (int j = 0; j < m; j++) {
                    if(arr[i][j] == 0) continue;

                    if((double)arr[i][j] > avg) arr[i][j]--;
                    else if((double)arr[i][j] < avg) arr[i][j]++;
                }
            }
        }
    }

}
