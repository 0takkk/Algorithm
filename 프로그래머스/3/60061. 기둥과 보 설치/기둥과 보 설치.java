import java.util.*;

class Solution {
    
    public static int len;
    public static final int GI = 2, BO = 1, INSTALL = 1;
    public static int[][] gi, bo;
    public static int[] dx = {-1, -1, -1, 0, 0, 0, 1, 1, 1};
    public static int[] dy = {-1, 0, 1, -1, 0, 1, -1, 0, 1};

    public static int[][] solution(int n, int[][] build_frame) {
        len = n;
        int[][] answer = {};
        gi = new int[len+1][len+1];
        bo = new int[len+1][len+1];

        for (int[] buildFrame : build_frame) {
            int x = buildFrame[0];
            int y = buildFrame[1];
            int build = buildFrame[2];
            int install = buildFrame[3];

            if(install == INSTALL) {
                if(isCanBuild(build, x, y)) {
                    if(build == BO) bo[y][x] = BO;
                    else gi[y][x] = GI;
                }
            }
            else {
                boolean flag = true;
                if(build == BO) bo[y][x] = 0;
                else gi[y][x] = 0;

                for(int i = 0; i < 9; i++) {
                    int nx = x + dx[i];
                    int ny = y + dy[i];

                    if(isRange(nx, ny)) {
                        if(gi[ny][nx] != 0) {
                            flag = isCanInstall(gi[ny][nx], nx, ny);
                            if(!flag) break;
                        }
                        if(bo[ny][nx] != 0) {
                            flag = isCanInstall(bo[ny][nx], nx, ny);
                            if(!flag) break;
                        }
                    }
                }

                if(!flag) {
                    if(build == BO) bo[y][x] = BO;
                    else gi[y][x] = GI;
                }
            }
        }

        ArrayList<int[]> result = new ArrayList<>();
        for(int i = 0; i <= n; i++) {
            for(int j = 0; j <= n; j++) {
                if(gi[i][j] != 0) {
                    result.add(new int[] {j, i, 0});
                }
                if(bo[i][j] != 0) {
                    result.add(new int[] {j, i, 1});
                }
            }
        }

        Collections.sort(result, ((o1, o2) -> {
            if(o1[0] == o2[0]) {
                if(o1[1] == o2[1]) return o1[2] - o2[2];
                return o1[1] - o2[1];
            }
            return o1[0] - o2[0];
        }));

        answer = new int[result.size()][3];
        for(int i = 0; i < result.size(); i++) {
            answer[i] = result.get(i);
        }

        return answer;
    }

    public static boolean isCanBuild(int build, int x, int y) {
        if(build == BO) {
            return (y-1 >= 0 && (gi[y-1][x] == GI || (x+1 <= len && gi[y-1][x+1] == GI)))
                    || ((x-1 >= 0 && bo[y][x-1] == BO) && (x+1 <= len && bo[y][x+1] == BO));
        }
        else {
            return y == 0 || gi[y-1][x] == GI || bo[y][x] == BO || (x >= 1 && bo[y][x-1] == BO);
        }
    }

    public static boolean isCanInstall(int build, int x, int y) {
        if(build == BO) {
            if(x == 0 || x == len) {
                return gi[y-1][x] == GI || gi[y-1][x+1] == GI;
            }
            else {
                return (gi[y-1][x] == GI || gi[y-1][x+1] == GI)
                        || (bo[y][x-1] == BO &&  bo[y][x+1] == BO);
            }
        }
        else {
            if(x >= 1) return y == 0 || gi[y-1][x] == GI || bo[y][x] == BO || bo[y][x-1] == BO;
            else return y == 0 || gi[y-1][x] == GI || bo[y][x] == BO;
        }
    }

    public static boolean isRange(int x, int y) {
        return x >= 0 && x <= len && y >= 0 && y <= len;
    }
}