import java.io.*;
import java.util.*;

public class Main {

    public static int n;
    public static long max;
    public static long[][] map;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        n = Integer.parseInt(br.readLine());
        map = new long[n][n];

        for(int i = 0; i < n; i++){
            st = new StringTokenizer(br.readLine());
            for(int j = 0; j < n; j++){
                map[i][j] = Long.parseLong(st.nextToken());
            }
        }

        bfs();
        System.out.println(max);
    }

    public static void bfs(){
        Queue<long[][]> q = new LinkedList<>();
        q.offer(map);

        int count = 0;

        while(!q.isEmpty()){
            int size = q.size();

            if(count > 5) return;

            for(int s = 0; s < size; s++) {
                long[][] tmp = q.poll();

                for(int i = 0; i < n ; i++){
                    for(int j = 0; j < n; j++){
                        max = Math.max(max, tmp[i][j]);
                    }
                }

                for (int i = 0; i < 4; i++) {
                    long[][] copy = copy(tmp);
                    move(copy, i);
                    q.offer(copy);
                }
            }

            count++;
        }
    }

    public static void move(long[][] copy, int dir){
        boolean[][] visited = new boolean[n][n];

        if(dir == 0){
            for(int i = 0; i < n; i++){
                for(int j = n-2; j >= 0; j--){
                    int k = j;
                    while(copy[i][k] != 0 && k < n-1){
                        if(copy[i][k+1] == 0){
                            copy[i][k+1] = copy[i][k];
                            copy[i][k] = 0;
                        }
                        else if(copy[i][k] == copy[i][k+1] && !visited[i][k] && !visited[i][k+1]){
                            copy[i][k] = 0;
                            copy[i][k+1] *= 2;
                            visited[i][k+1] = true;
                        }
                        k++;
                    }
                }
            }
        }
        else if(dir == 1){
            for(int i = 0; i < n; i++){
                for(int j = 1; j < n; j++){
                    int k = j;
                    while(copy[i][k] != 0 && k > 0){
                        if(copy[i][k-1] == 0){
                            copy[i][k-1] = copy[i][k];
                            copy[i][k] = 0;
                        }
                        else if(copy[i][k] == copy[i][k-1] && !visited[i][k-1] && !visited[i][k]){
                            copy[i][k] = 0;
                            copy[i][k-1] *= 2;
                            visited[i][k-1] = true;
                        }
                        k--;
                    }
                }
            }
        }
        else if(dir == 2){
            for(int j = 0; j < n; j++){
                for(int i = n-2; i >= 0; i--){
                    int k = i;
                    while(copy[k][j] != 0 && k < n-1){
                        if(copy[k+1][j] == 0){
                            copy[k+1][j] = copy[k][j];
                            copy[k][j] = 0;
                        }
                        else if(copy[k][j] == copy[k+1][j] && !visited[k][j] && !visited[k+1][j]){
                            copy[k][j] = 0;
                            copy[k+1][j] *= 2;
                            visited[k+1][j] = true;
                        }
                        k++;
                    }
                }
            }
        }
        else if(dir == 3){
            for(int j = 0; j < n; j++){
                for(int i = 1; i < n; i++){
                    int k = i;
                    while(copy[k][j] != 0 && k > 0){
                        if(copy[k-1][j] == 0){
                            copy[k-1][j] = copy[k][j];
                            copy[k][j] = 0;
                        }
                        else if(copy[k][j] == copy[k-1][j] && !visited[k-1][j] && !visited[k][j]){
                            copy[k][j] = 0;
                            copy[k-1][j] *= 2;
                            visited[k-1][j] = true;
                        }
                        k--;
                    }
                }
            }
        }
    }

    public static long[][] copy(long[][] tmp){
        long[][] copyMap = new long[n][n];

        for(int i = 0; i < n; i++){
            for(int j = 0; j < n; j++){
                copyMap[i][j] = tmp[i][j];
            }
        }

        return copyMap;
    }

}
