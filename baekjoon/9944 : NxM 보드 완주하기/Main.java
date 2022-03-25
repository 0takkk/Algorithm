package com.company;
import java.io.*;
import java.util.*;

public class Main {

    public static int n, m, cell, ans;
    public static char[][] map;
    public static boolean[][] visited;

    public static int[] dx = {0, 0, 1, -1};
    public static int[] dy = {1, -1, 0, 0};

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        String str = "";
        int idx = 1;
        StringBuilder sb = new StringBuilder();

        while((str = br.readLine()) != null && !str.isEmpty()){
            st = new StringTokenizer(str);

            n = Integer.parseInt(st.nextToken());
            m = Integer.parseInt(st.nextToken());
            cell = 0;
            ans = Integer.MAX_VALUE;

            map = new char[n][m];

            for(int i = 0; i < n; i++){
                String tmp = br.readLine();
                for(int j = 0; j < m; j++){
                    map[i][j] = tmp.charAt(j);
                    if(map[i][j] == '.') cell++;
                }
            }

            for(int i = 0; i < n; i++){
                for(int j = 0; j < m; j++){
                    visited = new boolean[n][m];

                    if(map[i][j] == '.') {
                        visited[i][j] = true;
                        dfs(i, j, 0, 1);
                        visited[i][j] = false;
                    }
                }
            }

            if(ans == Integer.MAX_VALUE) sb.append("Case " + idx++ + ": " + -1 + "\n");
            else sb.append("Case " + idx++ + ": " + ans + "\n");
        }

        System.out.println(sb.toString());
    }

    public static void dfs(int x, int y, int cnt, int move){
        if(move == cell){
            ans = Math.min(ans, cnt);
        }

        for(int d = 0; d < 4; d++){
            int count = 0;
            int r = x;
            int c = y;
            int nr, nc;

            while(true){
                nr = r + dx[d];
                nc = c + dy[d];

                if(nr < 0 || nr >= n || nc < 0 || nc >= m) break;
                if(map[nr][nc] == '*' || visited[nr][nc]) break;

                visited[nr][nc] = true;
                count++;
                r = nr;
                c = nc;
            }

            if(r == x && c == y) continue;

            dfs(r, c, cnt+1, move + count);

            for(int i = 0; i < count; i++){
                visited[r][c] = false;
                r = r - dx[d];
                c = c - dy[d];
            }
        }

    }

}
