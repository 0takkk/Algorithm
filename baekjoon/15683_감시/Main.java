import java.io.*;
import java.util.*;

public class Main {

    public static class CCTV {  // cctv의 번호와 좌표 객체
        int x, y, num;

        public CCTV(int x, int y, int num) {
            this.x = x;
            this.y = y;
            this.num = num;
        }
    }

    public static int n, m, ans = Integer.MAX_VALUE;
    public static int[][] map, copy;

    public static ArrayList<CCTV> list;  // cctv 저장

    public static int[] dx = {0, -1 ,0, 1};
    public static int[] dy = {-1, 0, 1, 0};  // 좌, 상, 우, 하

    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());

        list = new ArrayList<>();
        map = new int[n][m];
        copy = new int[n][m];

        for(int i = 0; i < n; i++){
            st = new StringTokenizer(br.readLine());
            for(int j = 0; j < m; j++){
                map[i][j] = Integer.parseInt(st.nextToken());
                if(map[i][j] != 0 && map[i][j] != 6) {
                    list.add(new CCTV(i, j, map[i][j]));  // cctv이면 list에 저장
                }
                if(map[i][j] == 6) copy[i][j] = 6;  // 벽만 copy 배열에 저장해둠
            }
        }

        rec(0);  // 재귀를 돌면서 cctv가 볼 수 있는 방향 설정
        System.out.println(ans);
    }

    public static void rec(int idx){
        if(idx == list.size()){  // 모든 cctv를 체크했을 때
            int cnt = 0;
            for(int i = 0; i < n; i++){
                for(int j = 0; j < m; j++){
                    if(copy[i][j] == 0) cnt++;
                }
            }
            ans = Math.min(ans, cnt);  // 최소 값 
            return;
        }

        CCTV p = list.get(idx);
        int x = p.x;
        int y = p.y;
        int num = p.num;

        int count = 4;
        if(num == 2) count = 2;
        else if(num == 5) count = 1;

        for(int j = 0; j < count; j++){   // cctv 번호에 해당하는 볼 수 있는 방향만큼 for문을 돌면서 재귀
            setCCTV(x, y, num, j, 1);  // 1로 설정하고
            rec(idx+1);  // 재귀를 돈 다음
            setCCTV(x, y, num, j, -1);  // -1해서 설정한 값 초기화
        }
    }

    public static void setCCTV(int x, int y, int num, int dir, int state){
        copy[x][y] += state;
        if(num == 1){
            cctvArea(x, y, dir, state);
        }
        else if(num == 2){
            cctvArea(x, y, dir, state);
            cctvArea(x, y, dir+2, state);
        }
        else if(num == 3){
            cctvArea(x, y, dir, state);
            cctvArea(x, y, dir+1, state);
        }
        else if(num == 4){
            cctvArea(x, y, dir, state);
            cctvArea(x, y, dir+1, state);
            cctvArea(x, y, dir+2, state);
        }
        else if(num == 5){
            cctvArea(x, y, 0, state);
            cctvArea(x, y, 1, state);
            cctvArea(x, y, 2, state);
            cctvArea(x, y, 3, state);
        }
    }

    private static void cctvArea(int x, int y, int dir, int state) {
        dir %= 4;

        int nx = x + dx[dir];
        int ny = y + dy[dir];
        while(isRange(nx, ny) && map[nx][ny] != 6){
            copy[nx][ny] += state;
            nx += dx[dir];
            ny += dy[dir];
        }
    }

    public static boolean isRange(int x, int y){
        return x >= 0 && x < n && y >= 0 && y < m;
    }
}
