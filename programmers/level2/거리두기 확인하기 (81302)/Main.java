import java.util.*;


public class Main {
    public static void main(String[] args) {
//        System.out.println(solution("one4seveneight"));
        int[] solution = solution(new String[][]{{"POOOP", "OXXOX", "OPXPX", "OOXOX", "POXXP"},
                {"POOPX", "OXPXP", "PXXXO", "OXXXO", "OOOPP"},
                {"PXOPX", "OXOXP", "OXPOX", "OXXOP", "PXPOX"},
                {"OOOXX", "XOOOX", "OOOXX", "OXOOX", "OOOOO"},
                {"PXPXP", "XPXPX", "PXPXP", "XPXPX", "PXPXP"}});

        for (int i : solution) {
            System.out.print(i+" ");
        }
    }

    public static int[] dx = {0, 0, 1, -1, -1, -1, 1, 1, -2, 0, 2, 0};
    public static int[] dy = {1, -1, 0, 0, -1, 1, -1, 1, 0, 2, 0, -2};

    public static int[] solution(String[][] places) {
        int size = places.length;
        int[] ans = new int[size];

        for(int i = 0; i < size; i++){
            String[] place = places[i];

            boolean flag = true;
            for(int j = 0; j < 5; j++){
                for(int k = 0; k < 5; k++){
                    if(place[j].charAt(k) == 'P'){
                        flag = check(place, j, k);
                        if(!flag) break;
                    }
                }
                if(!flag) break;
            }

            if(flag) ans[i] = 1;
            else ans[i] = 0;
        }

        return ans;
    }

    public static boolean check(String[] place, int x, int y){
        int nx;
        int ny;

        for(int i = 0; i < 4; i++){
            nx = x + dx[i];
            ny = y + dy[i];

            if(!isRange(nx, ny)) continue;

            if(place[nx].charAt(ny) == 'P') return false;
        }

        for(int i = 4; i < 8; i++){
            nx = x + dx[i];
            ny = y + dy[i];

            if(!isRange(nx, ny)) continue;

            if(place[nx].charAt(ny) == 'P'){
                if(place[x].charAt(ny) == 'O' || place[nx].charAt(y) == 'O')
                    return false;
            }
        }

        for(int i = 8; i < 12; i++){
            nx = x + dx[i];
            ny = y + dy[i];

            if(!isRange(nx, ny)) continue;

            if(place[nx].charAt(ny) == 'P'){
//                if(place[(x + nx)/2].charAt((y + ny)/2) == 'O')
                if(place[x + dx[i]/2].charAt(y + dy[i]/2) == 'O')
                    return false;
            }
        }

        return true;
    }

    public static boolean isRange(int x, int y){
        return x >= 0 && x < 5 && y >= 0 && y < 5;
    }

}
