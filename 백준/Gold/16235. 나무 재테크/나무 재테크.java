import java.io.*;
import java.util.*;

public class Main {

    public static int n;
    public static int[][] map, s2d2;
    public static ArrayList<Integer>[][] trees;

    public static int[] dx = {0, 0, 1, -1, -1, -1, 1, 1};
    public static int[] dy = {1, -1, 0, 0, -1, 1, -1, 1};

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        int m = Integer.parseInt(st.nextToken());
        int k = Integer.parseInt(st.nextToken());

        map = new int[n+1][n+1];
        s2d2 = new int[n+1][n+1];
        trees = new ArrayList[n+1][n+1];

        for(int i = 1; i <= n; i++) {
            st = new StringTokenizer(br.readLine());
            for(int j = 1; j <= n; j++) {
                map[i][j] = 5;
                s2d2[i][j] = Integer.parseInt(st.nextToken());
                trees[i][j] = new ArrayList<>();
            }
        }

        while(m-->0) {
            st = new StringTokenizer(br.readLine());
            int x = Integer.parseInt(st.nextToken());
            int y = Integer.parseInt(st.nextToken());
            int age = Integer.parseInt(st.nextToken());
            trees[x][y].add(age);
        }

        while(k-->0) {
            springAndSummer();
            fall();
            winter();
        }

        int ans = 0;
        for(int i = 1; i <= n; i++) {
            for(int j = 1; j <= n; j++) {
                ans += trees[i][j].size();
            }
        }

        System.out.println(ans);
    }

    public static void springAndSummer() {
        for(int i = 1; i <= n; i++) {
            for(int j = 1; j <= n; j++) {
                if(!trees[i][j].isEmpty()) {
                    Collections.sort(trees[i][j]);

                    int idx = 0;
                    ArrayList<Integer> tmp = new ArrayList<>();
                    for(; idx < trees[i][j].size(); idx++) {
                        int age = trees[i][j].get(idx);
                        if(map[i][j] >= age) {
                            tmp.add(age+1);
                            map[i][j] -= age;
                        }
                        else break;
                    }

                    for(; idx < trees[i][j].size(); idx++) {
                        int age = trees[i][j].get(idx);
                        map[i][j] += age/2;
                    }

                    trees[i][j] = tmp;
                }
            }
        }
    }

    public static void fall() {
        for(int i = 1; i <= n; i++) {
            for(int j = 1; j <= n; j++) {
                if(!trees[i][j].isEmpty()) {
                    for (int age : trees[i][j]) {
                        if(age % 5 == 0) {
                            for(int d = 0; d < 8; d++) {
                                int x = i + dx[d];
                                int y = j + dy[d];

                                if(isRange(x, y)) {
                                    trees[x][y].add(1);
                                }
                            }
                        }
                    }
                }
            }
        }
    }

    public static void winter() {
        for(int i = 1; i <= n; i++) {
            for(int j = 1; j <= n; j++) {
                map[i][j] += s2d2[i][j];
            }
        }
    }

    public static boolean isRange(int x, int y) {
        return x >= 1 && x <= n && y >= 1 && y <= n;
    }

}