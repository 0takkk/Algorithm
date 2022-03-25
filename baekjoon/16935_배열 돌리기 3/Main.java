package com.company;
import java.io.*;
import java.util.*;

public class Main {

    public static int n, m;
    public static int[][] map;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        int t = Integer.parseInt(st.nextToken());

        map = new int[n][m];
        for(int i = 0; i < n; i++){
            st = new StringTokenizer(br.readLine());
            for(int j = 0; j < m; j++){
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        st = new StringTokenizer(br.readLine());
        while(t-->0){
            int op = Integer.parseInt(st.nextToken());

            operation(op);
        }

        StringBuilder sb = new StringBuilder();
        for(int i = 0; i < map.length; i++){
            for(int j = 0; j < map[0].length; j++){
                sb.append(map[i][j] + " ");
            }
            sb.append("\n");
        }

        System.out.println(sb.toString());
    }

    public static void operation(int op){
        if(op == 1){
            int[][] transMap = new int[n][m];

            for(int i = 0; i < n; i++){
                for(int j = 0; j < m; j++){
                    transMap[i][j] = map[n-1-i][j];
                }
            }

            map = transMap;
        }
        else if(op ==  2){
            int[][] transMap = new int[n][m];

            for(int i = 0; i < n; i++){
                for(int j = 0; j < m; j++){
                    transMap[i][j] = map[i][m-j-1];
                }
            }

            map = transMap;
        }
        else if(op == 3){
            int[][] transMap = new int[m][n];

            for(int i = 0; i < n; i++){
                for(int j = 0; j < m; j++){
                    transMap[j][n-i-1] = map[i][j];
                }
            }

            int tmp = n;
            n = m;
            m = tmp;

            map = transMap;
        }
        else if(op == 4){
            int[][] transMap = new int[m][n];

            for(int i = 0; i < n; i++){
                for(int j = 0; j < m; j++){
                    transMap[m-j-1][i] = map[i][j];
                }
            }

            int tmp = n;
            n = m;
            m = tmp;

            map = transMap;
        }
        else{
            int hn = n/2;
            int hm = m/2;

            int[][] a = new int[hn][hm];
            int[][] b = new int[hn][hm];
            int[][] c = new int[hn][hm];
            int[][] d = new int[hn][hm];

            for(int i = 0; i < n; i++){
                for(int j = 0; j < m; j++){
                    if(i < hn && j < hm) a[i][j] = map[i][j];
                    else if(i < hn && j >= hm) b[i][j-hm] = map[i][j];
                    else if(i >= hn && j < hm) c[i-hn][j] = map[i][j];
                    else d[i-hn][j-hm] = map[i][j];
                }
            }

            if(op == 5){
                for(int i = 0; i < n; i++){
                    for(int j = 0; j < m; j++){
                        if(i < hn && j < hm) map[i][j] = c[i][j];
                        else if(i < hn && j >= hm) map[i][j] = a[i][j-hm];
                        else if(i >= hn && j < hm) map[i][j] = d[i-hn][j];
                        else map[i][j] = b[i-hn][j-hm];
                    }
                }
            }

            else if(op == 6){
                for(int i = 0; i < n; i++){
                    for(int j = 0; j < m; j++){
                        if(i < hn && j < hm) map[i][j] = b[i][j];
                        else if(i < hn && j >= hm) map[i][j] = d[i][j-hm];
                        else if(i >= hn && j < hm) map[i][j] = a[i-hn][j];
                        else map[i][j] = c[i-hn][j-hm];
                    }
                }
            }
        }
    }

}
