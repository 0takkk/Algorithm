import java.io.*;
import java.util.*;

public class Main {

    public static int r, c;
    public static long ans, cnt;
    public static int[] dx = {0, 0, 1, 1};
    public static int[] dy = {0, 1, 0, 1};

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        st = new StringTokenizer(br.readLine());
        int n = Integer.parseInt(st.nextToken());
        int size = (int)Math.pow(2, n);
        r = Integer.parseInt(st.nextToken());
        c = Integer.parseInt(st.nextToken());

        partition(0, 0, size);
        System.out.println(ans);
    }

    public static void partition(int x, int y, int size){
        if(size <= 2){
            for(int i = 0; i < 4; i++){
                int nx = x + dx[i];
                int ny = y + dy[i];

                if(nx == r && ny == c) ans = cnt;
                cnt++;
            }
            return;
        }

        int nSize = size/2;
        int startX = x;
        int startY = y;

        if(r >= startX + nSize){
            cnt += (long)size * size / 2;
            startX += nSize;
        }
        if(c >= startY + nSize){
            cnt += (long)size * size / 4;
            startY += nSize;
        }

        partition(startX, startY, nSize);
    }

}
