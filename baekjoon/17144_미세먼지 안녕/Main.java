import java.io.*;
import java.util.*;

public class Main {

    public static int r, c, upX, downX;  // 공기청정기 위 좌표, 아래 좌표
    public static int[][] map;

    public static int[] dx = {0, -1, 0, 1};
    public static int[] dy = {1, 0, -1, 0};

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        st = new StringTokenizer(br.readLine());
        r = Integer.parseInt(st.nextToken());
        c = Integer.parseInt(st.nextToken());
        int t = Integer.parseInt(st.nextToken());
        map = new int[r+1][c+1];

        for(int i = 1; i <= r; i++){
            st = new StringTokenizer(br.readLine());
            for(int j = 1; j <= c; j++){
                map[i][j] = Integer.parseInt(st.nextToken());
                if(map[i][j] == -1){
                    if(upX == 0) upX = i;  // 공기청정기 좌표 저장
                    else downX = i;
                }
            }
        }

        while(t-->0){
            spread();  // 미세먼지 확산
            cleaningUp(upX-1, 1);  // 공기청정위 위 청소
            cleaningDown(downX+1, 1);  // 공기청정기 아래 청소
        }

        int ans = counting();  // 미세먼지 수 계산
        System.out.println(ans);
    }

    private static int counting() {
        int ans = 0;
        for(int i = 1; i <= r; i++){
            for(int j = 1; j <= c; j++){
                if(map[i][j] != -1){
                    ans += map[i][j];
                }
            }
        }
        return ans;
    }

    public static void spread(){  // 미세먼지 확산
        int[][] tmp = new int[r+1][c+1];  // 확산되는 미세먼지 양 저장할 배열

        for(int i = 1; i <= r; i++){
            for(int j = 1; j <= c; j++){
                if(map[i][j] >= 5){  // 5보다 커야지 확산이 된다.
                    int cnt = 0;  // 확산된 방향 개수

                    for(int d = 0; d < 4; d++){
                        int nx = i + dx[d];
                        int ny = j + dy[d];

                        if(!isRange(nx, ny) || map[nx][ny] == -1) continue;

                        tmp[nx][ny] += map[i][j] / 5;  // 확산되는 곳에 확산량 누적
                        cnt++;  // 개수+1
                    }

                    tmp[i][j] -= (map[i][j] / 5) * cnt;  // 기존 좌표에서 확산된 미세먼지량 감소
                }
            }
        }

        for(int i = 1; i <= r; i++){
            for(int j = 1; j <= c; j++){
                map[i][j] += tmp[i][j];
            }
        }
    }

    public static void cleaningUp(int x, int y){
        int dir = 1;

        while(!(x == upX && y == 2)){
            int nx = x + dx[dir];
            int ny = y + dy[dir];

            if(!isRange(nx, ny) || nx > upX) dir = (dir-1+4) % 4;  // 배열 범위를 벗어나거나 upX보다 밑으로 가면 방향 전환
            else{
                map[x][y] = map[nx][ny];  // 미세먼지 이동
                x = nx;
                y = ny;
            }
        }
        map[upX][2] = 0;
    }

    public static void cleaningDown(int x, int y){
        int dir = 3;

        while(!(x == downX && y == 2)){
            int nx = x + dx[dir];
            int ny = y + dy[dir];

            if(!isRange(nx, ny) || nx < downX) dir = (dir+1) % 4;  // 배열 범위를 벗어나거나 downX보다 위로 올라가면 방향 전환
            else{
                map[x][y] = map[nx][ny];  // 미세먼지 이동
                x = nx;
                y = ny;
            }
        }
        map[downX][2] = 0;
    }

    public static boolean isRange(int x, int y){
        return x > 0 && x <= r && y > 0 && y <= c;
    }

}
