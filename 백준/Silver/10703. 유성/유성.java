import java.io.*;
import java.util.*;

public class Main {

    public static class Pos {
        int x, y;

        public Pos(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        st = new StringTokenizer(br.readLine());
        int n = Integer.parseInt(st.nextToken());
        int m = Integer.parseInt(st.nextToken());

        char[][] map = new char[n][m];

        Queue<Pos> meteors = new ArrayDeque<>();
        Queue<Pos> grands = new ArrayDeque<>();

        for(int i = 0; i < n; i++) {
            String str = br.readLine();
            for(int j = 0; j < m; j++) {
                map[i][j] = str.charAt(j);
                if(map[i][j] == 'X') meteors.offer(new Pos(i, j));
                else if(map[i][j] == '#') grands.offer(new Pos(i, j));
            }
        }

        int down = Integer.MAX_VALUE;
        for(int j = 0; j < m; j++) {
            int meteor = -1;
            int grand = n-1;
            for(int i = 0; i < n; i++) {
                if(map[i][j] == 'X') {
                    meteor = i;
                }
                if(map[i][j] == '#') {
                    grand = Math.min(grand, i);
                }
            }

            if(meteor != -1) {
                down = Math.min(down, grand-meteor-1);
            }
        }

        char[][] result = new char[n][m];
        for(int i = 0; i < n; i++) {
            Arrays.fill(result[i], '.');
        }

        while(!meteors.isEmpty()) {
            Pos meteor = meteors.poll();
            result[meteor.x+down][meteor.y] = 'X';
        }

        while(!grands.isEmpty()) {
            Pos grand = grands.poll();
            result[grand.x][grand.y] = '#';
        }

        StringBuilder sb = new StringBuilder();
        for(int i = 0; i < n; i++) {
            for(int j = 0; j < m; j++) {
                sb.append(result[i][j]);
            }
            sb.append("\n");
        }

        System.out.println(sb.toString());
    }

}