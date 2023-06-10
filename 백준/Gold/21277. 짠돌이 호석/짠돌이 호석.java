import java.io.*;
import java.util.*;

public class Main {

    public static int n1, m1, n2, m2;
    public static int[][] map1, map2;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        st = new StringTokenizer(br.readLine());
        n1 = Integer.parseInt(st.nextToken());
        m1 = Integer.parseInt(st.nextToken());

        map1 = new int[150][150];
        for(int i = 50; i < 50+n1; i++){
            String str = br.readLine();
            for(int j = 50; j < 50+m1; j++){
                map1[i][j] = str.charAt(j-50) - '0';
            }
        }

        st = new StringTokenizer(br.readLine());
        n2 = Integer.parseInt(st.nextToken());
        m2 = Integer.parseInt(st.nextToken());

        map2 = new int[n2][m2];
        for(int i = 0; i < n2; i++){
            String str = br.readLine();
            for(int j = 0; j < m2; j++){
                map2[i][j] = str.charAt(j) - '0';
            }
        }

        int ans = Integer.MAX_VALUE;
        ans = Math.min(ans, compare());
        rotate();
        ans = Math.min(ans, compare());
        rotate();
        ans = Math.min(ans, compare());
        rotate();
        ans = Math.min(ans, compare());

        System.out.println(ans);
    }

    public static int compare(){
        int minArea = Integer.MAX_VALUE;

        for(int i = 0; i < 150-n2; i++){
            for(int j = 0; j < 150-m2; j++){
                if(isOverlap(i, j)) continue;

                int minX = Math.min(i, 50);
                int maxX = Math.max(i + n2, n1 + 50);
                int minY = Math.min(j, 50);
                int maxY = Math.max(j + m2, m1 + 50);

                int area = (maxX - minX) * (maxY - minY);
                minArea = Math.min(minArea, area);
            }
        }

        return minArea;
    }

    public static boolean isOverlap(int x, int y){
        for(int i = 0; i < n2; i++){
            for(int j = 0; j < m2; j++){
                if(map1[i+x][j+y] == 1 && map2[i][j] == 1) return true;
            }
        }
        return false;
    }

    public static void rotate(){
        int[][] tmp = new int[m2][n2];
        for(int i = 0; i < n2; i++){
            for(int j = 0; j < m2; j++){
                tmp[j][n2-i-1] = map2[i][j];
            }
        }

        map2 = tmp;
        int tmp1 = n2;
        n2 = m2;
        m2 = tmp1;
    }
}
